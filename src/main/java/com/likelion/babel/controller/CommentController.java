package com.likelion.babel.controller;

import com.likelion.babel.domain.Member;
import com.likelion.babel.dto.CommentDto;
import com.likelion.babel.form.CommentForm;
import com.likelion.babel.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/post/{postId}/comments") // 댓글 삽입
    public ResponseEntity<?> saveComment(@PathVariable Long postId,
                                              @RequestBody CommentForm commentForm,
                                              HttpSession session) throws IOException {
        Member member = (Member) session.getAttribute("member");

        if (member != null) { // 로그인 상태라면
            Long commentId = commentService.saveComment(member, commentForm, postId);

            return ResponseEntity.ok(commentId);
        } else {
            // 로그인되어 있지 않음
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인이 필요합니다.");
        }
    }

    @GetMapping("/post/{postId}/comments") // 댓글 조회
    public List<CommentDto> saveComment(@PathVariable Long postId, HttpSession session) throws IOException {
        Member member = (Member) session.getAttribute("member");

        return commentService.getComments(member, postId);
    }

}
