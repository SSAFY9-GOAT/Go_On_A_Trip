package com.ssafy.goat.member.repository;

import com.ssafy.goat.member.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static com.ssafy.goat.member.Authority.CLIENT;
import static org.assertj.core.api.Assertions.assertThat;

class MemberRepositoryTest {

    @Autowired
    private  MemberRepository memberRepository;

    @BeforeEach
    void beforeEach() {
        memberRepository.save(Member.builder()
                .loginId("ssafy")
                .loginPw("12345678")
                .username("김싸피")
                .email("ssafy@ssafy.com")
                .phone("01012345678")
                .birth("010101")
                .gender("1")
                .nickname("광주5반")
                .authority(CLIENT)
                .build());
    }

    @AfterEach
    void afterEach() {
        memberRepository.clear();
    }

    @Test
    @DisplayName("회원저장")
    void save() {
        //given
        Member member = Member.builder()
                .loginId("ssafy")
                .loginPw("12345678")
                .username("김싸피")
                .email("ssafy@ssafy.com")
                .phone("01012345678")
                .birth("010101")
                .gender("1")
                .nickname("광주5반")
                .authority(CLIENT)
                .build();

        //when
        int count = memberRepository.save(member);

        //then
        assertThat(count).isEqualTo(1);
    }

    @Test
    @DisplayName("PK로 조회")
    void findById() {
        //given
        Member member = memberRepository.findByLoginId("ssafy").get();

        //when
        Optional<Member> findMember = memberRepository.findById(member.getId());

        //then
        assertThat(findMember).isPresent();
    }

    @Test
    @DisplayName("로그인 아이디로 조회")
    void findByLoginId() {
        //given
        String loginId = "ssafy";

        //when
        Optional<Member> findMember = memberRepository.findByLoginId(loginId);

        //then
        assertThat(findMember).isPresent();
    }

    @Test
    @DisplayName("이메일로 조회")
    void findByEmail() {
        //given
        String email = "ssafy@ssafy.com";

        //when
        Optional<Member> findMember = memberRepository.findByEmail(email);

        //then
        assertThat(findMember).isPresent();
    }

    @Test
    @DisplayName("연락처로 조회")
    void findByPhone() {
        //given
        String phone = "01012345678";

        //when
        Optional<Member> findMember = memberRepository.findByPhone(phone);

        //then
        assertThat(findMember).isPresent();
    }

    @Test
    @DisplayName("닉네임으로 조회")
    void findByNickname() {
        //given
        String nickname = "광주5반";

        //when
        Optional<Member> findMember = memberRepository.findByNickname(nickname);

        //then
        assertThat(findMember).isPresent();
    }

    @Test
    @DisplayName("회원업데이트")
    void update() {
        //given
        Member member = memberRepository.findByLoginId("ssafy").get();
        member.changeNickname("SSAFY9기");
        //when
        int count = memberRepository.update(member.getId(), member);

        //then
        assertThat(count).isEqualTo(1);
    }

    @Test
    @DisplayName("회원삭제")
    void remove() {
        //given
        Member member = memberRepository.findByLoginId("ssafy").get();

        //when
        int count = memberRepository.remove(member.getId());

        //then
        assertThat(count).isEqualTo(1);
    }
}