package com.ssafy.goat.trend.repository;

import com.ssafy.goat.trend.Trend;
import com.ssafy.goat.trend.dto.TrendViewDto;

import java.util.Optional;

public interface TrendRepository {

    int save(int contentId);

    Optional<Trend> findByContentId(int contentId);

    TrendViewDto findPopularByTeenage();

    TrendViewDto findPopularByTwenty();

    TrendViewDto findPopularByThirty();

    TrendViewDto findPopularByMale();

    TrendViewDto findPopularByFemale();

    int update(Trend trend);
}
