package com.yashtry.task.dto;

import com.yashtry.task.enumuration.ImageType;
import com.yashtry.task.enumuration.Status;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PictureDTO {

    private Long id;

    private String name;

    private String description;

    private Status status;

    private String uploader;

    private LocalDateTime uploadedAt;

    private ImageType type;

    private String url;
}
