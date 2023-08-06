package com.likelion.babel.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter @Setter
public class Follow {

    @Id @GeneratedValue
    @Column(name = "follow_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member memberId; // 회원

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "following_id")
    private Member followingId; // 팔로잉 회원


    private LocalDateTime followDate; // 팔로우 날짜

}
