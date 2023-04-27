package com.ssafy.goat.common.validation.validator;

import com.ssafy.goat.common.validation.dto.HotPlaceRequest;
import com.ssafy.goat.common.validation.dto.InvalidResponse;

import java.util.List;

public interface HotPlaceValidator {

    List<InvalidResponse> validate(HotPlaceRequest request);
}
