package com.likelion.babel.domain.post;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
public class KorPost {

    @Id @GeneratedValue
    @Column(name = "kor_id")
    private Long id;

    private String title; // 한글제목

    private String content; // 한글 내용

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

}
