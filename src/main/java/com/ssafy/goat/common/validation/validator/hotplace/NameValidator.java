package com.ssafy.goat.common.validation.validator.hotplace;

import common.validation.ValidationMessage;
import common.validation.dto.HotPlaceRequest;
import common.validation.dto.InvalidResponse;
import common.validation.validator.HotPlaceValidator;

import java.util.Collections;
import java.util.List;

public class NameValidator implements HotPlaceValidator {

    private static final InvalidResponse INVALID_RESPONSE = new InvalidResponse("name", ValidationMessage.NAME);
    private static final int MAX_LENGTH = 50;

    @Override
    public List<InvalidResponse> validate(HotPlaceRequest request) {
        String name = request.getName();

        if (name == null || isBlank(name)) {
            return Collections.singletonList(INVALID_RESPONSE);
        }

        if (isLength(name)) {
            return Collections.singletonList(INVALID_RESPONSE);
        }

        return Collections.emptyList();
    }

    private boolean isBlank(String name) {
        return name.trim().isEmpty();
    }

    private boolean isLength(String name) {
        return MAX_LENGTH < name.trim().length();
    }
}
