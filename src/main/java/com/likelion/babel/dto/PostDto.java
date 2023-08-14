package com.likelion.babel.dto;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;


@Getter @Setter
public class PostDto {

    private Long id; //글 순번

    private String member_nickName; // 글 작성자

    private String category_name; // 글 카테고리

    private String title; // 글 제목

    private String content; // 글 내용

    private String summary; // 글 요약

    private LocalDateTime date; // 게시글 작성일

    private Long hit; // 조회수

    private Long likes; // 좋아요

    public PostDto() {
    }

    public PostDto(Long id, String member_nickName, String category_name, String title,
                   String content, String summary, LocalDateTime date,
                   Long hit, Long likes) {
        this.id = id;
        this.member_nickName = member_nickName;
        this.category_name = category_name;
        this.title = title;
        this.content = content;
        this.summary = summary;
        this.date = date;
        this.hit = hit;
        this.likes = likes;
    }
}
