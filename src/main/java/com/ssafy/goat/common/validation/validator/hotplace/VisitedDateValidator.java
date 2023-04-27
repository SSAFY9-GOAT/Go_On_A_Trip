package com.ssafy.goat.common.validation.validator.hotplace;

import common.validation.ValidationMessage;
import common.validation.dto.HotPlaceRequest;
import common.validation.dto.InvalidResponse;
import common.validation.validator.HotPlaceValidator;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

public class VisitedDateValidator implements HotPlaceValidator {

    private static final InvalidResponse INVALID_RESPONSE = new InvalidResponse("visitedDate", ValidationMessage.VISITED_DATE);
    private static final int LENGTH = 10;

    @Override
    public List<InvalidResponse> validate(HotPlaceRequest request) {
        String visitedDate = request.getVisitedDate();

        if (visitedDate == null || isBlank(visitedDate)) {
            return Collections.singletonList(INVALID_RESPONSE);
        }

        if (isLength(visitedDate)) {
            return Collections.singletonList(INVALID_RESPONSE);
        }

        if (isFuture(visitedDate)) {
            return Collections.singletonList(INVALID_RESPONSE);
        }

        return Collections.emptyList();
    }

    private boolean isBlank(String visitedDate) {
        return visitedDate.trim().isEmpty();
    }

    private boolean isLength(String visitedDate) {
        return LENGTH != visitedDate.trim().length();
    }

    private boolean isFuture(String visitedDate) {
        String[] date = visitedDate.split("-");
        LocalDateTime localVisitedDate = LocalDateTime.of(Integer.parseInt(date[0]), Integer.parseInt(date[1]), Integer.parseInt(date[2]), 0, 0);
        LocalDateTime now = LocalDateTime.now();
        return now.isBefore(localVisitedDate);
    }
}
