package com.ssafy.goat.trend.repository;

import com.ssafy.goat.attraction.AttractionInfo;
import com.ssafy.goat.trend.Trend;
import com.ssafy.goat.trend.dto.TrendViewDto;
import com.ssafy.goat.trend.mapper.TrendMapper;
import com.ssafy.goat.util.DBConnectionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class TrendMybatisRepository implements TrendRepository {
    private final TrendMapper trendMapper;

    @Override
    public int save(int contentId) {
        return trendMapper.save(contentId);
    }

    @Override
    public Optional<Trend> findByContentId(int contentId) {
        return trendMapper.findByContentId(contentId);
    }

    @Override
    public TrendViewDto findPopularByTeenage() {
        return trendMapper.findPopularByTeenage();
    }

    @Override
    public TrendViewDto findPopularByTwenty() {
        return trendMapper.findPopularByTwenty();
    }

    @Override
    public TrendViewDto findPopularByThirty() {
        return trendMapper.findPopularByThirty();
    }

    @Override
    public TrendViewDto findPopularByMale() {
        return trendMapper.findPopularByMale();
    }

    @Override
    public TrendViewDto findPopularByFemale() {
        return trendMapper.findPopularByFemale();
    }

    @Override
    public int update(Trend trend) {
        return trendMapper.update(trend);
    }
}
