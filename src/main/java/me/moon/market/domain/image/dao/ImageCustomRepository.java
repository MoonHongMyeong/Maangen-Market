package me.moon.market.domain.image.dao;

import me.moon.market.domain.image.entity.Image;
import me.moon.market.domain.post.entity.Post;

import java.util.List;

public interface ImageCustomRepository {
    List<Image> findImagesByPost(Post post);
}
