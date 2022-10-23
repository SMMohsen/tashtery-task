package com.yashtry.task.model;

import com.yashtry.task.enumuration.Role;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role = Role.NORMAL_USER;

    @OneToMany(fetch = FetchType.LAZY , mappedBy = "uploader")
    private Set<Picture> pictures = new HashSet<>();

    public void addPicture(Picture pic) {

        this.pictures.add(pic);
        pic.setUploader(this);
    }
}
