package com.ssafy.goat.notion.service;

import com.ssafy.goat.common.exception.NotionException;
import com.ssafy.goat.member.Member;
import com.ssafy.goat.member.repository.MemberJdbcRepository;
import com.ssafy.goat.member.repository.MemberRepository;
import com.ssafy.goat.notion.Notion;
import com.ssafy.goat.notion.dto.NotionDto;
import com.ssafy.goat.notion.repository.NotionJdbcRepository;
import com.ssafy.goat.notion.repository.NotionRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.ssafy.goat.member.Authority.ADMIN;
import static com.ssafy.goat.member.Authority.CLIENT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NotionServiceTest {

    private  NotionService notionService;//= NotionServiceImpl.getNotionService();
    private  NotionRepository notionRepository = NotionJdbcRepository.getNotionRepository();
    private  MemberRepository memberRepository = MemberJdbcRepository.getMemberRepository();

    private Long adminId;
    private Long clientId;
    private Long notionId;

    @BeforeEach
    void beforeEach() {
        memberRepository.save(Member.builder()
                .loginId("admin")
                .loginPw("12345678")
                .username("김싸피")
                .email("ssafy@ssafy.com")
                .phone("01012345678")
                .birth("010101")
                .gender("1")
                .nickname("광주5반")
                .authority(ADMIN)
                .build());
        Member admin = memberRepository.findByLoginId("admin").get();
        adminId = admin.getId();
        memberRepository.save(Member.builder()
                .loginId("client")
                .loginPw("12345678")
                .username("김싸피")
                .email("ssafy@ssafy.com")
                .phone("01012345678")
                .birth("010101")
                .gender("1")
                .nickname("광주5반")
                .authority(CLIENT)
                .build());
        clientId = memberRepository.findByLoginId("client").get().getId();

        notionRepository.save(Notion.builder()
                .title("notion title")
                .content("notion content")
                .createdBy(admin)
                .lastModifiedBy(admin)
                .build());
        notionId = notionRepository.findAll().get(0).getId();
    }

    @AfterEach
    void afterEach() {
        notionRepository.clear();
        memberRepository.clear();
    }

    @Test
    @DisplayName("공지사항 등록")
    void addNotion() {
        //given
        NotionDto notionDto = NotionDto.builder()
                .title("notion title")
                .content("notion content")
                .top(true)
                .build();

        //when
        int count = notionService.addNotion(adminId, notionDto);

        //then
        assertThat(count).isEqualTo(1);
    }

    @Test
    @DisplayName("공지사항 등록#미등록 회원")
    void addNotion_exception_member() {
        //given
        NotionDto notionDto = NotionDto.builder()
                .title("notion title")
                .content("notion content")
                .top(true)
                .build();

        //when
        //then
        assertThatThrownBy(() -> notionService.addNotion(0L, notionDto))
                .isInstanceOf(NotionException.class);
    }

    @Test
    @DisplayName("공지사항 등록#관리자 권한")
    void addNotion_exception_admin() {
        //given
        NotionDto notionDto = NotionDto.builder()
                .title("notion title")
                .content("notion content")
                .top(true)
                .build();

        //when
        //then
        assertThatThrownBy(() -> notionService.addNotion(clientId, notionDto))
                .isInstanceOf(NotionException.class);
    }

    @Test
    @DisplayName("공지사항 수정")
    void editNotion() {
        //given
        NotionDto notionDto = NotionDto.builder()
                .title("new notion title")
                .content("new notion content")
                .build();

        //when
        int count = notionService.editNotion(notionId, adminId, notionDto);

        //then
        assertThat(count).isEqualTo(1);
    }

    @Test
    @DisplayName("공지사항 수정#미등록 회원")
    void editNotion_exception_member() {
        //given
        NotionDto notionDto = NotionDto.builder()
                .title("new notion title")
                .content("new notion content")
                .build();

        //when
        //then
        assertThatThrownBy(() -> notionService.editNotion(notionId, 0L, notionDto))
                .isInstanceOf(NotionException.class);
    }

    @Test
    @DisplayName("공지사항 수정#관리자 권한")
    void editNotion_exception_admin() {
        //given
        NotionDto notionDto = NotionDto.builder()
                .title("new notion title")
                .content("new notion content")
                .build();

        //when
        //then
        assertThatThrownBy(() -> notionService.editNotion(notionId, clientId, notionDto))
                .isInstanceOf(NotionException.class);
    }
    
    @Test
    @DisplayName("공지사항 수정#미등록 공지사항")
    void editNotion_exception_notion() {
        //given
        NotionDto notionDto = NotionDto.builder()
                .title("new notion title")
                .content("new notion content")
                .build();

        //when
        //then
        assertThatThrownBy(() -> notionService.editNotion(0L, adminId, notionDto))
                .isInstanceOf(NotionException.class);
    }

    @Test
    @DisplayName("공지사항 삭제")
    void removeNotion() {
        //given
        //when
        int count = notionService.removeNotion(notionId, adminId);

        //then
        assertThat(count).isEqualTo(1);
    }
    
    @Test
    @DisplayName("공지사항 삭제#미등록 회원")
    void removeNotion_exception_member() {
        //given
        //when
        //then
        assertThatThrownBy(() -> notionService.removeNotion(notionId, 0L))
                .isInstanceOf(NotionException.class);
    }
    
    @Test
    @DisplayName("공지사항 삭제#관리자 권한")
    void removeNotion_exception_admin() {
        //given
        //when
        //then
        assertThatThrownBy(() -> notionService.removeNotion(notionId, clientId))
                .isInstanceOf(NotionException.class);
    }

    @Test
    @DisplayName("공지사항 삭제#미등록 공지사항")
    void removeNotion_exception_notion() {
        //given
        //when
        //then
        assertThatThrownBy(() -> notionService.removeNotion(0L, clientId))
                .isInstanceOf(NotionException.class);
    }
}