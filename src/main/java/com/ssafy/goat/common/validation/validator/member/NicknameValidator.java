package com.ssafy.goat.common.validation.validator.member;

import com.ssafy.goat.common.validation.ValidationMessage;
import com.ssafy.goat.common.validation.dto.InvalidResponse;
import com.ssafy.goat.common.validation.dto.MemberRequest;
import com.ssafy.goat.common.validation.validator.MemberValidator;

import java.util.Collections;
import java.util.List;

public class NicknameValidator implements MemberValidator {

    private static final InvalidResponse INVALID_RESPONSE = new InvalidResponse("nickname", ValidationMessage.NICKNAME);
    private static final int MAX_LENGTH = 10;

    @Override
    public List<InvalidResponse> validate(MemberRequest request) {
        String nickname = request.getNickname();

        if (nickname == null || isBlank(nickname)) {
            return Collections.singletonList(INVALID_RESPONSE);
        }

        if (isLength(nickname)) {
            return Collections.singletonList(INVALID_RESPONSE);
        }

        return Collections.emptyList();
    }

    private boolean isBlank(String nickname) {
        return nickname.trim().isEmpty();
    }

    private boolean isLength(String nickname) {
        return MAX_LENGTH < nickname.length();
    }
}
