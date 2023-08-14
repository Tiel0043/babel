package com.likelion.babel.domain.comment;

import com.likelion.babel.domain.Member;
import com.likelion.babel.domain.post.Post;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.time.LocalDateTime;

import static javax.persistence.FetchType.*;

@Entity
@Getter @Setter
public class Comment {

    @Id @GeneratedValue
    @Column(name = "comment_id")
    private Long id; // 댓글 순번

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;   // 회원 연관관계

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "post_id")
    private Post post; // 게시글 연관관계

    private String Content; // 댓글 내용

    private LocalDateTime date; // 댓글 작성일자

    private Long likes;

    private String korContent; // 한글
    private String engContent; // 영어
    private String jpnContent; // 일어

}
