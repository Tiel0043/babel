package com.likelion.babel.controller;

import com.likelion.babel.domain.Member;
import com.likelion.babel.dto.PostDto;
import com.likelion.babel.dto.papago.TranslationResponse;
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

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final MemberRepository memberRepository;
    private final FileRepository fileRepository;
    private final TranslationService translationService;


    @PostMapping("/post/new")
    public ResponseEntity<String> Save(PostForm postForm, HttpSession session) throws IOException {

        Member member = (Member) session.getAttribute("member");

        if (member != null) {
            postService.post(member, postForm);

            return ResponseEntity.ok("게시글이 작성되었습니다.");
        } else {
            // 로그인되어 있지 않음
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인이 필요합니다.");
        }
    }

    @GetMapping("/post/{id}")
    public PostDto getPost(@PathVariable Long id, HttpSession session){
        Member member = (Member) session.getAttribute("member");

        if (member != null) {
            System.out.println("회원 잇지롱");
            System.out.println(id);
        } else {
            System.out.println("회원 없지롱");
            System.out.println(id);
        }
        return new PostDto();
    }

}
