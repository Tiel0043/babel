package com.likelion.babel.repository;

import com.likelion.babel.domain.File;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class FileRepository {

    private EntityManager em;

    public void save(File file){
        em.persist(file);
    }

    public File getFile(Long id){
        return em.find(File.class, id);
    }

    public List<File> getFiles(){
        return em.createQuery("select f from File f")
                .getResultList();
    }

}
