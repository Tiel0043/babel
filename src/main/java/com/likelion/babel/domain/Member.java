package com.likelion.babel.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @Column(name = "member_user_id")
    private String userId;

    @Column(name = "member_pw")
    private String userPassword;

    @Column(name = "member_nick")
    private String userNickname;

    @Column(name = "member_lang")
    private String language;

    @Lob
    @Column(name = "member_profile", length = 1000)
    private byte[] profile;

    @Column(name = "member_joinDate")
    private LocalDateTime memberJoinDate;

}
