package com.ssafy.goat.trend.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class TrendViewDto {

    private String title;
    private String firstImage;
    private String addr1;
    private String zipcode;

    @Builder
    public TrendViewDto(String title, String firstImage, String addr1, String zipcode) {
        this.title = title;
        this.firstImage = firstImage;
        this.addr1 = addr1;
        this.zipcode = zipcode;
    }
}
