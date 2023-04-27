package com.ssafy.goat.common.validation.validator.article;

import common.validation.ValidationMessage;
import common.validation.dto.ArticleRequest;
import common.validation.dto.InvalidResponse;
import common.validation.validator.ArticleValidator;

import java.util.Collections;
import java.util.List;

public class TitleValidator implements ArticleValidator {

    private static final InvalidResponse INVALID_RESPONSE = new InvalidResponse("title", ValidationMessage.TITLE);
    private static final int MAX_LENGTH = 100;

    @Override
    public List<InvalidResponse> validate(ArticleRequest request) {
        String title = request.getTitle();

        if (title == null || isBlank(title)) {
            return Collections.singletonList(INVALID_RESPONSE);
        }

        if (isLength(title)) {
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
