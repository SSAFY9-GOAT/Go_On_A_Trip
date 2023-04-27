package com.ssafy.goat.common.validation.validator;

import com.ssafy.goat.common.validation.dto.InvalidResponse;
import com.ssafy.goat.common.validation.dto.NotionRequest;

import java.util.List;

public interface NotionValidator {

    List<InvalidResponse> validate(NotionRequest request);

}
