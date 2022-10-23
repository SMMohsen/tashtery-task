package com.yashtry.task.service;

import com.yashtry.task.exception.CustomValidationException;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Component
public class PictureHandlerServiceImpl implements PictureHandlerService {

    private final String PICTURES_ROOT = "/var/files";

    private final Set<String> ALLOWED_EXTENSIONS = new HashSet<>(Arrays.asList("png","gif","jpg"));
    @Override
    public String uploadPicture(MultipartFile picture) {

        try {

            Path rootDir = Paths.get(PICTURES_ROOT);

            if(Files.notExists(rootDir)) {

                Files.createDirectory(rootDir);
            }

            String randomName = UUID.randomUUID() + getPictureExtension(picture.getOriginalFilename());
            Files.copy(picture.getInputStream(), rootDir.resolve(randomName));

            return randomName;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
        }
    }

    @Override
    public void deletePicture(String pictureName) {
        try {
            Path rootDir = Paths.get(PICTURES_ROOT);

            Files.delete(rootDir.resolve(pictureName));

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Could not delete the file. Error: " + e.getMessage());
        }
    }

    private String getPictureExtension(String fileName) {

        String extension = fileName.split("\\.")[1];

        if(!ALLOWED_EXTENSIONS.contains(extension)) {

            throw new CustomValidationException("Not allowed extension");
        }

        return "." + extension;
    }
}
