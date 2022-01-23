package me.moon.market.domain.image.service;

import com.amazonaws.services.s3.model.ObjectMetadata;

import java.io.InputStream;

public interface ImageService {

    void upload(InputStream inputStream, ObjectMetadata objectMetadata, String fileName);

    String getFileUrl(String fileName);
}
