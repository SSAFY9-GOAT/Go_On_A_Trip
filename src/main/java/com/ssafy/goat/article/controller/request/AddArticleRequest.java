package com.ssafy.goat.article.controller.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class AddArticleRequest {

    @NotEmpty
    @Size(max = 100)
    private String title;
    @NotEmpty
    @Size(max = 1000)
    private String content;
}
