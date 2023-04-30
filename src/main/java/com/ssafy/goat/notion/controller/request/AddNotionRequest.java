package com.ssafy.goat.notion.controller.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class AddNotionRequest {

    @NotEmpty
    @Size(max = 100)
    private String title;
    @NotEmpty
    @Size(max = 1000)
    private String content;
}
