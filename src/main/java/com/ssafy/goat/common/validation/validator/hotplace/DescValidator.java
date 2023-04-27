package com.ssafy.goat.common.validation.validator.hotplace;

import com.ssafy.goat.common.validation.ValidationMessage;
import com.ssafy.goat.common.validation.dto.HotPlaceRequest;
import com.ssafy.goat.common.validation.dto.InvalidResponse;
import com.ssafy.goat.common.validation.validator.HotPlaceValidator;

import java.util.Collections;
import java.util.List;

public class DescValidator implements HotPlaceValidator {

    private static final InvalidResponse INVALID_RESPONSE = new InvalidResponse("desc", ValidationMessage.DESC);
    private static final int MAX_LENGTH = 500;

    @Override
    public List<InvalidResponse> validate(HotPlaceRequest request) {
        String desc = request.getDesc();

        if (desc == null || isBlank(desc)) {
            return Collections.singletonList(INVALID_RESPONSE);
        }

        if (isLength(desc)) {
            return Collections.singletonList(INVALID_RESPONSE);
        }

        return Collections.emptyList();
    }

    private boolean isBlank(String desc) {
        return desc.trim().isEmpty();
    }

    private boolean isLength(String desc) {
        return MAX_LENGTH < desc.trim().length();
    }
}
