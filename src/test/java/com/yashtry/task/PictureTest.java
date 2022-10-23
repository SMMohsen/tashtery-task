package com.yashtry.task;

import com.yashtry.task.enumuration.Status;
import com.yashtry.task.exception.CustomValidationException;
import com.yashtry.task.mapper.PictureMapper;
import com.yashtry.task.model.Picture;
import com.yashtry.task.repository.PictureRepository;
import com.yashtry.task.repository.UserRepository;
import com.yashtry.task.service.PictureHandlerService;
import com.yashtry.task.service.PictureService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PictureTest {

    private PictureRepository pictureRepository = mock(PictureRepository.class);

    private PictureMapper pictureMapper = Mappers.getMapper(PictureMapper.class);

    private UserRepository userRepository = mock(UserRepository.class);

    private PictureHandlerService pictureHandlerService = mock(PictureHandlerService.class);

    private PictureService pictureService;

    public PictureTest() {

        pictureService = new PictureService(pictureRepository,pictureMapper
                ,pictureHandlerService,userRepository);
    }

    @Test
    public void GivenAcceptedPictureRejectPictureTestShouldThrowException() {

        when(pictureRepository.findById(anyLong())).thenReturn(Optional.of(getPictureWithStatus(Status.REJECTED)));

        Assertions.assertThrows(CustomValidationException.class,
                ()->pictureService.rejectPicture(1l),
                "operation not allowed");

    }

    @Test
    public void GivenPendingPictureRejectPictureTestShouldSuccess() {

        when(pictureRepository.findById(anyLong())).thenReturn(Optional.of(getPictureWithStatus(Status.PENDING)));

        Assertions.assertDoesNotThrow(()->pictureService.rejectPicture(1l));

    }

    public Picture getPictureWithStatus(Status status) {

        Picture picture = new Picture();

        picture.setStatus(status);
        picture.setName("pic name");
        picture.setId(1l);
        picture.setDescription("description");

        return picture;
    }
}
