package com.ssafy.goat.common.validation.validator.member;

import com.ssafy.goat.common.validation.dto.InvalidResponse;
import com.ssafy.goat.common.validation.dto.MemberRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class BirthValidatorTest {

    private final BirthValidator validator = new BirthValidator();

    @Test
    @DisplayName("생년월일 검증")
    void birthValidator() {
        //given
        MemberRequest request = MemberRequest.builder()
                .birth("010101")
                .build();

        //when
        List<InvalidResponse> validate = validator.validate(request);

        //then
        assertThat(validate).isEmpty();
    }

    @ParameterizedTest
    @DisplayName("생년월일 길이 예외")
    @CsvSource({"9911", "20010101"})
    void exception_length(String birth) {
        //given
        MemberRequest request = MemberRequest.builder()
                .birth(birth)
                .build();

        //when
        List<InvalidResponse> validate = validator.validate(request);

        //then
        assertThat(validate).isNotEmpty();
    }

    @ParameterizedTest
    @DisplayName("생년월일 타입 예외")
    @CsvSource({"99.1.1", "O1O1O1"})
    void exception_type(String birth) {
        //given
        MemberRequest request = MemberRequest.builder()
                .birth(birth)
                .build();

        //when
        List<InvalidResponse> validate = validator.validate(request);

        //then
        assertThat(validate).isNotEmpty();
    }
}