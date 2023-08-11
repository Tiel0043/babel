package com.likelion.babel.form.post;

import com.likelion.babel.domain.Member;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@Getter @Setter
public class PostForm {

    private String category;

    private String title; // 제목

    private String content; // 내용

    private List<MultipartFile> files;

}
