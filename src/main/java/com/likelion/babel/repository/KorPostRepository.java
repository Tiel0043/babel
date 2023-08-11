package com.likelion.babel.repository;

import com.likelion.babel.domain.post.KorPost;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class KorPostRepository {

    private final EntityManager em;

    public void save(KorPost post){
        em.persist(post);
    }

    public KorPost findPost(Long id){
        return em.find(KorPost.class, id);
    }

    public List<KorPost> findPosts(){
        return em.createQuery("select * from KorPost p", KorPost.class)
                .getResultList();
    }

}
