package com.likelion.babel.repository;

import com.likelion.babel.domain.post.EngPost;
import com.likelion.babel.domain.post.JpnPost;
import com.likelion.babel.domain.post.KorPost;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
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

    public EngPost findByPostId(Long postId) {
        TypedQuery<EngPost> query = em.createQuery("select e from EngPost e where e.post.id =:postId", EngPost.class);
        query.setParameter("postId", postId);

        return query.getSingleResult();
    }

    public List<EngPost> findPosts(){
        return em.createQuery("select * from EngPost p", EngPost.class)
                .getResultList();
    }

    public List<EngPost> findAll(int page, Long cateId) {
        return em.createQuery("select k from EngPost k join fetch k.post p join fetch p.category c" +
                        " where c.id = :cateId", EngPost.class)
                .setParameter("cateId", cateId)
                .setFirstResult(page - 1)
                .setMaxResults(page * 10 - 1)
                .getResultList();
    }

}
