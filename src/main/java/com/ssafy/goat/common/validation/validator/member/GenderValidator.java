package com.ssafy.goat.common.validation.validator.member;

import com.ssafy.goat.common.validation.ValidationMessage;
import com.ssafy.goat.common.validation.dto.InvalidResponse;
import com.ssafy.goat.common.validation.dto.MemberRequest;
import com.ssafy.goat.common.validation.validator.MemberValidator;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GenderValidator implements MemberValidator {

    private static final InvalidResponse INVALID_RESPONSE = new InvalidResponse("gender", ValidationMessage.GENDER);
    private static final int LENGTH = 1;
    private static final List<String> GENDERS = Arrays.asList("1", "2", "3", "4");

    @Override
    public List<InvalidResponse> validate(MemberRequest request) {
        String gender = request.getGender();

        if (gender == null || isBlank(gender)) {
            return Collections.singletonList(INVALID_RESPONSE);
        }

        if (isLength(gender)) {
            return Collections.singletonList(INVALID_RESPONSE);
        }

        if (isNumber(gender) && isCheck(gender)) {
            return Collections.emptyList();
        }

        return Collections.singletonList(INVALID_RESPONSE);
    }

    private boolean isBlank(String gender) {
        return gender.trim().isEmpty();
    }

    private boolean isLength(String gender) {
        return gender.length() != LENGTH;
    }

    private boolean isNumber(String gender) {
        return gender.matches("[0-9]+");
    }

    private boolean isCheck(String gender) {
        return GENDERS.contains(gender);
    }
}
