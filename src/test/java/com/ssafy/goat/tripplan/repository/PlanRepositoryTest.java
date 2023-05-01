package com.ssafy.goat.tripplan.repository;

import com.ssafy.goat.attraction.AttractionInfo;
import com.ssafy.goat.member.Member;
import com.ssafy.goat.member.repository.MemberRepository;
import com.ssafy.goat.tripplan.DetailPlan;
import com.ssafy.goat.tripplan.TripPlan;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.ssafy.goat.member.Authority.CLIENT;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class PlanRepositoryTest {
    @Autowired
    private PlanRepository planRepository;// = PlanJdbcRepository.getPlanRepository();
    @Autowired
    private MemberRepository memberRepository;// = MemberJdbcRepository.getMemberRepository();
    private long memberId;

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
    }

    @AfterEach
    void afterEach() {
        planRepository.clear();
        memberRepository.clear();
    }

    @Test
    @DisplayName("여행계획 저장")
    void addTripPlan() {
        //given
        Optional<Member> findMember = memberRepository.findById(memberId);
        TripPlan tripPlan = TripPlan.builder()
                .title("trip plan title")
                .member(findMember.get())
                .build();
        //when
        int result = planRepository.save(tripPlan);

        //then
        assertThat(result).isEqualTo(1);
    }

    @Test
    @DisplayName("세부여행계획 저장")
    void addDetailPlan() {
        //given
        Optional<Member> findMember = memberRepository.findById(memberId);

        planRepository.save(TripPlan.builder()
                .title("trip plan title")
                .member(findMember.get())
                .build());
        List<TripPlan> findTripPlans = planRepository.findAllByMemberId(memberId);
        TripPlan tripPlan = findTripPlans.get(0);
        System.out.println(tripPlan.getId());
        DetailPlan detailPlan = DetailPlan.builder()
                .tripPlan(tripPlan)
                .attractionInfo(new AttractionInfo(125405))
                .build();

        //when
        int result = planRepository.save(detailPlan);

        //then
        assertThat(result).isEqualTo(1);
    }

    @Test
    @DisplayName("여행계획 업데이트")
    void updateTripPlan() {
        //given
        Optional<Member> findMember = memberRepository.findById(memberId);
        planRepository.save(TripPlan.builder()
                .title("trip plan title")
                .member(findMember.get())
                .build());
        List<TripPlan> findTripPlans = planRepository.findAllByMemberId(memberId);
        TripPlan tripPlan = findTripPlans.get(0);

        //when
        tripPlan.changeTitle("new title");
        int result = planRepository.updateTripPlan(tripPlan.getId(), tripPlan);

        //then
        assertThat(result).isEqualTo(1);
    }

    @Test
    @DisplayName("상세여행계획 삭제")
    void removeDetailPlan() {
        //given
        Optional<Member> findMember = memberRepository.findById(memberId);
        planRepository.save(TripPlan.builder()
                .title("trip plan title")
                .member(findMember.get())
                .build());
        List<TripPlan> findTripPlans = planRepository.findAllByMemberId(memberId);
        TripPlan tripPlan = findTripPlans.get(0);
        DetailPlan detailPlan = DetailPlan.builder()
                .tripPlan(tripPlan)
                .attractionInfo(new AttractionInfo(125405))
                .build();
        planRepository.save(detailPlan);
//        DetailPlan findDetailPlan = planRepository.findAllByTripPlanId(tripPlan.getId()).get(0);
//
//        when
//        int result = planRepository.removeDetailPlan(findDetailPlan.getId());
//
//        then
//        assertThat(result).isEqualTo(1);
    }

    @Test
    @DisplayName("여행계획 삭제")
    void removeTripPlan() {
        //given
        Optional<Member> findMember = memberRepository.findById(memberId);
        planRepository.save(TripPlan.builder()
                .title("trip plan title")
                .member(findMember.get())
                .build());
        List<TripPlan> findTripPlans = planRepository.findAllByMemberId(memberId);
        TripPlan tripPlan = findTripPlans.get(0);

        //when
        int result = planRepository.removeTripPlan(tripPlan.getId());

        //then
        assertThat(result).isEqualTo(1);
    }
}