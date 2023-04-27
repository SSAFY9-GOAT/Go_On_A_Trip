package com.ssafy.goat.member.service;

import member.dto.MemberAddDto;
import member.dto.MemberDto;

public interface MemberService {

    int signUp(MemberAddDto memberAddDto);

    MemberDto myPage(Long memberId);

    void changePassword(Long memberId, String loginPw);

    void changeEmail(Long memberId, String email);

    void changePhone(Long memberId, String phone);

    void changeNickname(Long memberId, String nickname);

    void withdrawal(Long memberId, String loginPw);
}
