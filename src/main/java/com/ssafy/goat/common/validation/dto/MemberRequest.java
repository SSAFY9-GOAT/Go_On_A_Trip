package com.ssafy.goat.common.validation.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberRequest {

    private String loginId;
    private String loginPw;
    private String username;
    private String email;
    private String phone;
    private String nickname;
    private String birth;
    private String gender;

    @Builder
    public MemberRequest(String loginId, String loginPw, String username, String email, String phone, String nickname, String birth, String gender) {
        this.loginId = loginId;
        this.loginPw = loginPw;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.nickname = nickname;
        this.birth = birth;
        this.gender = gender;
    }
}
