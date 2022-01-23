package me.moon.market.domain.post.service;

import lombok.RequiredArgsConstructor;
import me.moon.market.domain.image.service.ImageSaveService;
import me.moon.market.domain.post.dao.PostRepository;
import me.moon.market.domain.post.dto.PostSaveRequest;
import me.moon.market.domain.post.dto.PostUpdateRequest;
import me.moon.market.domain.post.entity.Category;
import me.moon.market.domain.post.entity.Post;
import me.moon.market.domain.post.entity.TradeStatus;
import me.moon.market.domain.user.service.UserFindService;
import me.moon.market.global.dto.SessionUser;
import me.moon.market.global.error.exception.InvalidValueException;
import me.moon.market.global.error.exception.UnAuthorizedAccessException;
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
    private final ImageSaveService imageService;

    @Transactional(rollbackFor = Exception.class)
    public void create(PostSaveRequest dto, List<MultipartFile> photos, SessionUser user) {

        Post post = dto.toEntity(userFindService.findUserBySessionUser(user));
        Category category = categoryFindService.findByCategoryName(dto.getCategory());

        post.setCategory(category);

        Post savedPost = postRepository.save(post);

        List<String> filePaths = photos.stream()
                .map(MultipartFile::getOriginalFilename)
                .collect(Collectors.toList());

        imageService.savePostImages(filePaths, savedPost);
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

}
