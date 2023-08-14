package com.likelion.babel.repository;

import com.likelion.babel.domain.post.JpnPost;
import com.likelion.babel.domain.post.KorPost;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
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

    public JpnPost findByPostId(Long postId) {
        TypedQuery<JpnPost> query = em.createQuery("select j from JpnPost j where j.post.id =:postId", JpnPost.class);
        query.setParameter("postId", postId);

        return query.getSingleResult();
    }

    public List<JpnPost> findPosts(){
        return em.createQuery("select * from JpnPost p", JpnPost.class)
                .getResultList();
    }

    public List<JpnPost> findAll(int page, Long cateId) {
        return em.createQuery("select k from JpnPost k join fetch k.post p join fetch p.category c" +
                        " where c.id = :cateId", JpnPost.class)
                .setParameter("cateId", cateId)
                .setFirstResult(page - 1)
                .setMaxResults(page * 10 - 1)
                .getResultList();
    }

}
