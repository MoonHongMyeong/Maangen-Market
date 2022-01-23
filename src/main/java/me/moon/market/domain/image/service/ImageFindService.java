package me.moon.market.domain.image.service;

import lombok.RequiredArgsConstructor;
import me.moon.market.domain.image.dao.ImageRepository;
import me.moon.market.domain.image.entity.Image;
import me.moon.market.domain.post.entity.Post;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageFindService {

    private final ImageRepository imageRepository;

    public List<Image> findAllByPost(Post post) {
        return imageRepository.findImagesByPost(post);
    }
}
