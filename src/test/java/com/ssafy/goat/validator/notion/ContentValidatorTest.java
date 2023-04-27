package com.ssafy.goat.validator.notion;

import com.ssafy.goat.common.validation.dto.InvalidResponse;
import com.ssafy.goat.common.validation.dto.NotionRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ContentValidatorTest {

    private final ContentValidator validator = new ContentValidator();

    @Test
    @DisplayName("게시물 내용 검증")
    void contentValidator() {
        //given
        NotionRequest request = NotionRequest.builder()
                .content("게시물 내용!")
                .build();

        //when
        List<InvalidResponse> validate = validator.validate(request);

        //then
        assertThat(validate).isEmpty();
    }

    @Test
    @DisplayName("게시물 내용 길이 예외")
    void content_exception_length() {
        //given
        NotionRequest request = NotionRequest.builder()
                .content(getString(1001))
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