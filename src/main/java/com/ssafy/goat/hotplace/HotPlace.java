package com.ssafy.goat.hotplace;

import com.ssafy.goat.attraction.AttractionInfo;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import com.ssafy.goat.member.Member;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HotPlace {

    private Long id;
    private String name;
    private String desc;
    private int hit;
    private String visitedDate;
    private UploadFile uploadFile;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;

    private Integer contentTypeId;
    private Member member;
    private AttractionInfo attractionInfo;

    public HotPlace(Long id) {
        this.id = id;
    }

    @Builder
    public HotPlace(Long id, String name, String desc, int hit, String visitedDate, UploadFile uploadFile, LocalDateTime createdDate, LocalDateTime lastModifiedDate, Integer contentTypeId, Member member, AttractionInfo attractionInfo) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.hit = hit;
        this.visitedDate = visitedDate;
        this.uploadFile = uploadFile;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
        this.contentTypeId = contentTypeId;
        this.member = member;
        this.attractionInfo = attractionInfo;
    }

    //== 비즈니스 로직 ==//
    public void editContent(String name, String desc, String visitedDate) {
        this.name = name;
        this.desc = desc;
        this.visitedDate = visitedDate;
        this.lastModifiedDate = LocalDateTime.now();
    }

    public void increaseHit() {
        this.hit += 1;
    }
}
