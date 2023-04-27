package com.ssafy.goat.tripplan.service;

import attraction.AttractionInfo;
import attraction.repository.AttractionJdbcRepository;
import attraction.repository.AttractionRepository;
import common.exception.PlanException;
import member.Member;
import member.repository.MemberJdbcRepository;
import member.repository.MemberRepository;
import tripplan.DetailPlan;
import tripplan.TripPlan;
import tripplan.dto.PlanListDto;
import tripplan.dto.PlanSearch;
import tripplan.dto.TripPlanDto;
import tripplan.repository.PlanJdbcRepository;
import tripplan.repository.PlanRepository;

import java.util.List;
import java.util.Optional;

public class PlanServiceImpl implements PlanService {
    private static final PlanService planService = new PlanServiceImpl();
    private final PlanRepository planRepository;
    private final MemberRepository memberRepository;
    private final AttractionRepository attractionRepository;

    private PlanServiceImpl() {
        planRepository = PlanJdbcRepository.getPlanRepository();
        memberRepository = MemberJdbcRepository.getMemberRepository();
        attractionRepository = AttractionJdbcRepository.getAttractionRepository();
    }

    public static PlanService getPlanService() {
        return planService;
    }

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
