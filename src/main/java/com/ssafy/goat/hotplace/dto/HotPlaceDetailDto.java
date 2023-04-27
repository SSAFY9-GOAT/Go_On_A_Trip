package com.ssafy.goat.hotplace.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class HotPlaceDetailDto {

    private Long hotPlaceId;
    private String visitedDate;
    private String name;
    private String desc;
    private String storeFileName;

    private Long memberId;
    private String nickname;

    private String title;
    private String firstImage;
    private String addr1;
    private String zipcode;
    private Double latitude;
    private Double longitude;

    @Builder
    public HotPlaceDetailDto(Long hotPlaceId, String visitedDate, String name, String desc, String storeFileName, Long memberId, String nickname, String title, String firstImage, String addr1, String zipcode, Double latitude, Double longitude) {
        this.hotPlaceId = hotPlaceId;
        this.visitedDate = visitedDate;
        this.name = name;
        this.desc = desc;
        this.storeFileName = storeFileName;
        this.memberId = memberId;
        this.nickname = nickname;
        this.title = title;
        this.firstImage = firstImage;
        this.addr1 = addr1;
        this.zipcode = zipcode;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
