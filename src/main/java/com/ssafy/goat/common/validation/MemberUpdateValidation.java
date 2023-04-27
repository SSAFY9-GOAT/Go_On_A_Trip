package com.ssafy.goat.common.validation;

import common.validation.dto.InvalidResponse;
import common.validation.dto.MemberRequest;
import common.validation.validator.MemberValidator;
import common.validation.validator.member.EmailValidator;
import common.validation.validator.member.LoginPwValidator;
import common.validation.validator.member.NicknameValidator;
import common.validation.validator.member.PhoneValidator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class MemberUpdateValidation {

    private final List<MemberValidator> validators;

    public MemberUpdateValidation() {
        validators = new ArrayList<>();
        validators.add(new LoginPwValidator());
        validators.add(new EmailValidator());
        validators.add(new PhoneValidator());
        validators.add(new NicknameValidator());
    }

    public List<InvalidResponse> validate(MemberRequest request) {
        if (request == null) {
            return Collections.singletonList(new InvalidResponse("request", "유효하지 않은 요청"));
        }

        return validators.stream()
                .map(validator -> validator.validate(request))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }
}
