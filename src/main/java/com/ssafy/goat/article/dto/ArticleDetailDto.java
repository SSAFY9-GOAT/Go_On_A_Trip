package com.ssafy.goat.article.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class ArticleDetailDto {

    private Long articleId;
    private String title;
    private String content;
    private String createdDate;

    private Long memberId;
    private String nickname;

    @Builder
    public ArticleDetailDto(Long articleId, String title, String content, String createdDate, Long memberId, String nickname) {
        this.articleId = articleId;
        this.title = title;
        this.content = content;
        this.createdDate = createdDate;
        this.memberId = memberId;
        this.nickname = nickname;
    }
}
