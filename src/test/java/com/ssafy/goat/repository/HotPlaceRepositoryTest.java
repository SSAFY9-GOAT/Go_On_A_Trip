package com.ssafy.goat.repository;

import com.ssafy.goat.attraction.AttractionInfo;
import com.ssafy.goat.attraction.repository.AttractionJdbcRepository;
import com.ssafy.goat.attraction.repository.AttractionRepository;
import com.ssafy.goat.hotplace.HotPlace;
import com.ssafy.goat.hotplace.UploadFile;
import com.ssafy.goat.member.Member;
import com.ssafy.goat.member.repository.MemberJdbcRepository;
import com.ssafy.goat.member.repository.MemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static com.ssafy.goat.member.Authority.CLIENT;
import static org.assertj.core.api.Assertions.assertThat;

class HotPlaceRepositoryTest {

    private final HotPlaceRepository hotPlaceRepository = HotPlaceJdbcRepository.getHotPlaceRepository();
    private final MemberRepository memberRepository = MemberJdbcRepository.getMemberRepository();
    private final AttractionRepository attractionRepository = AttractionJdbcRepository.getAttractionRepository();
    private Long memberId;
    private Long hotPlaceId;

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
        Member member = memberRepository.findByLoginId("ssafy").get();
        memberId = member.getId();

        HotPlace hotPlace = HotPlace.builder()
                .name("핫플레이스 이름")
                .desc("핫플레이스 설명")
                .visitedDate("2020-01-01")
                .uploadFile(
                        UploadFile.builder()
                                .uploadFileName("uploadFileName.png")
                                .storeFileName("storeFileName.png")
                                .build()
                )
                .contentTypeId(11)
                .member(member)
                .attractionInfo(new AttractionInfo(125405))
                .build();
        hotPlaceRepository.save(hotPlace);
        List<HotPlace> findHotPlace = hotPlaceRepository.findAll();
        hotPlaceId = findHotPlace.get(0).getId();
    }

    @AfterEach
    void afterEach() {
        hotPlaceRepository.clear();
        memberRepository.clear();
    }

    @Test
    @DisplayName("핫플레이스 저장")
    void save() {
        //given
        Optional<Member> fineMember = memberRepository.findById(memberId);
        Optional<AttractionInfo> findAttractionInfo = attractionRepository.findById(125405);
        HotPlace hotPlace = HotPlace.builder()
                .name("핫플레이스 이름")
                .desc("핫플레이스 설명")
                .visitedDate("2020-01-01")
                .uploadFile(
                        UploadFile.builder()
                                .uploadFileName("uploadFileName.png")
                                .storeFileName("storeFileName.png")
                                .build()
                )
                .contentTypeId(11)
                .member(fineMember.get())
                .attractionInfo(findAttractionInfo.get())
                .build();

        //when
        int result = hotPlaceRepository.save(hotPlace);

        //then
        assertThat(result).isEqualTo(1);
    }

    @Test
    @DisplayName("핫플레이스 업데이트")
    void update() {
        //given
        HotPlace hotPlace = hotPlaceRepository.findById(hotPlaceId).get();
        hotPlace.editContent("new hot place name", "new hot place desc", "2023-01-01");

        //when
        int result = hotPlaceRepository.update(hotPlace);

        //then
        assertThat(result).isEqualTo(1);
    }

    @Test
    @DisplayName("핫플레이스 조회수 증가")
    void updateHit() {
        //given
        HotPlace hotPlace = hotPlaceRepository.findById(hotPlaceId).get();
        hotPlace.increaseHit();

        //when
        int result = hotPlaceRepository.updateHit(hotPlace);

        //then
        assertThat(result).isEqualTo(1);
    }

    @Test
    @DisplayName("핫플레이스 삭제")
    void remove() {
        //given
        //when
        int result = hotPlaceRepository.remove(hotPlaceId);

        //then
        assertThat(result).isEqualTo(1);
    }
}