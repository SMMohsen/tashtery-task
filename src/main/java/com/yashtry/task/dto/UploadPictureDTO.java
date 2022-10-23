package com.yashtry.task.dto;

import com.yashtry.task.enumuration.ImageType;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class UploadPictureDTO {

    @NotNull
    private MultipartFile picture;

    @NotBlank
    private String description;

    @NotNull
    private ImageType type;
}
