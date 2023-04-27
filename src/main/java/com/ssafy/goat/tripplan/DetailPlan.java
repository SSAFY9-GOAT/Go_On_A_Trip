package com.ssafy.goat.tripplan;

import com.ssafy.goat.attraction.AttractionInfo;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class DetailPlan {
    private Long id;
    private TripPlan tripPlan;
    private AttractionInfo attractionInfo;
    private int sequence;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;

    @Builder
    public DetailPlan(Long id, TripPlan tripPlan, AttractionInfo attractionInfo, int sequence, LocalDateTime createdDate, LocalDateTime lastModifiedDate) {
        this.id = id;
        this.tripPlan = tripPlan;
        this.attractionInfo = attractionInfo;
        this.sequence = sequence;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
    }
}
