package com.ssafy.goat.member.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class LoginMember {

    private Long id;
    private String loginId;
    private String nickname;
    private String authority;
    private boolean snsUser;

    @Builder
    public LoginMember(Long id, String loginId, String nickname, String authority, boolean snsUser) {
        this.id = id;
        this.loginId = loginId;
        this.nickname = nickname;
        this.authority = authority;
        this.snsUser = snsUser;
    }
}
