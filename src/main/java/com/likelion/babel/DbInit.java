package com.likelion.babel;

import com.likelion.babel.domain.Member;
import com.likelion.babel.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class DbInit {

    private final MemberService memberService;

    @PostConstruct // 초기화 데이터 DB 삽입
    public void init() {
        Member member = new Member();
        member.setUserId("abc");
        member.setUserPassword("1234");
        member.setLanguage("english");
        member.setMemberJoinDate(LocalDateTime.now());
        member.setProfile("www.naver.com");
        member.setUserNickname("닉네임");
        memberService.join(member);
    }

}
