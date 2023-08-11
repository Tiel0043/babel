package com.likelion.babel.repository;

import com.likelion.babel.domain.post.EngPost;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class EngPostRepository {

    private final EntityManager em;

    public void save(EngPost post){
        em.persist(post);
    }

    public EngPost findPost(Long id){
        return em.find(EngPost.class, id);
    }

    public List<EngPost> findPosts(){
        return em.createQuery("select * from EngPost p", EngPost.class)
                .getResultList();
    }

}
