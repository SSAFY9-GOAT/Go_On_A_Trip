package com.ssafy.goat.common.validation.validator;

import common.validation.dto.InvalidResponse;
import common.validation.dto.NotionRequest;

import java.util.List;

public interface NotionValidator {

    List<InvalidResponse> validate(NotionRequest request);

}
