package com.likelion.babel.domain.post;

import com.likelion.babel.domain.Category;
import com.likelion.babel.domain.Member;
import com.likelion.babel.form.post.PostForm;
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
    private Category category; // FK  category에 값이 있다면 persist

    private String title; // 제목

    @Column(length = 1000)
    private String content; // 내용


    private String summary; // 요약

    private LocalDateTime date; // 게시글 작성일

    private Long hit; // 조회수

    private Long likes; // 좋아요

    public void updateHit(){
        this.hit += 1;
    }
    public void addLikes(){
        this.likes += 1;
    }
    public void minusLikes(){
        this.likes -= 1;
    }

    public static Post createPost(Member member, Category category, PostForm postForm, String summary) {
        Post post = new Post();
        post.setMember(member);
        post.setCategory(category);
        post.setHit(0L);
        post.setLikes(0L);
        post.setTitle(postForm.getTitle());
        post.setContent(postForm.getContent());
        post.setDate(LocalDateTime.now());
        post.setSummary(summary);
        return post;
    }

}
