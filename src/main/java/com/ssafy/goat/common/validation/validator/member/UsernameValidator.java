package com.ssafy.goat.common.validation.validator.member;

import common.validation.ValidationMessage;
import common.validation.dto.InvalidResponse;
import common.validation.dto.MemberRequest;
import common.validation.validator.MemberValidator;

import java.util.Collections;
import java.util.List;

public class UsernameValidator implements MemberValidator {

    private static final InvalidResponse INVALID_RESPONSE = new InvalidResponse("username", ValidationMessage.USERNAME);
    private static final int MAX_LENGTH = 20;

    @Override
    public List<InvalidResponse> validate(MemberRequest request) {
        String username = request.getUsername();

        if (username == null || isBlank(username)) {
            return Collections.singletonList(INVALID_RESPONSE);
        }

        if (isLength(username)) {
            return Collections.singletonList(INVALID_RESPONSE);
        }

        if (isKorean(username)) {
            return Collections.emptyList();
        }

        return Collections.singletonList(INVALID_RESPONSE);
    }

    private boolean isBlank(String username) {
        return username.trim().isEmpty();
    }

    private boolean isLength(String username) {
        return MAX_LENGTH < username.length();
    }

    private boolean isKorean(String username) {
        return username.matches("[가-힣]+");
    }
}
