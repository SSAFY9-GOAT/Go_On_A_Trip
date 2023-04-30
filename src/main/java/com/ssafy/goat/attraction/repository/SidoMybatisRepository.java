package com.ssafy.goat.attraction.repository;

import com.ssafy.goat.attraction.Sido;
import com.ssafy.goat.attraction.mapper.AttractionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class SidoMybatisRepository implements SidoRepository{
    private final AttractionMapper attractionMapper;
    @Override
    public List<Sido> findAll() {
        return attractionMapper.findAll();
    }
}
