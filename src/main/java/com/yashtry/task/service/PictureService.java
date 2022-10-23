package com.yashtry.task.service;

import com.yashtry.task.dto.PictureDTO;
import com.yashtry.task.dto.PictureForUserDTO;
import com.yashtry.task.dto.UploadPictureDTO;
import com.yashtry.task.enumuration.Status;
import com.yashtry.task.exception.CustomValidationException;
import com.yashtry.task.mapper.PictureMapper;
import com.yashtry.task.model.Picture;
import com.yashtry.task.model.User;
import com.yashtry.task.repository.PictureRepository;
import com.yashtry.task.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PictureService {

    private final PictureRepository pictureRepository;

    private final PictureMapper pictureMapper;
    private final PictureHandlerService pictureHandlerService;

    private final UserRepository userRepository;

    public void uploadPicture(UploadPictureDTO dto,String userEmail) {

        String imageName = pictureHandlerService.uploadPicture(dto.getPicture());

        Picture newPic = pictureMapper.toPicture(dto);

        newPic.setName(imageName);

        User user = userRepository.findByEmail(userEmail).get();

        newPic.setUploader(user);
        
        pictureRepository.save(newPic);
    }

    public void acceptPicture(Long picId) {

        Picture picture = pictureRepository.findById(picId).get();

        if(!picture.getStatus().equals(Status.PENDING)) {

            throw new CustomValidationException("operation not allowed");
        }

        picture.accept();

        pictureRepository.save(picture);
    }

    public void rejectPicture(Long picId) {

        Picture picture = pictureRepository.findById(picId).get();

        if(!picture.getStatus().equals(Status.PENDING)) {

            throw new CustomValidationException("operation not allowed");
        }

        picture.reject();

        pictureHandlerService.deletePicture(picture.getName());

        pictureRepository.save(picture);
    }

    public List<PictureForUserDTO> getPicturesForUsers(int offset, int size) {

        Page<Picture> pictures = pictureRepository.
                findByStatusIs(Status.ACCEPTED, PageRequest.of(offset,size));

        return pictureMapper.toPictureUserDTO(pictures.getContent());
    }

    public List<PictureDTO> getPicturesForAdmin(int offset,int size) {

        Page<Picture> pictures = pictureRepository.
                findByStatusIs(Status.PENDING, PageRequest.of(offset,size));

        return pictureMapper.toDTO(pictures.getContent());
    }
}
