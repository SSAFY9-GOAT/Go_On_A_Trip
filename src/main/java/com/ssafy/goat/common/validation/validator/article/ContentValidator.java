package com.ssafy.goat.common.validation.validator.article;

import common.validation.ValidationMessage;
import common.validation.dto.ArticleRequest;
import common.validation.dto.InvalidResponse;
import common.validation.validator.ArticleValidator;

import java.util.Collections;
import java.util.List;

public class ContentValidator implements ArticleValidator {

    private static final InvalidResponse INVALID_RESPONSE = new InvalidResponse("content", ValidationMessage.CONTENT);
    private static final int MAX_LENGTH = 1_000;

    @Override
    public List<InvalidResponse> validate(ArticleRequest request) {
        String content = request.getContent();

        if (content == null || isBlank(content)) {
            return Collections.singletonList(INVALID_RESPONSE);
        }

        if (isLength(content)) {
            return Collections.singletonList(INVALID_RESPONSE);
        }

        return Collections.emptyList();
    }

    private boolean isBlank(String title) {
        return title.trim().isEmpty();
    }

    private boolean isLength(String title) {
        return MAX_LENGTH < title.trim().length();
    }
}
