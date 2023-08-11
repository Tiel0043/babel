package com.likelion.babel.domain;

import com.likelion.babel.domain.post.Post;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Setter
@Table(name = "file")
public class Photo {

    @Id
    @GeneratedValue
    @Column(name = "file_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @Column(name = "file_name")
    private String name;

    @Lob
    @Column(name = "file_data")
    private byte[] data; // 이미지 데이터

}
