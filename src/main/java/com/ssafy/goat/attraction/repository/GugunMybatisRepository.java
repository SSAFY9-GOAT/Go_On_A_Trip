package com.ssafy.goat.attraction.repository;

import com.ssafy.goat.attraction.Gugun;
import com.ssafy.goat.attraction.mapper.AttractionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class GugunMybatisRepository implements GugunRepository{
    private final AttractionMapper attractionMapper;
    @Override
    public List<Gugun> findBySidoCode(int sidoCode) {
        return attractionMapper.findBySidoCode(sidoCode);
    }
}
