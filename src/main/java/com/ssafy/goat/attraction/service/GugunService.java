package com.ssafy.goat.attraction.service;

import attraction.dto.GugunDto;

import java.util.List;

public interface GugunService {

    List<GugunDto> searchGuguns(int sidoCode);
}
