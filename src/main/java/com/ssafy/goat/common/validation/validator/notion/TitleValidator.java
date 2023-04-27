package com.ssafy.goat.common.validation.validator.notion;

import com.ssafy.goat.common.validation.ValidationMessage;
import com.ssafy.goat.common.validation.dto.InvalidResponse;
import com.ssafy.goat.common.validation.dto.NotionRequest;
import com.ssafy.goat.common.validation.validator.NotionValidator;

import java.util.Collections;
import java.util.List;

public class TitleValidator implements NotionValidator {

    private static final InvalidResponse INVALID_RESPONSE = new InvalidResponse("title", ValidationMessage.TITLE);
    private static final int MAX_LENGTH = 100;

    @Override
    public List<InvalidResponse> validate(NotionRequest request) {
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
