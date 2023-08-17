package com.likelion.babel.repository;

import com.likelion.babel.domain.post.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class PostRepository {

    private final EntityManager em;

    public void save(Post post){
        em.persist(post);
    }

    public Post findPost(Long id){
        return em.find(Post.class, id);
    }


    public List<Post> findList(int page,Long cateId) {
        return em.createQuery("select p from Post p where category.id = :cateId", Post.class)
                .setParameter("cateId", cateId)
                .setFirstResult(page - 1 * 10)
                .setMaxResults(page * 10 - 1)
                .getResultList();
    }

    public long totalCount(Long cateId){
        return em.createQuery("select count(p) from Post p where category.id = :cateId", Long.class)
                .setParameter("cateId", cateId)
                .getSingleResult();
    }
}
