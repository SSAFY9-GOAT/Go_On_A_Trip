package com.ssafy.goat.notion.repository;

import com.ssafy.goat.member.Member;
import com.ssafy.goat.member.repository.MemberJdbcRepository;
import com.ssafy.goat.member.repository.MemberRepository;
import com.ssafy.goat.notion.Notion;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static com.ssafy.goat.member.Authority.CLIENT;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class NotionRepositoryTest {

    @Autowired
    private  NotionRepository notionRepository;
    @Autowired
    private  MemberRepository memberRepository ;//= MemberJdbcRepository.getMemberRepository();

    private Member member;

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
                .authority(CLIENT)
                .build());
        member = memberRepository.findByLoginId("admin").get();
    }

//    @AfterEach
//    void afterEach() {
//        notionRepository.clear();
//        memberRepository.clear();
//    }

    @Test
    @DisplayName("공지사항 저장")
    void save() {
        //given
        Notion notion = Notion.builder()
                .title("notion title")
                .content("notion content")
                .createdBy(member)
                .lastModifiedBy(member)
                .build();

        //when
        int count = notionRepository.save(notion);

        //then
        assertThat(count).isEqualTo(1);
    }

    @Test
    @DisplayName("공지사항 수정")
    void update() {
        //given
        notionRepository.save(Notion.builder()
                .title("notion title")
                .content("notion content")
                .createdBy(member)
                .lastModifiedBy(member)
                .build());
        Notion notion = notionRepository.findAll().get(0);
        notion.edit("new notion title", "new notion content", member);

        //when
        int count = notionRepository.update(notion);

        //then
        assertThat(count).isEqualTo(1);
    }

    @Test
    @DisplayName("공지사항 삭제")
    void remove() {
        //given
        notionRepository.save(Notion.builder()
                .title("notion title")
                .content("notion content")
                .createdBy(member)
                .lastModifiedBy(member)
                .build());
        Notion notion = notionRepository.findAll().get(0);

        //when
        int count = notionRepository.remove(notion.getId());

        //then
        assertThat(count).isEqualTo(1);
    }
}