package com.likelion.babel.domain;

import com.likelion.babel.domain.post.Post;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter @Setter
public class File {

    @Id @GeneratedValue
    @Column(name = "file_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    private String name;

    private String path;

    private String covertPath;

}
