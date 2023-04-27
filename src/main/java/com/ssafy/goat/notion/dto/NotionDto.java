package com.ssafy.goat.notion.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class NotionDto {

    private Long id;
    private String title;
    private String content;
    private boolean top;
    private String createdDate;

    @Builder
    public NotionDto(Long id, String title, String content, boolean top, LocalDateTime createdDate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.top = top;
        this.createdDate = dateFormat(createdDate);
    }

    private String dateFormat(LocalDateTime dateTime) {
        if (dateTime == null) {
            return null;
        }
        return dateTime.format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
    }
}
