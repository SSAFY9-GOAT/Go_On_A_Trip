package com.ssafy.goat.tripplan.dto;

import com.ssafy.goat.member.Member;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PlanDto {

    private Long id;
    private String title;
    private LocalDateTime createdDate;
    private Member member;

    @Builder
    public PlanDto(Long id, String title, LocalDateTime createdDate, Member member) {
        this.id = id;
        this.title = title;
        this.createdDate = createdDate;
        this.member = member;
    }
}
