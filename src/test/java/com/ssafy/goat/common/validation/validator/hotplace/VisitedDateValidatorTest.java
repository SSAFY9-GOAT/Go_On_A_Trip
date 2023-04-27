package com.ssafy.goat.common.validation.validator.hotplace;

import com.ssafy.goat.common.validation.dto.HotPlaceRequest;
import com.ssafy.goat.common.validation.dto.InvalidResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class VisitedDateValidatorTest {

    private final VisitedDateValidator validator = new VisitedDateValidator();

    @Test
    @DisplayName("핫플레이스 방문일자 검증")
    void visitedDateValidator() {
        //given
        HotPlaceRequest request = HotPlaceRequest.builder()
                .visitedDate("2023-01-01")
                .build();

        //when
        List<InvalidResponse> responses = validator.validate(request);

        //then
        assertThat(responses).isEmpty();
    }

    @Test
    @DisplayName("방문일자 길이 예외")
    void visitedDate_exception_length() {
        //given
        HotPlaceRequest request = HotPlaceRequest.builder()
                .visitedDate("20230101")
                .build();

        //when
        List<InvalidResponse> responses = validator.validate(request);

        //then
        assertThat(responses).isNotEmpty();
    }

    @Test
    @DisplayName("방문일자 예외")
    void visitedDate_exception_future() {
        //given
        HotPlaceRequest request = HotPlaceRequest.builder()
                .visitedDate("3000-01-01")
                .build();

        //when
        List<InvalidResponse> responses = validator.validate(request);

        //then
        assertThat(responses).isNotEmpty();
    }
}