package com.ssafy.goat.validator.member;

import com.ssafy.goat.common.validation.dto.InvalidResponse;
import com.ssafy.goat.common.validation.dto.MemberRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class NicknameValidatorTest {

    private final NicknameValidator validator = new NicknameValidator();

    @Test
    @DisplayName("닉네임 검증")
    void nicknameValidator() {
        //given
        MemberRequest request = MemberRequest.builder()
                .nickname("광주5반")
                .build();

        //when
        List<InvalidResponse> validate = validator.validate(request);

        //then
        assertThat(validate).isEmpty();
    }

    @ParameterizedTest
    @DisplayName("닉네임 길이 예외")
    @CsvSource({"01234567890"})
    void exception_length(String nickname) {
        //given
        MemberRequest request = MemberRequest.builder()
                .nickname(nickname)
                .build();

        //when
        List<InvalidResponse> validate = validator.validate(request);

        //then
        assertThat(validate).isNotEmpty();
    }
}