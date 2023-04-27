package com.ssafy.goat.validator.notion;

import com.ssafy.goat.common.validation.dto.InvalidResponse;
import com.ssafy.goat.common.validation.dto.NotionRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class TitleValidatorTest {

    private final TitleValidator validator = new TitleValidator();

    @Test
    @DisplayName("게시물 제목 검증")
    void titleValidator() {
        //given
        NotionRequest request = NotionRequest.builder()
                .title("게시물 제목!")
                .build();

        //when
        List<InvalidResponse> validate = validator.validate(request);

        //then
        assertThat(validate).isEmpty();
    }

    @Test
    @DisplayName("게시물 제목 길이 예외")
    void title_exception_length() {
        //given
        NotionRequest request = NotionRequest.builder()
                .title(getString(101))
                .build();

        //when
        List<InvalidResponse> validate = validator.validate(request);

        //then
        assertThat(validate).isNotEmpty();
    }

    private String getString(int size) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append("a");
        }
        return String.valueOf(sb);
    }
}