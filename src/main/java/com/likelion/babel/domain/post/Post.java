package com.likelion.babel.domain.post;

import com.likelion.babel.domain.Category;
import com.likelion.babel.domain.File;
import com.likelion.babel.domain.Member;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.FetchType.*;

@Entity
@Getter @Setter
public class Post {

    @Id @GeneratedValue
    @Column(name = "post_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member; // FK

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "category_id")
    private Category category; // FK

    private String title; // 제목

    private String content; // 내용

    private String summary; // 요약

    private LocalDateTime date; // 게시글 작성일

    private Long hit; // 조회수

    private Long likes; // 좋아요

}
