package com.likelion.babel.service;

import com.likelion.babel.domain.post.Post;
import com.likelion.babel.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

    PostRepository postRepository;

    @Transactional
    public void save(Post post){
        postRepository.save(post);
    }

}
