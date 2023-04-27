package com.ssafy.goat.common.validation;

import common.validation.dto.ArticleRequest;
import common.validation.dto.InvalidResponse;
import common.validation.validator.ArticleValidator;
import common.validation.validator.article.ContentValidator;
import common.validation.validator.article.TitleValidator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ArticleValidation {

    private final List<ArticleValidator> validators;

    public ArticleValidation() {
        validators = new ArrayList<>();
        validators.add(new TitleValidator());
        validators.add(new ContentValidator());
    }

    public List<InvalidResponse> validate(ArticleRequest request) {
        if (request == null) {
            return Collections.singletonList(new InvalidResponse("request", "유효하지 않은 요청"));
        }

        return validators.stream()
                .map(validator -> validator.validate(request))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }
}
