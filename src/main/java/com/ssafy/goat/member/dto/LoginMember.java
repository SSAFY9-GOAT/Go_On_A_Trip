package com.ssafy.goat.member.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class LoginMember {

    private Long id;
    private String loginId;
    private String nickname;
    private String authority;

    @Builder
    public LoginMember(Long id, String loginId, String nickname, String authority) {
        this.id = id;
        this.loginId = loginId;
        this.nickname = nickname;
        this.authority = authority;
    }
}
