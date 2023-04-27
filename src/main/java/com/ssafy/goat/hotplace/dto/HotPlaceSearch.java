package com.ssafy.goat.hotplace.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class HotPlaceSearch {

    private String name;
    private int sortCondition;

    @Builder
    public HotPlaceSearch(String name, int sortCondition) {
        this.name = name;
        this.sortCondition = sortCondition;
    }
}
