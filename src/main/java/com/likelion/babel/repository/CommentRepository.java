package com.likelion.babel.repository;

import com.likelion.babel.domain.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CommentRepository {

    private final EntityManager em;

    public void saveComment(Comment comment) {
        em.persist(comment);
    }

}
