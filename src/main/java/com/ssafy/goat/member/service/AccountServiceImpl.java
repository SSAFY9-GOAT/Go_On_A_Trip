package com.ssafy.goat.member.service;

import common.exception.AccountException;
import common.exception.LoginException;
import member.Member;
import member.dto.LoginMember;
import member.repository.MemberJdbcRepository;
import member.repository.MemberRepository;

import java.util.Optional;

import static common.exception.ExceptionMessage.ACCOUNT_EXCEPTION;
import static common.exception.ExceptionMessage.LOGIN_EXCEPTION;

public class AccountServiceImpl implements AccountService {

    private static final AccountService accountService = new AccountServiceImpl();
    private final MemberRepository memberRepository;

    private AccountServiceImpl() {
        memberRepository = MemberJdbcRepository.getMemberRepository();
    }

    public static AccountService getAccountService() {
        return accountService;
    }

    @Override
    public LoginMember login(String loginId, String loginPw) {
        Optional<Member> findMember = memberRepository.findByLoginIdAndLoginPw(loginId, loginPw);
        if (!findMember.isPresent()) {
            throw new LoginException(LOGIN_EXCEPTION);
        }
        Member member = findMember.get();

        return LoginMember.builder()
                .id(member.getId())
                .loginId(member.getLoginId())
                .loginPw(member.getLoginPw())
                .authority(member.getAuthority().toString())
                .build();
    }

    @Override
    public String findLoginId(String email, String phone) {
        Optional<Member> findMember = memberRepository.findByEmail(email);
        if (!findMember.isPresent()) {
            throw new AccountException(ACCOUNT_EXCEPTION);
        }

        Member member = findMember.get();
        if (isNotEqual(member.getPhone(), phone)) {
            throw new AccountException(ACCOUNT_EXCEPTION);
        }

        return member.getLoginId();
    }

    @Override
    public String findLoginPw(String loginId, String email, String phone) {
        Optional<Member> findMember = memberRepository.findByLoginId(loginId);
        if (!findMember.isPresent()) {
            throw new AccountException(ACCOUNT_EXCEPTION);
        }
        Member member = findMember.get();

        if (isNotEqual(member.getEmail(), email)
                || isNotEqual(member.getPhone(), phone)) {
            throw new AccountException(ACCOUNT_EXCEPTION);
        }

        return member.getLoginPw();
    }

    private static boolean isNotEqual(String target1, String target2) {
        return !target1.equals(target2);
    }
}
