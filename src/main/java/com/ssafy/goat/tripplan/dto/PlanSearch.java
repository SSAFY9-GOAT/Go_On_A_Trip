package com.ssafy.goat.tripplan.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class PlanSearch {
    private String condition;

    @Builder
    public PlanSearch(String condition) {
        this.condition = condition;
    }
}
