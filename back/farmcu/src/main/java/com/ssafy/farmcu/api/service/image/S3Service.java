package com.ssafy.farmcu.api.service.image;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class S3Service {
    @Value("${cloud.aws.s3.bucket}")
    private String S3Bucket;
    private final AmazonS3Client amazonS3Client;

    public String uploadFile(MultipartFile multipartFile) throws Exception {
        String originalName = multipartFile.getOriginalFilename(); //파일 이름
        long size = multipartFile.getSize(); //파일 크기

        ObjectMetadata objectMetaData = new ObjectMetadata();
        objectMetaData.setContentType(multipartFile.getContentType());
        objectMetaData.setContentLength(size);

        //S3에 업로드
        amazonS3Client.putObject(
                new PutObjectRequest(S3Bucket, originalName, multipartFile.getInputStream(), objectMetaData)
                        .withCannedAcl(CannedAccessControlList.PublicRead)
        );

        String imagePath = amazonS3Client.getUrl(S3Bucket, originalName).toString(); //접근가능한 URL 가져오기

        return imagePath;
    }
}
