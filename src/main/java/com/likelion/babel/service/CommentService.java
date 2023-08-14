package com.likelion.babel.service;

import com.likelion.babel.domain.Member;
import com.likelion.babel.domain.comment.Comment;
import com.likelion.babel.domain.post.EngPost;
import com.likelion.babel.domain.post.JpnPost;
import com.likelion.babel.domain.post.KorPost;
import com.likelion.babel.domain.post.Post;
import com.likelion.babel.dto.CommentDto;
import com.likelion.babel.dto.papago.TranslationResponse;
import com.likelion.babel.form.CommentForm;
import com.likelion.babel.repository.CommentRepository;
import com.likelion.babel.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final TranslationService translationService;

    @Transactional
    public Long saveComment(Member member, CommentForm commentForm, Long postId){
        Post post = postRepository.findPost(postId);

        Comment comment = new Comment();
        comment.setLikes(0L);
        comment.setPost(post);
        comment.setDate(LocalDateTime.now());
        comment.setContent(commentForm.getContent());
        comment.setMember(member);

        if(!member.getLanguage().equals("ko")){ // 한국어로 번역
            TranslationResponse koComment = translationService.translateText(commentForm.getContent(), member.getLanguage(), "ko");
            comment.setKorContent(koComment.getMessage().getResult().getTranslatedText());
        }else{
            comment.setKorContent(commentForm.getContent());
        }

        if(!member.getLanguage().equals("en")){ // 영어로 번역
            TranslationResponse koComment = translationService.translateText(commentForm.getContent(), member.getLanguage(), "en");
            comment.setEngContent(koComment.getMessage().getResult().getTranslatedText());
        }else{
            comment.setEngContent(commentForm.getContent());
        }

        if(!member.getLanguage().equals("ja")){ // 일본어로 번역하는 경우
            TranslationResponse koComment = translationService.translateText(commentForm.getContent(), member.getLanguage(), "ja");
            comment.setJpnContent(koComment.getMessage().getResult().getTranslatedText());
        }else{
            comment.setJpnContent(commentForm.getContent());
        }
        commentRepository.saveComment(comment);

        return comment.getId();
    }

    public List<CommentDto> getComments(Member member, Long postId) {
        List<Comment> comments = commentRepository.findAll(postId);

        List<CommentDto> list = new ArrayList<>(); // dto로 변환할 배열

        if(member == null){ // 로그인 하지 않았다면
            for (Comment c : comments) {
                list.add(new CommentDto(c.getId(), c.getContent(), c.getDate(), c.getMember().getUserNickname(), c.getLikes()));
            }
        }else{
            String lang = member.getLanguage();
            if(lang.equals("ko")){
                for (Comment c : comments) {
                    list.add(new CommentDto(c.getId(), c.getKorContent(), c.getDate(), c.getMember().getUserNickname(), c.getLikes()));
                }
            } else if (lang.equals("ja")) {
                for (Comment c : comments) {
                    list.add(new CommentDto(c.getId(), c.getJpnContent(), c.getDate(), c.getMember().getUserNickname(), c.getLikes()));
                }
            }else{
                for (Comment c : comments) {
                    list.add(new CommentDto(c.getId(), c.getEngContent(), c.getDate(), c.getMember().getUserNickname(), c.getLikes()));
                }
            }
        }
        return list;
    }
}
