package com.ssafy.goat.common.validation.validator.member;

import com.ssafy.goat.common.validation.ValidationMessage;
import com.ssafy.goat.common.validation.dto.InvalidResponse;
import com.ssafy.goat.common.validation.dto.MemberRequest;
import com.ssafy.goat.common.validation.validator.MemberValidator;

import java.util.Collections;
import java.util.List;

public class PhoneValidator implements MemberValidator {

    private static final InvalidResponse INVALID_RESPONSE = new InvalidResponse("phone", ValidationMessage.PHONE);
    private static final int LENGTH = 11;

    @Override
    public List<InvalidResponse> validate(MemberRequest request) {
        String phone = request.getPhone();

        if (phone == null || isBlank(phone)) {
            return Collections.singletonList(INVALID_RESPONSE);
        }

        if (isLength(phone)) {
            return Collections.singletonList(INVALID_RESPONSE);
        }

        if (isNumber(phone)) {
            return Collections.emptyList();
        }

        return Collections.singletonList(INVALID_RESPONSE);
    }

    private boolean isBlank(String phone) {
        return phone.trim().isEmpty();
    }

    private boolean isLength(String phone) {
        return phone.length() != LENGTH;
    }

    private boolean isNumber(String phone) {
        return phone.matches("[0-9]+");
    }
}
