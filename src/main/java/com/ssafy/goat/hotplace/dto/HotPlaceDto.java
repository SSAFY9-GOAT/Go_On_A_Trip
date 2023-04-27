package com.ssafy.goat.hotplace.dto;

import com.ssafy.goat.attraction.AttractionInfo;
import com.ssafy.goat.hotplace.UploadFile;
import lombok.Builder;
import lombok.Data;
import com.ssafy.goat.member.Member;

@Data
public class HotPlaceDto {

    private Long id;
    private String name;
    private String desc;
    private int hit;
    private String visitedDate;
    private UploadFile uploadFile;
    private String createdDate;

    private Integer contentTypeId;
    private Member member;
    private AttractionInfo attractionInfo;

    @Builder
    public HotPlaceDto(Long id, String name, String desc, int hit, String visitedDate, UploadFile uploadFile, String createdDate, Integer contentTypeId, Member member, AttractionInfo attractionInfo) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.hit = hit;
        this.visitedDate = visitedDate;
        this.uploadFile = uploadFile;
        this.createdDate = createdDate;
        this.contentTypeId = contentTypeId;
        this.member = member;
        this.attractionInfo = attractionInfo;
    }
}
