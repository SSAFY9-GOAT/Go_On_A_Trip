package com.ssafy.goat.member.service;

import com.ssafy.goat.common.exception.InformationChangeException;
import com.ssafy.goat.common.exception.SignUpException;
import com.ssafy.goat.common.exception.WithdrawalException;
import com.ssafy.goat.common.validation.MemberUpdateValidation;
import com.ssafy.goat.common.validation.dto.InvalidResponse;
import com.ssafy.goat.common.validation.dto.MemberRequest;
import com.ssafy.goat.member.Member;
import com.ssafy.goat.member.dto.MemberAddDto;
import com.ssafy.goat.member.dto.MemberDto;
import com.ssafy.goat.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

//    private static final MemberService memberService = new MemberServiceImpl();
    private final MemberRepository memberRepository;

//    private MemberServiceImpl() {
//        memberRepository = MemberJdbcRepository.getMemberRepository();
//    }

//    public static MemberService getMemberService() {
//        return memberService;
//    }

    @Override
    public int signUp(MemberAddDto memberAddDto) {
        Optional<Member> findMember = memberRepository.duplicateSignup(memberAddDto);
        if (findMember.isPresent()) {
            throw new SignUpException();
        }

        Member member = Member.builder()
                .loginId(memberAddDto.getLoginId())
                .loginPw(memberAddDto.getLoginPw())
                .username(memberAddDto.getUsername())
                .email(memberAddDto.getEmail())
                .phone(memberAddDto.getPhone())
                .birth(memberAddDto.getBirth())
                .gender(memberAddDto.getGender())
                .nickname(memberAddDto.getNickname())
                .authority(memberAddDto.getAuthority())
                .build();

        return memberRepository.save(member);
    }

    @Override
    public MemberDto myPage(Long memberId) {
        Member member = getMemberByMemberId(memberId);

        return MemberDto.builder()
                .loginId(member.getLoginId())
                .loginPw(member.getLoginPw())
                .username(member.getUsername())
                .email(member.getEmail())
                .phone(member.getPhone())
                .birth(member.getBirth())
                .gender(member.getGender())
                .nickname(member.getNickname())
                .authority(member.getAuthority())
                .build();
    }

    @Override
    public void changePassword(Long memberId, String loginPw) {
        Member member = getMemberByMemberId(memberId);
        member.changeLoginPw(loginPw);

//        updateValidation(member);

        memberRepository.update(memberId, member);
    }

    @Override
    public void changeEmail(Long memberId, String email) {
        Member member = getMemberByMemberId(memberId);

        Optional<Member> emailCheck = memberRepository.findByEmail(email);
        if (emailCheck.isPresent()) {
            throw new InformationChangeException();
        }

        member.changeEmail(email);

//        updateValidation(member);

        memberRepository.update(memberId, member);
    }

    @Override
    public void changePhone(Long memberId, String phone) {
        Member member = getMemberByMemberId(memberId);

        Optional<Member> phoneCheck = memberRepository.findByPhone(phone);
        if (phoneCheck.isPresent()) {
            throw new InformationChangeException();
        }

        member.changePhone(phone);

//        updateValidation(member);

        memberRepository.update(memberId, member);
    }

    @Override
    public void changeNickname(Long memberId, String nickname) {
        Member member = getMemberByMemberId(memberId);
        Optional<Member> nicknameCheck = memberRepository.findByNickname(nickname);
        if (nicknameCheck.isPresent()) {
            throw new InformationChangeException();
        }


        if (member.getNicknameLastModifiedDate().isAfter(LocalDateTime.now().minusDays(30))) {
            throw new InformationChangeException();
        }

        member.changeNickname(nickname);

//        updateValidation(member);

        memberRepository.update(memberId, member);
    }

    @Override
    public void withdrawal(Long memberId, String loginPw) {
        Member member = getMemberByMemberId(memberId);

//        if (!member.getLoginPw().equals(loginPw)) {
//            throw new WithdrawalException();
//        }

        memberRepository.remove(memberId);

    }

    private Member getMemberByMemberId(Long memberId) {
        Optional<Member> findMember = memberRepository.findById(memberId);
        if (!findMember.isPresent()) {
            throw new InformationChangeException();
        }

        return findMember.get();
    }

    private void updateValidation(Member member) {
        MemberUpdateValidation validation = new MemberUpdateValidation();

        MemberRequest request = MemberRequest.builder()
                .loginPw(member.getLoginPw())
                .email(member.getEmail())
                .phone(member.getPhone())
                .nickname(member.getNickname())
                .build();

        List<InvalidResponse> responses = validation.validate(request);
        if (!responses.isEmpty()) {
            throw new InformationChangeException();
        }
    }
}
