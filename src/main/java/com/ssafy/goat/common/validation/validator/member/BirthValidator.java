package com.ssafy.goat.common.validation.validator.member;

import com.ssafy.goat.common.validation.ValidationMessage;
import com.ssafy.goat.common.validation.dto.InvalidResponse;
import com.ssafy.goat.common.validation.dto.MemberRequest;
import com.ssafy.goat.common.validation.validator.MemberValidator;

import java.util.Collections;
import java.util.List;

public class BirthValidator implements MemberValidator {

    private static final InvalidResponse INVALID_RESPONSE = new InvalidResponse("birth", ValidationMessage.BIRTH);
    private static final int LENGTH = 6;

    @Override
    public List<InvalidResponse> validate(MemberRequest request) {
        String birth = request.getBirth();

        if (birth == null || isBlank(birth)) {
            return Collections.singletonList(INVALID_RESPONSE);
        }

        if (isLength(birth)) {
            return Collections.singletonList(INVALID_RESPONSE);
        }

        if (isNumber(birth)) {
            return Collections.emptyList();
        }

        return Collections.singletonList(INVALID_RESPONSE);
    }

    private boolean isBlank(String birth) {
        return birth.trim().isEmpty();
    }

    private boolean isLength(String birth) {
        return birth.length() != LENGTH;
    }

    private boolean isNumber(String birth) {
        return birth.matches("[0-9]+");
    }
}
