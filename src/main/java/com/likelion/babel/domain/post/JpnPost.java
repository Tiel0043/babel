package com.likelion.babel.domain.post;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.time.LocalDateTime;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter @Setter
public class JpnPost {

    @Id
    @GeneratedValue
    @Column(name = "jpn_id")
    private Long id;

    private String title; // 한글제목

    @Column(length=1000)
    private String content; // 한글 내용

    @Column(length=1000)
    private String summary; // 일본 요약

    private String language = "ja";

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

}
