package com.project.mission.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class BoardWrite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long num;
    private String title;
    private String content;
    private String password;


}
