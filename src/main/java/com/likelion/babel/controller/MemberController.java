package com.likelion.babel.controller;

import com.likelion.babel.domain.Member;
import com.likelion.babel.form.Member.LoginForm;
import com.likelion.babel.form.Member.MemberForm;
import com.likelion.babel.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

@RestController // ResponseBody + Controller
@RequiredArgsConstructor // final 필드를 생성자로 만들어 줌
public class MemberController {

    private final MemberService memberService; // 해당 객체를 스프링 빈이 관리

    @PostMapping("/members/signup") // 해당 url로 포스트 매핑
    public void create(@RequestBody MemberForm form) {
        Member member = new Member();
        member.setUserId(form.getUserId());
        member.setUserPassword(form.getUserPassword());
        member.setUserNickname(form.getUserNickname());
        member.setLanguage(form.getLanguage());
        member.setProfile(form.getProfile());
        member.setMemberJoinDate(LocalDateTime.now());

        memberService.join(member);
    }

    @PostMapping("/members/login")
    public ResponseEntity login(@RequestBody LoginForm form, HttpSession session){
        Member member = memberService.login(form);

        if (member != null) {
            session.setAttribute("member", member);
            // 로그인 성공 시 회원 정보를 응답으로 반환
            return ResponseEntity.ok(member);
        } else {
            // 로그인 실패 시 에러 메시지를 응답으로 반환
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("아이디 또는 비밀번호가 잘못되었습니다.");
        }
    }
    @PostMapping("/members/logout")
    public ResponseEntity logout(HttpSession session) {
        // 로그아웃 시 세션에서 회원 정보를 제거
        session.removeAttribute("loggedInMember");
        return ResponseEntity.ok("로그아웃되었습니다."); // 메시지를 JSON 형태로 응답
    }

}
