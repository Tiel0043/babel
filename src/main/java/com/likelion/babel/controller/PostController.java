package com.likelion.babel.controller;

import com.likelion.babel.domain.Member;
import com.likelion.babel.dto.PostDto;
import com.likelion.babel.dto.PostListDto;
import com.likelion.babel.form.post.PostForm;
import com.likelion.babel.repository.FileRepository;
import com.likelion.babel.repository.MemberRepository;
import com.likelion.babel.service.PostService;
import com.likelion.babel.service.TranslationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final MemberRepository memberRepository;
    private final FileRepository fileRepository;
    private final TranslationService translationService;


    @PostMapping("/post/new") // 게시글 정보
    public ResponseEntity<String> Save(PostForm postForm, HttpSession session) throws IOException {

        Member member = (Member) session.getAttribute("member");

        if (member != null) {
            postService.post(member, postForm);
            return ResponseEntity.ok("게시글이 작성되었습니다.");
        } else {
            // 로그인되어 있지 않음
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("need Login");
        }
    }

    // 게시글 상세 조회
    @GetMapping("/post/{id}")
    public PostDto getPost(@PathVariable Long id, HttpSession session){
        Member member = (Member) session.getAttribute("member");

        PostDto postDto = postService.getPost(id, member);

        return postDto;
    }

    @GetMapping("/post")
    public PostListDto getPost(@RequestParam int page, @RequestParam String category, HttpSession session){
        Member member = (Member) session.getAttribute("member");
        PostListDto list = postService.getPosts(member, page, category);
        return list;
    }

}
