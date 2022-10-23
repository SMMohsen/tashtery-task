package com.yashtry.task.controller;

import com.yashtry.task.dto.ApiResponse;
import com.yashtry.task.dto.PictureDTO;
import com.yashtry.task.dto.UploadPictureDTO;
import com.yashtry.task.service.PictureService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/pictures")
@RequiredArgsConstructor
public class PictureController {

    private final PictureService pictureService;

    @PostMapping("/upload")
    @ApiOperation(notes = "" , value = "api for upload pictures for authenticated users"
            ,consumes = "application/json" , produces = "application/json", response = ApiResponse.class)
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class, example = "Bearer access_token")
    public ApiResponse uploadPic(@ModelAttribute @Valid UploadPictureDTO uploadPictureDTO, @RequestAttribute String userEmail) {

        pictureService.uploadPicture(uploadPictureDTO,userEmail);

        return ApiResponse.ok();
    }

    @GetMapping
    @ApiOperation(notes = "" , value = "api for getting accepted pictures for all users"
            ,consumes = "application/json" , produces = "application/json", response = PictureDTO.class)
   public ApiResponse getPicturesForUsers(@RequestParam Integer offset
            , @RequestParam Integer size) {

        return ApiResponse.ok(pictureService.getPicturesForUsers(offset,size));
    }

}
