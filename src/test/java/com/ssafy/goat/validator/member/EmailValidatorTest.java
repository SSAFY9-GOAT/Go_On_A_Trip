package com.ssafy.goat.validator.member;

import common.validation.dto.InvalidResponse;
import common.validation.dto.MemberRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class EmailValidatorTest {

    private final EmailValidator validator = new EmailValidator();

    @Test
    @DisplayName("이메일 검증")
    void usernameValidator() {
        //given
        MemberRequest request = MemberRequest.builder()
                .email("ssafy@ssafy.com")
                .build();

        //when
        List<InvalidResponse> validate = validator.validate(request);

        //then
        assertThat(validate).isEmpty();
    }

    @ParameterizedTest
    @DisplayName("이메일 길이 예외")
    @CsvSource({"ssafy0123456789012345678901234567890123456789@ssafy.com"})
    void exception_length(String email) {
        //given
        MemberRequest request = MemberRequest.builder()
                .email(email)
                .build();

        //when
        List<InvalidResponse> validate = validator.validate(request);

        //then
        assertThat(validate).isNotEmpty();
    }

    @ParameterizedTest
    @DisplayName("이메일 포멧 예외")
    @CsvSource({"ssafyssafy.com", "ssafy@ssafycom", "ssafy@ssafy", "ssafy@.com", "@ssafy.com",
            "ssafy@ssafy.", "ssafyssafycom", "싸피@싸피.컴", "ssafy!@ssafy.com", "ssafy@ ssafy.com"})
    void exception_type(String email) {
        //given
        MemberRequest request = MemberRequest.builder()
                .email(email)
                .build();

        //when
        List<InvalidResponse> validate = validator.validate(request);

        //then
        assertThat(validate).isNotEmpty();
    }
}