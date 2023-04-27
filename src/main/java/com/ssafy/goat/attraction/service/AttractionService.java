package com.ssafy.goat.attraction.service;

import com.ssafy.goat.attraction.AttractionInfo;
import com.ssafy.goat.attraction.dto.AttractionDto;
import com.ssafy.goat.attraction.dto.AttractionSearch;

import java.util.List;

public interface AttractionService {

    AttractionDto searchAttraction(int contentId);

    List<AttractionDto> searchAttraction(AttractionSearch condition);

    List<AttractionDto> searchAttraction(String title);

    List<AttractionInfo> searchAttraction(List<Integer> contentIds);
}
