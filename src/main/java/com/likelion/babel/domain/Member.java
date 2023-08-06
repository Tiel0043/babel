package com.likelion.babel.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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

    @Column(name = "member_profile")
    private String profile;

    @Column(name = "member_joinDate")
    private LocalDateTime memberJoinDate;

}
