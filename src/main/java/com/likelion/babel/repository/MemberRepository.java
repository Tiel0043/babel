package com.likelion.babel.repository;

import com.likelion.babel.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;

    // 회원 등록 로직 (Member 객체 INSERT)]

    public void save(Member member){
        em.persist(member);
    }

    public Member findOne(Long id){
        Member member = em.find(Member.class, id); // find() := id에 맞는 회원을 가져온다 select * from member where member.id = id
        return member;
    }

    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class) // JPQL 언어 select * from member과 같음
                .getResultList(); // LIST 구조로 뽑는다.
    }

    public Member findByUserId(String userId) {
        TypedQuery<Member> query = em.createQuery("select m from Member m where m.userId =:userId", Member.class);
        query.setParameter("userId", userId);

        return query.getSingleResult();
    }
}
