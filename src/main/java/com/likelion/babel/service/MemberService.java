package com.likelion.babel.service;

import com.likelion.babel.domain.Member;
import com.likelion.babel.form.Member.LoginForm;
import com.likelion.babel.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true) // 트랜잭션 안에서 JPA 수행
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional // 트랜잭션 readOnly 기본이 false -> insert, update, delete는 @Transaction을 걸어줌
    public Long join(Member member) {
        memberRepository.save(member);
        return member.getId();
    }

    public Member findOne(Long id){
        return memberRepository.findOne(id);
    }

    public List<Member> findAll(){
        return memberRepository.findAll();
    }

    public Member login(LoginForm form) {
        Member findMember = memberRepository.findByUserId(form.getUserId());

        if(findMember != null && findMember.getUserPassword().equals(form.getUserPassword())){ // 로그인 검증
            return findMember;
        }
        else {
            return null;
        }
    }
}
