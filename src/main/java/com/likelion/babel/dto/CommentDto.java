package com.likelion.babel.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class CommentDto {

    private Long contentId;

    private String content;

    private LocalDateTime date;

    private String memberNickName;

    private Long likes;

    public CommentDto() {
    }

    public CommentDto(Long contentId, String content, LocalDateTime date, String memberNickName, Long likes) {
        this.contentId = contentId;
        this.content = content;
        this.date = date;
        this.memberNickName = memberNickName;
        this.likes = likes;
    }
}
