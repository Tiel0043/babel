package com.likelion.babel.domain.post;

import com.likelion.babel.domain.Category;
import com.likelion.babel.domain.Member;
import com.likelion.babel.form.post.PostForm;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.time.LocalDateTime;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter @Setter
public class KorPost {

    @Id @GeneratedValue
    @Column(name = "kor_id")
    private Long id;

    private String title; // 한글제목

    @Column(length = 1000)
    private String content; // 한글 내용

    private String summary; // 한글 요약

    private String language = "ko";

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

}
