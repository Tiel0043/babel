package com.likelion.babel.repository;

import com.likelion.babel.domain.Photo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class FileRepository {

    private final EntityManager em;

    public void save(Photo file){
        em.persist(file);
    }

    public Photo getFile(Long id){
        return em.find(Photo.class, id);
    }

    public List<Photo> getFiles(){
        return em.createQuery("select f from Photo f")
                .getResultList();
    }

}
