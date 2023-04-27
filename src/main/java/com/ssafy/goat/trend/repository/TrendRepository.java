package com.ssafy.goat.trend.repository;

import trend.Trend;
import trend.dto.TrendViewDto;

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
