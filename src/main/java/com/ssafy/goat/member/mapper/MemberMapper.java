package com.ssafy.goat.member.mapper;

import com.ssafy.goat.member.Member;
import com.ssafy.goat.member.dto.MemberAddDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;

@Mapper
public interface MemberMapper {
    int save(Member member);

    Optional<Member> findById(Long memberId);

    Optional<Member> findByLoginId(String loginId);

    Optional<Member> findByLoginIdAndLoginPw(@Param("loginId") String loginId, @Param("loginPw") String loginPw);

    Optional<Member> duplicateSignup(MemberAddDto memberAddDto);

    Optional<Member> findByEmail(String email);

    Optional<Member> findByPhone(String phone);

    Optional<Member> findByNickname(String nickname);

    int update(@Param("memberId") Long memberId, @Param("member") Member member);

    int remove(Long memberId);
}
