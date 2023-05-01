package com.ssafy.goat.attraction;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AttractionInfo {

    private Integer id;
    private Integer contentTypeId;
    private String title;
    private String addr1;
    private String addr2;
    private String zipcode;
    private String tel;
    private String firstImage;
    private String firstImage2;
    private Integer readCount;
    private Sido sido;
    private Gugun gugun;
    private Double latitude;
    private Double longitude;
    private String mlevel;

    public AttractionInfo(Integer id) {
        this.id = id;
    }

    @Builder
    public AttractionInfo(Integer id, Integer contentTypeId, String title, String addr1, String addr2, String zipcode, String tel, String firstImage, String firstImage2, Integer readCount, Sido sido, Gugun gugun, Double latitude, Double longitude, String mlevel) {
        this.id = id;
        this.contentTypeId = contentTypeId;
        this.title = title;
        this.addr1 = addr1;
        this.addr2 = addr2;
        this.zipcode = zipcode;
        this.tel = tel;
        this.firstImage = firstImage;
        this.firstImage2 = firstImage2;
        this.readCount = readCount;
        this.sido = sido;
        this.gugun = gugun;
        this.latitude = latitude;
        this.longitude = longitude;
        this.mlevel = mlevel;
    }
}
