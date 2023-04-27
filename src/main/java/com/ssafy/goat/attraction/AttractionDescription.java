package com.ssafy.goat.attraction;

import lombok.Builder;
import lombok.Getter;

@Getter
public class AttractionDescription {

    private AttractionInfo attractionInfo;
    private String homepage;
    private String overview;
    private String telname;

    @Builder
    public AttractionDescription(AttractionInfo attractionInfo, String homepage, String overview, String telname) {
        this.attractionInfo = attractionInfo;
        this.homepage = homepage;
        this.overview = overview;
        this.telname = telname;
    }
}
