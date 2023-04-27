package com.ssafy.goat.tripplan.service;

import tripplan.dto.*;

import java.util.List;

public interface PlanService {

    int addTripPlan(Long memberId, String title);

    int addDetailPlan(Long memberId, Long tripPlanId, int contentId);


    List<PlanListDto> searchPlans(PlanSearch condition, int pageNum, int amount);

    TripPlanDto showPlan(Long tripPlanId);

    int getTotalCount();

    Long getTripPlanId(Long memberId);

    int updateTripPlan(Long memberId, Long tripPlanId, String title);

    int removeTripPlan(Long memberId, Long tripPlanId);

    int removeDetailPlan(Long memberId, Long detailPlanId);
}
