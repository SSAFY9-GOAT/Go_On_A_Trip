package com.ssafy.goat.common.validation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InvalidResponse {
    private String field;
    private String message;
}
