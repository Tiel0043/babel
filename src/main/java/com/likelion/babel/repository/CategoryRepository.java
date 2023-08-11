package com.likelion.babel.repository;

import com.likelion.babel.domain.Category;
import com.likelion.babel.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

@Repository
@RequiredArgsConstructor
public class CategoryRepository {

    private final EntityManager em;

    @Transactional
    public void save(Category category){
        em.persist(category);
    }

    public Category findByName(String categoryName) { // 카테고리 이름으로 카테고리 찾기
        TypedQuery<Category> query = em.createQuery("select c from Category c where c.name =:categoryName", Category.class);
        query.setParameter("categoryName", categoryName);

        return query.getSingleResult();
    }

}
