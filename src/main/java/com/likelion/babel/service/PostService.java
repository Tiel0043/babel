package com.likelion.babel.service;

import com.likelion.babel.domain.Category;
import com.likelion.babel.domain.Member;
import com.likelion.babel.domain.Photo;
import com.likelion.babel.domain.post.EngPost;
import com.likelion.babel.domain.post.JpnPost;
import com.likelion.babel.domain.post.KorPost;
import com.likelion.babel.domain.post.Post;
import com.likelion.babel.dto.papago.TranslationResponse;
import com.likelion.babel.form.post.PostForm;
import com.likelion.babel.repository.*;
import io.github.flashvayne.chatgpt.service.ChatgptService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class PostService {

    private final PostRepository postRepository;
    private final CategoryRepository categoryRepository;
    private final FileRepository fileRepository;
    private final TranslationService translationService;
    private final ChatgptService chatgptService;
    private final EngPostRepository engPostRepository;
    private final KorPostRepository korPostRepository;
    private final JpnPostRepository jpnPostRepository;

    @Transactional
    public void post(Member member, PostForm postForm) throws IOException {

        // 엔티티 조회
        Category category = categoryRepository.findByName(postForm.getCategory());

        String summary = chatgptService.sendMessage(postForm.getContent() + "\n 100자 이내로 요약해줘");

        Post post = Post.createPost(member, category, postForm, summary);


        postRepository.save(post); // 게시글 삽입 후


        if (postForm.getFiles() != null) {
            List<Photo> files = new ArrayList<>();
            for (MultipartFile file : postForm.getFiles()) {
                Photo photo = new Photo();
                photo.setName(file.getName());
                photo.setData(file.getBytes());
                photo.setPost(post);

                fileRepository.save(photo);
            }
        }

        if(!member.getLanguage().equals("ko")){

            TranslationResponse ko_title = translationService.translateText(post.getTitle(), member.getLanguage(), "ko");
            TranslationResponse ko_content = translationService.translateText(post.getContent(), member.getLanguage(), "ko");
            TranslationResponse ko_summary = translationService.translateText(post.getSummary(), member.getLanguage(), "ko");
            KorPost korPost = new KorPost();
            korPost.setPost(post);
            korPost.setTitle(ko_title.getMessage().getResult().getTranslatedText());
            korPost.setContent(ko_content.getMessage().getResult().getTranslatedText());
            korPost.setSumamry(ko_summary.getMessage().getResult().getTranslatedText());
            korPostRepository.save(korPost);
        }
        else{
            KorPost korPost = new KorPost();
            korPost.setPost(post);
            korPost.setSumamry(post.getSummary());
            korPost.setTitle(post.getTitle());
            korPost.setContent(post.getContent());
            korPostRepository.save(korPost);
        }


        if(!member.getLanguage().equals("ja")){
        TranslationResponse ja_title = translationService.translateText(post.getTitle(), member.getLanguage(), "ja");
        TranslationResponse ja_content = translationService.translateText(post.getContent(), member.getLanguage(), "ja");
        TranslationResponse ja_summary = translationService.translateText(post.getSummary(), member.getLanguage(), "ja");
        JpnPost jpnPost = new JpnPost();
        jpnPost.setPost(post);
        jpnPost.setTitle(ja_title.getMessage().getResult().getTranslatedText());
        jpnPost.setContent(ja_content.getMessage().getResult().getTranslatedText());
        jpnPost.setSumamry(ja_summary.getMessage().getResult().getTranslatedText());
        jpnPostRepository.save(jpnPost);
        }
        else{
            JpnPost jpnPost = new JpnPost();
            jpnPost.setPost(post);
            jpnPost.setSumamry(post.getSummary());
            jpnPost.setTitle(post.getTitle());
            jpnPost.setContent(post.getContent());
            jpnPostRepository.save(jpnPost);
        }

        // 영어
        if(!member.getLanguage().equals("en")) {
            TranslationResponse en_title = translationService.translateText(post.getTitle(), member.getLanguage(), "en");
            TranslationResponse en_content = translationService.translateText(post.getContent(), member.getLanguage(), "en");
            TranslationResponse en_summary = translationService.translateText(post.getSummary(), member.getLanguage(), "en");
            EngPost engPost = new EngPost();
            engPost.setPost(post);
            engPost.setTitle(en_title.getMessage().getResult().getTranslatedText());
            engPost.setContent(en_content.getMessage().getResult().getTranslatedText());
            engPost.setSumamry(en_summary.getMessage().getResult().getTranslatedText());
            engPostRepository.save(engPost);
        } else{
            EngPost engPost = new EngPost();
            engPost.setPost(post);
            engPost.setSumamry(post.getSummary());
            engPost.setTitle(post.getTitle());
            engPost.setContent(post.getContent());
            engPostRepository.save(engPost);
        }

    }
}
