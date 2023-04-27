package com.ssafy.goat.common.validation.validator;

import common.validation.dto.InvalidResponse;
import common.validation.dto.MemberRequest;

import java.util.List;

public interface MemberValidator {

    List<InvalidResponse> validate(MemberRequest request);

}
