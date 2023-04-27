package com.ssafy.goat.validator.hotplace;

import com.ssafy.goat.common.validation.dto.HotPlaceRequest;
import com.ssafy.goat.common.validation.dto.InvalidResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class NameValidatorTest {

    private final NameValidator validator = new NameValidator();

    @Test
    @DisplayName("핫플레이스 이름 검증")
    void nameValidator() {
        //given
        HotPlaceRequest request = HotPlaceRequest.builder()
                .name("핫플레이스 이름")
                .build();

        //when
        List<InvalidResponse> responses = validator.validate(request);

        //then
        assertThat(responses).isEmpty();
    }

    @Test
    @DisplayName("핫플레이스 이름 길이 예외")
    void name_exception_length() {
        //given
        HotPlaceRequest request = HotPlaceRequest.builder()
                .name(getString(51))
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