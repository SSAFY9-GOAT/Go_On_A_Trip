package com.ssafy.goat.attraction;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Gugun {

    private Integer code;
    private String name;
    private Sido sido;

    @Builder
    public Gugun(Integer code, String name, Sido sido) {
        this.code = code;
        this.name = name;
        this.sido = sido;
    }
}
