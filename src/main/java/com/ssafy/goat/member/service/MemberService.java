package com.ssafy.goat.member.service;

import com.ssafy.goat.member.dto.MemberAddDto;
import com.ssafy.goat.member.dto.MemberDto;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface MemberService {

    int signUp(MemberAddDto memberAddDto);

    MemberDto myPage(Long memberId);

    void changePassword(Long memberId, String loginPw);

    void changeEmail(Long memberId, String email);

    void changePhone(Long memberId, String phone);

    void changeNickname(Long memberId, String nickname);

    void withdrawal(Long memberId, String loginPw);
}
