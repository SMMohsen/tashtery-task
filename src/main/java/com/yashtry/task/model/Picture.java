package com.yashtry.task.model;

import com.yashtry.task.enumuration.ImageType;
import com.yashtry.task.enumuration.Status;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "pictures")
public class Picture {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private ImageType type;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status = Status.PENDING;

    private LocalDateTime uploadedAt = LocalDateTime.now();

    @Fetch(FetchMode.JOIN)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User uploader;

    public void accept() {

        this.status = Status.ACCEPTED;
    }

    public void reject() {

        this.status = Status.REJECTED;
    }
}
