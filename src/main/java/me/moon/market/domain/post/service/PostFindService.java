package me.moon.market.domain.post.service;

import lombok.RequiredArgsConstructor;
import me.moon.market.domain.post.dao.PostRepository;
import me.moon.market.domain.post.entity.Post;
import me.moon.market.domain.post.exception.PostNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostFindService {

    private final PostRepository postRepository;


    public Post findPostById(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException(postId.toString()));
    }
}
