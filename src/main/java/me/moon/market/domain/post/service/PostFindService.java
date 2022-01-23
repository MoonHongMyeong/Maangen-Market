package me.moon.market.domain.post.service;

import lombok.RequiredArgsConstructor;
import me.moon.market.domain.post.dao.PostRepository;
import me.moon.market.domain.post.dto.PostListResponse;
import me.moon.market.domain.post.entity.Post;
import me.moon.market.domain.post.exception.PostNotFoundException;
import me.moon.market.domain.user.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostFindService {

    private final PostRepository postRepository;


    public Post findPostById(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException(postId.toString()));
    }

    public List<PostListResponse> findPostByUser(User user) {
        return postRepository.findPostByUser(user).stream()
                .map(post -> new PostListResponse(post))
                .collect(Collectors.toList());
    }
}
