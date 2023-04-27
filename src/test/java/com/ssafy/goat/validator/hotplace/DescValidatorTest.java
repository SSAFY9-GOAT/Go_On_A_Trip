package com.ssafy.goat.validator.hotplace;

import common.validation.dto.HotPlaceRequest;
import common.validation.dto.InvalidResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class DescValidatorTest {

    private final DescValidator validator = new DescValidator();

    @Test
    @DisplayName("핫플레이스 설명 검증")
    void descValidator() {
        //given
        HotPlaceRequest request = HotPlaceRequest.builder()
                .desc("핫플레이스 설명")
                .build();

        //when
        List<InvalidResponse> responses = validator.validate(request);

        //then
        assertThat(responses).isEmpty();
    }

    @Test
    @DisplayName("핫플레이스 설명 길이 예외")
    void desc_exception_length() {
        //given
        HotPlaceRequest request = HotPlaceRequest.builder()
                .desc(getString(501))
                .build();

        //when
        List<InvalidResponse> responses = validator.validate(request);

        //then
        assertThat(responses).isNotEmpty();
    }

    private String getString(int size) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append("a");
        }
        return String.valueOf(sb);
    }
}