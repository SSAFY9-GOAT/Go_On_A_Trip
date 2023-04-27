package com.ssafy.goat.member.service;

import member.dto.LoginMember;

public interface AccountService {

    LoginMember login(String loginId, String loginPw);

    String findLoginId(String email, String phone);

    String findLoginPw(String loginId, String email, String phone);
}
