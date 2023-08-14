package com.likelion.babel.domain.post;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.time.LocalDateTime;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter @Setter
public class EngPost {

    @Id
    @GeneratedValue
    @Column(name = "jpn_id")
    private Long id;

    private String title;

    @Column(length=1000)
    private String content;

    private String summary; // 영어 요약

    private String language = "en";

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "post_id")
    private Post post;
}
