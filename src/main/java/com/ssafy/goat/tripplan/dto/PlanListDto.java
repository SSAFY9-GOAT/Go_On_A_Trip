package com.ssafy.goat.tripplan.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class PlanListDto {
    private Long tripPlanId;
    private String title;
    private String nickname;
    private String createdDate;

    @Builder
    public PlanListDto(Long tripPlanId, String title, String nickname, String createdDate) {
        this.tripPlanId = tripPlanId;
        this.title = title;
        this.nickname = nickname;
        this.createdDate = createdDate;
    }
}
