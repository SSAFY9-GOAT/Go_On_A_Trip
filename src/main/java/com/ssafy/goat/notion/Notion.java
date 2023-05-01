package com.ssafy.goat.notion;

import lombok.Builder;
import lombok.Getter;
import com.ssafy.goat.member.Member;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class Notion {

    private Long id;
    private String title;
    private String content;
    private int hit;
    private boolean top;
    private Member createdBy;
    private Member lastModifiedBy;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;

    @Builder
    public Notion(Long id, String title, String content, int hit, boolean top, Member createdBy, Member lastModifiedBy, LocalDateTime createdDate, LocalDateTime lastModifiedDate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.hit = hit;
        this.top = top;
        this.createdBy = createdBy;
        this.lastModifiedBy = lastModifiedBy;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
    }


    //== 비즈니스 로직 ==//
    public void edit(String title, String content, Member member) {
        this.title = title;
        this.content = content;
        this.lastModifiedBy = member;
        this.lastModifiedDate = LocalDateTime.now();
    }
}
