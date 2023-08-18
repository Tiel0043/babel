package com.likelion.babel.service;

import com.likelion.babel.domain.Category;
import com.likelion.babel.domain.Member;
import com.likelion.babel.domain.Photo;
import com.likelion.babel.domain.post.EngPost;
import com.likelion.babel.domain.post.JpnPost;
import com.likelion.babel.domain.post.KorPost;
import com.likelion.babel.domain.post.Post;
import com.likelion.babel.dto.PostDto;
import com.likelion.babel.dto.PostListDto;
import com.likelion.babel.dto.papago.TranslationResponse;
import com.likelion.babel.form.post.PostForm;
import com.likelion.babel.repository.*;
import io.github.flashvayne.chatgpt.service.ChatgptService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
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

        String summary = chatgptService.sendMessage(postForm.getContent() + "\n 50자 이내로 요약해줘");

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
            korPost.setSummary(ko_summary.getMessage().getResult().getTranslatedText());
            korPostRepository.save(korPost);
        }
        else{
            KorPost korPost = new KorPost();
            korPost.setPost(post);
            korPost.setSummary(post.getSummary());
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
        jpnPost.setSummary(ja_summary.getMessage().getResult().getTranslatedText());
        jpnPostRepository.save(jpnPost);
        }
        else{
            JpnPost jpnPost = new JpnPost();
            jpnPost.setPost(post);
            jpnPost.setSummary(post.getSummary());
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
            engPost.setSummary(en_summary.getMessage().getResult().getTranslatedText());
            engPostRepository.save(engPost);
        } else{
            EngPost engPost = new EngPost();
            engPost.setPost(post);
            engPost.setSummary(post.getSummary());
            engPost.setTitle(post.getTitle());
            engPost.setContent(post.getContent());
            engPostRepository.save(engPost);
        }

    }

    public PostDto getPost(Long id, Member member) {

        Post post = postRepository.findPost(id); // 1차로 영속성 컨텍스트를 뒤져 post객체를 가져온다.

        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setLikes(post.getLikes());
        postDto.setDate(post.getDate());
        postDto.setMember_nickName(post.getMember().getUserNickname());
        postDto.setCategory_name(post.getCategory().getName());
        postDto.setTitle(post.getTitle());
        postDto.setContent(post.getContent());
        postDto.setSummary(post.getSummary());
        postDto.setHit(post.getHit());




        if (member != null) { // 만약 회원이 있다면
            String lang = member.getLanguage();
            if(lang.equals("ko")){
                KorPost korPost = korPostRepository.findByPostId(id);
                postDto.setTitle(korPost.getTitle());
                postDto.setContent(korPost.getContent());
                postDto.setSummary(korPost.getSummary());

            } else if (lang.equals("ja")) {
                JpnPost jpnPost = jpnPostRepository.findByPostId(id);
                postDto.setTitle(jpnPost.getTitle());
                postDto.setContent(jpnPost.getContent());
                postDto.setSummary(jpnPost.getSummary());
            }else{
                EngPost engPost = engPostRepository.findByPostId(id);
                postDto.setTitle(engPost.getTitle());
                postDto.setContent(engPost.getContent());
                postDto.setSummary(engPost.getSummary());
            }

        }
        return postDto;
    }

    public PostListDto getPosts(Member member, int page, String categoryName) {
        log.info("회원 언어" + member.getLanguage());

        Long cateId = categoryRepository.findByName(categoryName).getId(); // 카테고리 아이디 조회

        List<PostDto> list = new ArrayList<>();


        if(member != null){ // 로그인을 했다면
            String lang = member.getLanguage();
            log.info("회원 언어" + member.getLanguage());

            if(lang.equals("ko")){
                log.info("회원 ko언어" + member.getLanguage());

                List<KorPost> korList = korPostRepository.findAll(page, cateId);
                for(KorPost korPost : korList){
                    Post post = korPost.getPost();

                    list.add(new PostDto(post.getId(), post.getMember().getUserNickname(), post.getCategory().getName(),
                            korPost.getTitle(), korPost.getContent(), korPost.getSummary(),
                            post.getDate(), post.getHit(), post.getLikes()));
                }
            } else if (lang.equals("en")) {
                log.info("회원 en 언어" + member.getLanguage());

                List<EngPost> engList = engPostRepository.findAll(page, cateId);
                for(EngPost engPost : engList){
                    Post post = engPost.getPost();
                    list.add(new PostDto(post.getId(), post.getMember().getUserNickname(), post.getCategory().getName(),
                            engPost.getTitle(), engPost.getContent(), engPost.getSummary(),
                            post.getDate(), post.getHit(), post.getLikes()));
                }
            }else {
                log.info("회원 ja 언어" + member.getLanguage());

                List<JpnPost> jpnList = jpnPostRepository.findAll(page, cateId);
                for (JpnPost jpnPost : jpnList) {
                    Post post = jpnPost.getPost();
                    list.add(new PostDto(post.getId(), post.getMember().getUserNickname(), post.getCategory().getName(),
                            jpnPost.getTitle(), jpnPost.getContent(), jpnPost.getSummary(),
                            post.getDate(), post.getHit(), post.getLikes()));
                }
            }
        }else{ // 로그인 안했다면
            log.info("로그인 안됨ㅋ");
            List<Post> postList = postRepository.findList(page, cateId);
            for(Post p : postList){
                list.add(new PostDto(p.getId(), p.getMember().getUserNickname(), p.getCategory().getName(),
                        p.getTitle(), p.getContent(), p.getSummary(),
                        p.getDate(), p.getHit(), p.getLikes()));
            }
        }

        long count = postRepository.totalCount(cateId);
        boolean hasNext = (page * 10) < count;

        return new PostListDto(page, hasNext, list);
    }



}
