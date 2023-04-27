package com.ssafy.goat.tripplan.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class TripPlanDto {
    private Long tripPlanId;
    private String title;
    private List<DetailPlanDto> detailPlans;

    @Builder
    public TripPlanDto(Long tripPlanId, String title, List<DetailPlanDto> detailPlans) {
        this.tripPlanId = tripPlanId;
        this.title = title;
        this.detailPlans = detailPlans;
    }
}
