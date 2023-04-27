package com.ssafy.goat.common.validation.validator;

import com.ssafy.goat.common.validation.dto.ArticleRequest;
import com.ssafy.goat.common.validation.dto.InvalidResponse;

import java.util.List;

public interface ArticleValidator {

    List<InvalidResponse> validate(ArticleRequest request);
}
