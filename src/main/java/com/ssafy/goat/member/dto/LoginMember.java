package com.ssafy.goat.member.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class LoginMember {

    private Long id;
    private String loginId;
    private String loginPw;
    private String authority;

    @Builder
    public LoginMember(Long id, String loginId, String loginPw, String authority) {
        this.id = id;
        this.loginId = loginId;
        this.loginPw = loginPw;
        this.authority = authority;
    }
}
