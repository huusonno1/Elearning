package com.codewithson.Elearning.services.awss3;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class S3Service implements IS3Service{

    @Value("${aws.s3.bucketName}")
    private String bucketName;

    private final AmazonS3 s3;

    @Override
    public String uploadFile(MultipartFile file) throws FileUploadException {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String todayDate = dateTimeFormatter.format(LocalDate.now());
        String filePath = "";
        try {
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(file.getContentType());
            objectMetadata.setContentLength(file.getSize());
            filePath = todayDate+"/" + UUID.randomUUID().toString() +file.getOriginalFilename();
            s3.putObject(bucketName, filePath, file.getInputStream(), objectMetadata);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new FileUploadException("Error occurred in file upload ==> "+e.getMessage());
        }

//        s3.setObjectAcl(bucketName, filePath, CannedAccessControlList.PublicRead);

//        return s3.getUrl(bucketName, filePath).toString();
        return filePath;
    }
}
