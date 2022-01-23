package me.moon.market.domain.image.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.InputStream;

@RequiredArgsConstructor
@Component
public class S3Service implements ImageService{

    private final AmazonS3 amazonS3;
    private final S3Component s3Component;

    @Override
    public void upload(InputStream inputStream, ObjectMetadata objectMetadata, String fileName) {
        amazonS3.putObject(
                new PutObjectRequest(s3Component.getBucket(),
                        fileName,
                        inputStream,
                        objectMetadata)
                        .withCannedAcl(CannedAccessControlList.PublicRead)
        );
    }

    @Override
    public String getFileUrl(String fileName) {
        return amazonS3
                .getUrl(s3Component.getBucket(), fileName)
                .toString();
    }
}
