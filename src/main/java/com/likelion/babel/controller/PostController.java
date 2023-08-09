package com.likelion.babel.controller;

import com.likelion.babel.domain.post.Post;
import com.likelion.babel.repository.FileRepository;
import com.likelion.babel.repository.MemberRepository;
import com.likelion.babel.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PostController {

    private PostService postService;
    private MemberRepository memberRepository;
    private FileRepository fileRepository;
    @PostMapping("/post/new")
    public void Save(Post post){
//        fileRepository.save();
    }

}
