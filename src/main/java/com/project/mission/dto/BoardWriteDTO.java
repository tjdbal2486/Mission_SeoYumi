package com.project.mission.dto;

import lombok.Data;

@Data
public class BoardWriteDTO {
    private Long num;
    private String title;
    private String content;
    private String password;


}
