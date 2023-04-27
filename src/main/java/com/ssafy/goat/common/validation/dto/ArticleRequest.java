package com.ssafy.goat.common.validation.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ArticleRequest {

    private String title;
    private String content;

    @Builder
    public ArticleRequest(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
