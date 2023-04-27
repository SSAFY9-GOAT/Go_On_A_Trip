package com.ssafy.goat.common.validation.validator.member;

import com.ssafy.goat.common.validation.ValidationMessage;
import com.ssafy.goat.common.validation.dto.InvalidResponse;
import com.ssafy.goat.common.validation.dto.MemberRequest;
import com.ssafy.goat.common.validation.validator.MemberValidator;

import java.util.Collections;
import java.util.List;

public class LoginIdValidator implements MemberValidator {

    private static final InvalidResponse INVALID_RESPONSE = new InvalidResponse("loginId", ValidationMessage.LOGIN_ID);
    private static final int MAX_LENGTH = 20;
    private static final int MIN_LENGTH = 5;

    @Override
    public List<InvalidResponse> validate(MemberRequest request) {
        String loginId = request.getLoginId();

        if (loginId == null || isBlank(loginId)) {
            return Collections.singletonList(INVALID_RESPONSE);
        }

        if (isLength(loginId)) {
            return Collections.singletonList(INVALID_RESPONSE);
        }

        if (isLowercaseOrNumber(loginId)) {
            return Collections.emptyList();
        }

        return Collections.singletonList(INVALID_RESPONSE);
    }

    private boolean isBlank(String loginId) {
        return loginId.trim().isEmpty();
    }

    private boolean isLength(String loginId) {
        return !(MIN_LENGTH <= loginId.length() && loginId.length() <= MAX_LENGTH);
    }

    private boolean isLowercaseOrNumber(String loginId) {
        return loginId.matches("[a-z0-9]+");
    }
}
