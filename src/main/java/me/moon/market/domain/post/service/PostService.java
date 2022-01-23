package me.moon.market.domain.post.service;

import lombok.RequiredArgsConstructor;
import me.moon.market.domain.image.entity.Image;
import me.moon.market.domain.image.service.ImageFindService;
import me.moon.market.domain.image.service.ImageSaveService;
import me.moon.market.domain.image.service.ImageUploadService;
import me.moon.market.domain.post.dao.PostRepository;
import me.moon.market.domain.post.dto.PostListResponse;
import me.moon.market.domain.post.dto.PostResponse;
import me.moon.market.domain.post.dto.PostSaveRequest;
import me.moon.market.domain.post.dto.PostUpdateRequest;
import me.moon.market.domain.post.entity.Category;
import me.moon.market.domain.post.entity.Post;
import me.moon.market.domain.post.entity.TradeStatus;
import me.moon.market.domain.user.entity.User;
import me.moon.market.domain.user.service.UserFindService;
import me.moon.market.global.dto.SessionUser;
import me.moon.market.global.error.exception.ErrorCode;
import me.moon.market.global.error.exception.InvalidValueException;
import me.moon.market.global.error.exception.UnAuthorizedAccessException;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserFindService userFindService;
    private final CategoryFindService categoryFindService;
    private final PostFindService postFindService;
    private final ImageFindService imageFindService;
    private final ImageSaveService imageSaveService;
    private final ImageUploadService imageUploadService;

    @Transactional(rollbackFor = Exception.class)
    public void create(PostSaveRequest dto, List<MultipartFile> photos, SessionUser user) {

        Post post = dto.toEntity(userFindService.findUserBySessionUser(user));

        Category category = categoryFindService.findByCategoryName(dto.getCategory());
        post.setCategory(category);

        List<String> filePaths = imageUploadService.uploadImages(photos);
        post.setThumbnail(filePaths.get(0));

        Post savedPost = postRepository.save(post);
        imageSaveService.savePostImages(filePaths, savedPost);
    }

    public void update(PostUpdateRequest dto, Long postId, SessionUser user) {

        Post post = postFindService.findPostById(postId);
        Category category = categoryFindService.findByCategoryName(dto.getCategory());

        isValidAuthor(user, post);

        post.setCategory(category);
        post.update(dto);
    }

    public void updateTradeStatus(String status, Long postId, SessionUser user) {

        Post post = postFindService.findPostById(postId);

        isValidAuthor(user, post);

        switch (status){
            case "SALE" :
                post.changeStatus(TradeStatus.SALE);
                break;
            case "RESERVED" :
                post.changeStatus(TradeStatus.RESERVED);
                break;
            case "SOLD" :
                post.changeStatus(TradeStatus.SOLD);
                break;
            default :
                throw new InvalidValueException(status);
        }
    }

    public void delete(Long postId, SessionUser user) {
        Post post = postFindService.findPostById(postId);

        isValidAuthor(user, post);

        post.delete();
    }

    private void isValidAuthor(SessionUser user, Post post) {
        if(new SessionUser(post.getAuthor()) != user) throw new UnAuthorizedAccessException("");
    }

    public PageImpl<PostListResponse> getPosts(SessionUser sessionUser, Pageable pageable) {

        User user = userFindService.findUserBySessionUser(sessionUser);

        PageImpl<Post> queryResults = postRepository.getPostsByUserAddress(user.getLocation().getLongitude(), user.getLocation().getLatitude(), pageable);

        List<PostListResponse> postToPostListResponse = queryResults.getContent().stream().map(post -> new PostListResponse(post))
                .collect(Collectors.toList());

        return new PageImpl<PostListResponse>(postToPostListResponse, queryResults.getPageable(), queryResults.getTotalPages());
    }

    @Transactional(readOnly = true)
    public PostResponse getPost(SessionUser sessionUser, Long postId) {

        User user = userFindService.findUserBySessionUser(sessionUser);
        Post post = postFindService.findPostById(postId);

        if(!isValidCoordinate(user, post)){
            throw new InvalidValueException("",ErrorCode.HANDLE_ACCESS_DENIED);
        }

        PostResponse response = new PostResponse(post);

        List<PostListResponse> anotherPosts = postFindService.findPostByUser(user);
        List<Image> images = imageFindService.findAllByPost(post);

        response.setAnotherPosts(anotherPosts);
        response.setImages(images);

        return response;
    }

    private boolean isValidCoordinate(User user, Post post) {

        Double postLong = post.getAuthor().getLocation().getLongitude();
        Double postLat = post.getAuthor().getLocation().getLatitude();
        Double userLong = user.getLocation().getLongitude();
        Double userLat = user.getLocation().getLatitude();

        if(postLong < userLong - 1/88.64  || postLong > userLong + 1/88.64){
            return false;
        }

        if(postLat < userLat - 1/109.958489129649955 || postLat < userLat + 1/109.958489129649955){
            return false;
        }

        return true;
    }

}
