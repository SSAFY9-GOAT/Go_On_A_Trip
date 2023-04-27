package com.ssafy.goat.common.validation.validator.member;

import com.ssafy.goat.common.validation.dto.InvalidResponse;
import com.ssafy.goat.common.validation.dto.MemberRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LoginPwValidatorTest {

    private final LoginPwValidator validator = new LoginPwValidator();

    @Test
    @DisplayName("로그인 비빌번호 검증")
    void loginIdValidator() {
        //given
        MemberRequest request = MemberRequest.builder()
                .loginPw("SSAFY1234")
                .build();

        //when
        List<InvalidResponse> validate = validator.validate(request);

        //then
        assertThat(validate).isEmpty();
    }

    @ParameterizedTest
    @DisplayName("로그인 비빌번호 길이 예외")
    @CsvSource({"0123", "012345678901234567890", "null", "0123456 ", " 0123456"})
    void exception_length(String loginPw) {
        //given
        MemberRequest request = MemberRequest.builder()
                .loginPw(loginPw)
                .build();

        //when
        List<InvalidResponse> validate = validator.validate(request);

        //then
        assertThat(validate).isNotEmpty();
    }

    @ParameterizedTest
    @DisplayName("로그인 비빌번호 타입 예외")
    @CsvSource({"SSAFY1234!", "SSAFY 1234", "비밀번호1234"})
    void exception_type(String loginPw) {
        //given
        MemberRequest request = MemberRequest.builder()
                .loginPw(loginPw)
                .build();

        //when
        List<InvalidResponse> validate = validator.validate(request);

        //then
        assertThat(validate).isNotEmpty();
    }
}