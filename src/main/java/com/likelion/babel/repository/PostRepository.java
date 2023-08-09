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

    public List<Post> findPosts(){
        return em.createQuery("select * from Post p", Post.class)
                .getResultList();
    }



}
