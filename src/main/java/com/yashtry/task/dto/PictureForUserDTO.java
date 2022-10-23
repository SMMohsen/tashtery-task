package com.yashtry.task.dto;

import com.yashtry.task.enumuration.ImageType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PictureForUserDTO {

    private Long id;

    private String name;

    private String description;

    private LocalDateTime uploadedAt;

    private String uploader;

    private ImageType type;

    private String url;
}
