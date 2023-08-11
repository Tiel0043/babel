package com.likelion.babel.repository;

import com.likelion.babel.domain.post.JpnPost;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class JpnPostRepository {

    private final EntityManager em;

    public void save(JpnPost post){
        em.persist(post);
    }

    public JpnPost findPost(Long id){
        return em.find(JpnPost.class, id);
    }

    public List<JpnPost> findPosts(){
        return em.createQuery("select * from JpnPost p", JpnPost.class)
                .getResultList();
    }

}
