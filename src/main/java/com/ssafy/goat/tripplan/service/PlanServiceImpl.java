package com.ssafy.goat.tripplan.service;

import com.ssafy.goat.attraction.AttractionInfo;
import com.ssafy.goat.attraction.repository.AttractionJdbcRepository;
import com.ssafy.goat.attraction.repository.AttractionRepository;
import com.ssafy.goat.common.exception.PlanException;
import com.ssafy.goat.member.Member;
import com.ssafy.goat.member.repository.MemberRepository;
import com.ssafy.goat.tripplan.DetailPlan;
import com.ssafy.goat.tripplan.TripPlan;
import com.ssafy.goat.tripplan.dto.PlanListDto;
import com.ssafy.goat.tripplan.dto.PlanSearch;
import com.ssafy.goat.tripplan.dto.TripPlanDto;
import com.ssafy.goat.tripplan.repository.PlanJdbcRepository;
import com.ssafy.goat.tripplan.repository.PlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlanServiceImpl implements PlanService {

    private final PlanRepository planRepository;
    private final MemberRepository memberRepository;
    private final AttractionRepository attractionRepository;

    @Override
    public int addTripPlan(Long memberId, String title) {
        Optional<Member> findMember = memberRepository.findById(memberId);
        if (!findMember.isPresent()) {
            throw new PlanException();
        }

        TripPlan tripPlan = TripPlan.builder()
                .title(title)
                .member(findMember.get())
                .build();

        return planRepository.save(tripPlan);
    }

    @Override
    public int addDetailPlan(Long memberId, Long tripPlanId, int contentId) {
        Optional<TripPlan> findTripPlan = planRepository.findById(tripPlanId);
        if (!findTripPlan.isPresent()) {
            throw new PlanException();
        }

        Optional<AttractionInfo> findAttractionInfo = attractionRepository.findById(contentId);
        if (!findAttractionInfo.isPresent()) {
            throw new PlanException();
        }

        TripPlan tripPlan = findTripPlan.get();
        if (!tripPlan.getMember().getId().equals(memberId)) {
            throw new PlanException();
        }

        DetailPlan detailPlan = DetailPlan.builder()
                .tripPlan(tripPlan)
                .attractionInfo(findAttractionInfo.get())
                .build();

        return planRepository.save(detailPlan);
    }

    @Override
    public List<PlanListDto> searchPlans(PlanSearch condition, int pageNum, int amount) {
        return planRepository.findByCondition(condition, pageNum, amount);
    }

//    @Override
//    public List<DetailPlanDto> showPlan(Long tripPlanId) {
//        Optional<TripPlan> findTripPlan = planRepository.findById(tripPlanId);
//        if (!findTripPlan.isPresent()) {
//            throw new PlanException();
//        }
//
//        planRepository.findAllByTripPlanId(tripPlanId);
//        return null;
//    }

    @Override
    public TripPlanDto showPlan(Long tripPlanId) {
        return planRepository.findAllByTripPlanId(tripPlanId);
    }

    @Override
    public int getTotalCount() {
        return planRepository.findTotalCount();
    }

    @Override
    public Long getTripPlanId(Long memberId) {
        return planRepository.findByMemberId(memberId);
    }

    @Override
    public int updateTripPlan(Long memberId, Long tripPlanId, String title) {
        Optional<TripPlan> findTripPlan = planRepository.findById(tripPlanId);
        if (!findTripPlan.isPresent()) {
            throw new PlanException();
        }

        TripPlan tripPlan = findTripPlan.get();
        if (!tripPlan.getMember().getId().equals(memberId)) {
            throw new PlanException();
        }

        tripPlan.changeTitle(title);

        return planRepository.updateTripPlan(tripPlanId, tripPlan);
    }

    @Override
    public int removeTripPlan(Long memberId, Long tripPlanId) {
        Optional<TripPlan> findTripPlan = planRepository.findById(tripPlanId);
        if (!findTripPlan.isPresent()) {
            throw new PlanException();
        }

        TripPlan tripPlan = findTripPlan.get();
        if (isNotMine(tripPlan, memberId)) {
            throw new PlanException();
        }

        return planRepository.removeTripPlan(tripPlanId);
    }

    @Override
    public int removeDetailPlan(Long memberId, Long detailPlanId) {
        Optional<DetailPlan> findDetailPlan = planRepository.findByDetailPlanId(detailPlanId);
        if (!findDetailPlan.isPresent()) {
            throw new PlanException();
        }

        DetailPlan detailPlan = findDetailPlan.get();
        if (isNotMine(detailPlan, memberId)) {
            throw new PlanException();
        }

        return planRepository.removeDetailPlan(detailPlanId);
    }

    private boolean isNotMine(TripPlan tripPlan, Long memberId) {
        return !tripPlan.getMember().getId().equals(memberId);
    }

    private boolean isNotMine(DetailPlan detailPlan, Long memberId) {
        return !detailPlan.getTripPlan().getMember().getId().equals(memberId);
    }
}
