package com.likelion.babel.form.member;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;


@Getter @Setter
public class MemberForm {

    private String userId;
    private String userPassword;
    private String userNickname;
    private String language;
    private MultipartFile profile;

}
