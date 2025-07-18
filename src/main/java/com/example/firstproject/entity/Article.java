package com.example.firstproject.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity // DB가 해당 객체를 인식 가능
@AllArgsConstructor
@NoArgsConstructor // default 생성자를 추가
@ToString
@Getter

public class Article {

    @Id
    @GeneratedValue // 자동 생성
    private Long id;

    @Column
    private String title;

    @Column
    private String content;

}
