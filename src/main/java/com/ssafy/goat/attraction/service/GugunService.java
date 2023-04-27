package com.ssafy.goat.attraction.service;

import com.ssafy.goat.attraction.dto.GugunDto;

import java.util.List;

public interface GugunService {

    List<GugunDto> searchGuguns(int sidoCode);
}
