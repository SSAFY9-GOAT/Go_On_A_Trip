package com.ssafy.goat.attraction;

import lombok.Builder;
import lombok.Getter;

@Getter
public class AttractionDetail {

    private AttractionInfo attractionInfo;
    private String cat1;
    private String cat2;
    private String cat3;
    private String created_time;
    private String modifierd_time;
    private String booktour;

    @Builder
    public AttractionDetail(AttractionInfo attractionInfo, String cat1, String cat2, String cat3, String created_time, String modifierd_time, String booktour) {
        this.attractionInfo = attractionInfo;
        this.cat1 = cat1;
        this.cat2 = cat2;
        this.cat3 = cat3;
        this.created_time = created_time;
        this.modifierd_time = modifierd_time;
        this.booktour = booktour;
    }
}
