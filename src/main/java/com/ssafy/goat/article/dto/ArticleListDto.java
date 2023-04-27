package com.ssafy.goat.article.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class ArticleListDto {

    private Long articleId;
    private String title;
    private String createdDate;

    @Builder
    public ArticleListDto(Long articleId, String title, String createdDate) {
        this.articleId = articleId;
        this.title = title;
        this.createdDate = createdDate;
    }
}
