package com.ssafy.goat.common.validation.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class HotPlaceRequest {
    private String name;
    private String desc;
    private String visitedDate;

    @Builder
    public HotPlaceRequest(String name, String desc, String visitedDate) {
        this.name = name;
        this.desc = desc;
        this.visitedDate = visitedDate;
    }
}
