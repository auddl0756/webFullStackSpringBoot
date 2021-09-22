package com.example.demo.entity;


import lombok.*;

import javax.persistence.*;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class FileInfo {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String fileName;
    private String saveFileName;
    private String contentType;
    private boolean deleteFlag;
    private String createDate;
    private String modifyDate;
}
