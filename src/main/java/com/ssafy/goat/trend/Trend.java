package com.ssafy.goat.trend;

import com.ssafy.goat.attraction.AttractionInfo;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Trend {

    private Long id;
    private int teenage;
    private int twenty;
    private int thirty;
    private int male;
    private int female;

    private AttractionInfo content;

    @Builder
    public Trend(Long id, int teenage, int twenty, int thirty, int male, int female, AttractionInfo content) {
        this.id = id;
        this.teenage = teenage;
        this.twenty = twenty;
        this.thirty = thirty;
        this.male = male;
        this.female = female;
        this.content = content;
    }

    //== 비즈니스 로직 ==//
    public void increaseTeenage() {
        this.teenage += 1;
    }

    public void increaseTwenty() {
        this.twenty += 1;
    }

    public void increaseThirty() {
        this.thirty += 1;
    }

    public void increaseMale() {
        this.male += 1;
    }

    public void increaseFemale() {
        this.female += 1;
    }
}
