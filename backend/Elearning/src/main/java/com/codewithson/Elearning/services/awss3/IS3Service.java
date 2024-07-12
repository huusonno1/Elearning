package com.codewithson.Elearning.services.awss3;

import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.web.multipart.MultipartFile;


public interface IS3Service {
    String uploadFile(MultipartFile file) throws FileUploadException;
}
