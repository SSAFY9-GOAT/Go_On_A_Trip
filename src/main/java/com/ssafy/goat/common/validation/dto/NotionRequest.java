package com.ssafy.goat.common.validation.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class NotionRequest {

    private String title;
    private String content;

    @Builder
    public NotionRequest(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
