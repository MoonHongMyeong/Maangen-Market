package me.moon.market.domain.image.service;

import lombok.RequiredArgsConstructor;
import me.moon.market.domain.image.dao.ImageRepository;
import me.moon.market.domain.image.entity.Image;
import me.moon.market.domain.post.entity.Post;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ImageSaveService {

    private final ImageRepository imageRepository;

    public void savePostImages(List<String> filePaths, Post post){

        filePaths.stream().forEach(
                file -> imageRepository.save(Image.builder()
                        .post(post)
                        .filePath(file)
                        .build())
        );
    }
}
