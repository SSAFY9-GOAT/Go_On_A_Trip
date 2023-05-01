package com.ssafy.goat.member.repository;

import com.ssafy.goat.member.Member;
import com.ssafy.goat.member.dto.MemberAddDto;
import com.ssafy.goat.member.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberMybatisRepository implements MemberRepository {

    private final MemberMapper mapper;

    @Override
    public int save(Member member) {
        return mapper.save(member);
    }

    @Override
    public Optional<Member> findById(Long memberId) {
        return mapper.findById(memberId);
    }

    @Override
    public Optional<Member> findByLoginId(String loginId) {
        return mapper.findByLoginId(loginId);
    }

    @Override
    public Optional<Member> findByLoginIdAndLoginPw(String loginId, String loginPw) {
        return mapper.findByLoginIdAndLoginPw(loginId, loginPw);
    }

    @Override
    public Optional<Member> duplicateSignup(MemberAddDto memberAddDto) {
        return mapper.duplicateSignup(memberAddDto);
    }

    @Override
    public Optional<Member> findByEmail(String email) {
        return mapper.findByEmail(email);
    }

    @Override
    public Optional<Member> findByPhone(String phone) {
        return mapper.findByPhone(phone);
    }

    @Override
    public Optional<Member> findByNickname(String nickname) {
        return mapper.findByNickname(nickname);
    }

    @Override
    public int update(Long memberId, Member member) {
        return mapper.update(memberId,member);
    }

    @Override
    public int remove(Long memberId) {
        return mapper.remove(memberId);
    }

    @Override
    public void clear() {

    }
}
