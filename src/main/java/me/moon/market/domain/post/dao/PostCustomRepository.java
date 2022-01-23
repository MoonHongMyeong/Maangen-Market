package me.moon.market.domain.post.dao;

import me.moon.market.domain.post.entity.Post;
import me.moon.market.domain.user.entity.User;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostCustomRepository {
    PageImpl<Post> getPostsByUserAddress(Double longitude, Double latitude, Pageable pageable);

    List<Post> findPostByUser(User user);
}
