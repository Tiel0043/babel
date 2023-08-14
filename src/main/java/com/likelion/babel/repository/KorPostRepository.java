package com.likelion.babel.repository;

import com.likelion.babel.domain.Member;
import com.likelion.babel.domain.post.KorPost;
import com.likelion.babel.domain.post.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
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

    public KorPost findByPostId(Long postId) {
        TypedQuery<KorPost> query = em.createQuery("select k from KorPost k where k.post.id = :postId", KorPost.class);
        query.setParameter("postId", postId);
        return query.getSingleResult();
    }

    public List<KorPost> findAll(int page, Long cateId) {
        return em.createQuery("select k from KorPost k join fetch k.post p join fetch p.category c" +
                        " where c.id = :cateId", KorPost.class)
                .setParameter("cateId", cateId)
                .setFirstResult(page - 1)
                .setMaxResults(page * 10 - 1)
                .getResultList();
    }
}
