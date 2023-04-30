package com.ssafy.goat.attraction.repository;

import com.ssafy.goat.attraction.AttractionInfo;
import com.ssafy.goat.attraction.dto.AttractionSearch;
import com.ssafy.goat.attraction.mapper.AttractionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AttractionMybatisRepository implements AttractionRepository {
    private final AttractionMapper attractionMapper;

    @Override
    public Optional<AttractionInfo> findById(int contentId) {
        return attractionMapper.findById(contentId);
    }

    @Override
    public List<AttractionInfo> findByConditions(AttractionSearch condition) {
        return attractionMapper.findByConditions(condition);
    }

    @Override
    public List<AttractionInfo> findByTitle(String title) {
        return attractionMapper.findByTitle(title);
    }

    @Override
    public List<AttractionInfo> findByContentIds(List<Integer> contentIds) {
        return attractionMapper.findByContentIds(contentIds);
    }

}
