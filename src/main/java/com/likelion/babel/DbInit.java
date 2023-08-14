package com.likelion.babel;

import com.likelion.babel.domain.Category;
import com.likelion.babel.domain.Member;
import com.likelion.babel.repository.CategoryRepository;
import com.likelion.babel.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class DbInit {

    private final MemberService memberService;
    private final CategoryRepository categoryRepository;

//    @PostConstruct // 초기화 데이터 DB 삽입
    public void init() {
//        saveMember("abc", "1234", "ja", LocalDateTime.now(), "naver.com", "일본인");
//        saveMember("abcd", "1234", "ko", LocalDateTime.now(), "naver.com", "한국인");
//        saveMember("abcde", "1234", "en", LocalDateTime.now(), "naver.com", "한국인");

        saveCategory("it");
        saveCategory("entertainments");
        saveCategory("economy");
        saveCategory("sports");
    }

    private void saveCategory(String cate) {
        Category category = new Category();
        category.setName(cate);
        categoryRepository.save(category);
    }

    private void saveMember(String userId, String password, String lang,
                            LocalDateTime date, MultipartFile profile, String nickname) throws IOException {
        Member member = new Member();
        member.setUserId(userId);
        member.setUserPassword(password);
        member.setLanguage(lang);
        member.setMemberJoinDate(date);
        member.setProfile(profile.getBytes());
        member.setUserNickname(nickname);
        memberService.join(member);
    }

}
