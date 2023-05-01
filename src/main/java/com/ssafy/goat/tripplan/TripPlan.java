package com.ssafy.goat.tripplan;

import com.ssafy.goat.member.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class TripPlan {

    private Long id;
    private Member member;
    private String title;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;

    @Builder
    public TripPlan(Long id, Member member, String title, LocalDateTime createdDate, LocalDateTime lastModifiedDate) {
        this.id = id;
        this.member = member;
        this.title = title;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
    }

    //== 비즈니스 로직 ==//
    public void changeTitle(String title) {
        this.title = title;
        this.lastModifiedDate = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "TripPlan{" +
                "id=" + id +
                ", member=" + member +
                ", title='" + title + '\'' +
                ", createdDate=" + createdDate +
                ", lastModifiedDate=" + lastModifiedDate +
                '}';
    }
}
