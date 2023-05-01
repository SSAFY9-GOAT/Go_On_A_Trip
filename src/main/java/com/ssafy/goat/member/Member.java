package com.ssafy.goat.member;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    private Long id;
    private String loginId;
    private String loginPw;
    private String username;
    private String email;
    private String phone;
    private String birth;
    private String gender;
    private String nickname;
    private LocalDateTime nicknameLastModifiedDate;
    private Authority authority;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;

    public Member(Long memberId) {
        this.id = memberId;
    }

    @Builder
    public Member(Long id, String loginId, String loginPw, String username, String email, String phone, String birth, String gender, String nickname, LocalDateTime nicknameLastModifiedDate, Authority authority, LocalDateTime createdDate, LocalDateTime lastModifiedDate) {
        this.id = id;
        this.loginId = loginId;
        this.loginPw = loginPw;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.birth = birth;
        this.gender = gender;
        this.nickname = nickname;
        this.nicknameLastModifiedDate = nicknameLastModifiedDate;
        this.authority = authority;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
    }

    //== 비즈니스 로직 ==//
    public void changeLoginPw(String loginPw) {
        this.loginPw = loginPw;
        this.lastModifiedDate = LocalDateTime.now();
    }

    public void changeEmail(String email) {
        this.email = email;
        this.lastModifiedDate = LocalDateTime.now();
    }

    public void changePhone(String phone) {
        this.phone = phone;
        this.lastModifiedDate = LocalDateTime.now();
    }

    public void changeNickname(String nickname) {
        this.nickname = nickname;
        this.nicknameLastModifiedDate = LocalDateTime.now();
        this.lastModifiedDate = LocalDateTime.now();
    }
}
