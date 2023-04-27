package com.ssafy.goat.attraction.repository;

import attraction.AttractionInfo;
import attraction.dto.AttractionSearch;

import java.util.List;
import java.util.Optional;

public interface AttractionRepository {

    Optional<AttractionInfo> findById(int contentId);

    List<AttractionInfo> findByConditions(AttractionSearch condition);

    List<AttractionInfo> findByTitle(String title);

    List<AttractionInfo> findByContentIds(List<Integer> contentIds);
}
