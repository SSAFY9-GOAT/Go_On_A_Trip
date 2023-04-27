package com.ssafy.goat.tripplan.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class DetailPlanDto {
    private String title;
    private Long detailPlanId;
    private Double latitude;
    private Double longitude;

    @Builder
    public DetailPlanDto(String title, Long detailPlanId, Double latitude, Double longitude) {
        this.title = title;
        this.detailPlanId = detailPlanId;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
