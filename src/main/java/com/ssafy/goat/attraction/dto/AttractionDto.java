package com.ssafy.goat.attraction.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class AttractionDto {

    private int id;
    private int contentTypeId;
    private String title;
    private String addr1;
    private String zipcode;
    private String firstImage;
    private Double latitude;
    private Double longitude;

    @Builder
    public AttractionDto(int id, int contentTypeId, String title, String addr1, String zipcode, String firstImage, Double latitude, Double longitude) {
        this.id = id;
        this.contentTypeId = contentTypeId;
        this.title = title;
        this.addr1 = addr1;
        this.zipcode = zipcode;
        this.firstImage = firstImage;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
