package com.ssafy.goat.article.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class ArticleSearch {

    private String condition;
    private int sortCondition;

    @Builder
    public ArticleSearch(String condition, int sortCondition) {
        this.condition = condition;
        this.sortCondition = sortCondition;
    }
}
