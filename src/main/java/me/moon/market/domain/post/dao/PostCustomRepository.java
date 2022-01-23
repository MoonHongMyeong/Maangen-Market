package me.moon.market.domain.post.dao;

import me.moon.market.domain.post.entity.Post;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

public interface PostCustomRepository {
    PageImpl<Post> getPostsByUserAddress(Double longitude, Double latitude, Pageable pageable);
}
