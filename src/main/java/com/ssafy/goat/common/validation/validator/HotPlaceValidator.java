package com.ssafy.goat.common.validation.validator;

import common.validation.dto.HotPlaceRequest;
import common.validation.dto.InvalidResponse;

import java.util.List;

public interface HotPlaceValidator {

    List<InvalidResponse> validate(HotPlaceRequest request);
}
