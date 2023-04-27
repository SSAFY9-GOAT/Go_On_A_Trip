package com.ssafy.goat.common.validation.validator;

import com.ssafy.goat.common.validation.dto.InvalidResponse;
import com.ssafy.goat.common.validation.dto.MemberRequest;

import java.util.List;

public interface MemberValidator {

    List<InvalidResponse> validate(MemberRequest request);

}
