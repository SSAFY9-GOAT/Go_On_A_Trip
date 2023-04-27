package com.ssafy.goat.article;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import member.Member;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Article {

    private Long id;
    private String title;
    private String content;
    private int hit;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;

    private Member member;

    public Article(Long id) {
        this.id = id;
    }

    @Builder
    public Article(Long id, String title, String content, int hit, LocalDateTime createdDate, LocalDateTime lastModifiedDate, Member member) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.hit = hit;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
        this.member = member;
    }

    //== 비즈니스 로직 ==//
    public void editArticle(String title, String content) {
        this.title = title;
        this.content = content;
        this.lastModifiedDate = LocalDateTime.now();
    }

    public void increaseHit() {
        this.hit += 1;
    }
}
