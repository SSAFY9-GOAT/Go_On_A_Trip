package com.ssafy.goat.common.validation.validator;

import common.validation.dto.ArticleRequest;
import common.validation.dto.InvalidResponse;

import java.util.List;

public interface ArticleValidator {

    List<InvalidResponse> validate(ArticleRequest request);
}
