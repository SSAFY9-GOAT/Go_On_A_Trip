package com.ssafy.goat.attraction.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class AttractionSearch {

    private Integer sidoCode;
    private Integer gugunCode;
    private Integer contentTypeId;

    @Builder
    public AttractionSearch(Integer sidoCode, Integer gugunCode, Integer contentTypeId) {
        this.sidoCode = sidoCode;
        this.gugunCode = gugunCode;
        this.contentTypeId = contentTypeId;
    }
}
