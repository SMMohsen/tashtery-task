package com.yashtry.task.controller;

import com.yashtry.task.dto.ApiResponse;
import com.yashtry.task.dto.PictureDTO;
import com.yashtry.task.service.PictureService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final PictureService pictureService;

    @GetMapping("/pictures")
    @ApiOperation(notes = "" , value = "api for getting pending pictures for admin"
            ,consumes = "application/json" , produces = "application/json", response = PictureDTO.class)
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class, example = "Bearer access_token")
    public ApiResponse getPicturesForAdmin(@RequestParam Integer offset
            , @RequestParam Integer size) {

        return ApiResponse.ok(pictureService.getPicturesForAdmin(offset,size));
    }

    @PostMapping("/picture/{picId}/accept")
    @ApiOperation(notes = "" , value = "api for accept pending pictures for admin"
            ,consumes = "application/json" , produces = "application/json", response = ApiResponse.class)
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class, example = "Bearer access_token")
    public ApiResponse acceptPicture(@PathVariable Long picId) {

        pictureService.acceptPicture(picId);

        return ApiResponse.ok();
    }

    @PostMapping("/picture/{picId}/reject")
    @ApiOperation(notes = "" , value = "api for reject pending pictures for admin"
            ,consumes = "application/json" , produces = "application/json", response = ApiResponse.class)
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class, example = "Bearer access_token")
    public ApiResponse rejectPicture(@PathVariable Long picId) {

        pictureService.rejectPicture(picId);

        return ApiResponse.ok();
    }
}
