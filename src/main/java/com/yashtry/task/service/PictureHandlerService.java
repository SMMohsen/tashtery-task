package com.yashtry.task.service;

import org.springframework.web.multipart.MultipartFile;

public interface PictureHandlerService {

    String uploadPicture(MultipartFile file);

    void deletePicture(String pictureName);
}
