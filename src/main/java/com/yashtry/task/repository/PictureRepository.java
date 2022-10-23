package com.yashtry.task.repository;

import com.yashtry.task.enumuration.Status;
import com.yashtry.task.model.Picture;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface PictureRepository extends PagingAndSortingRepository<Picture,Long> {

    Page<Picture> findByStatusIs(Status statuses, Pageable pageable);

    Picture findByIdAndStatusIs(Long id,Status status);
}
