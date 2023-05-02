package com.ssafy.goat.tripplan.repository;


import com.ssafy.goat.attraction.AttractionInfo;
import com.ssafy.goat.member.Member;
import com.ssafy.goat.tripplan.DetailPlan;
import com.ssafy.goat.tripplan.TripPlan;
import com.ssafy.goat.tripplan.dto.DetailPlanDto;
import com.ssafy.goat.tripplan.dto.PlanListDto;
import com.ssafy.goat.tripplan.dto.PlanSearch;
import com.ssafy.goat.tripplan.dto.TripPlanDto;
import com.ssafy.goat.tripplan.mapper.PlanMapper;
import com.ssafy.goat.util.DBConnectionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PlanMybatisRepository implements PlanRepository {
    private final PlanMapper planMapper;

    @Override
    public int save(TripPlan tripPlan) {
        return planMapper.save(tripPlan);
    }

    @Override
    public int save(DetailPlan detailPlan) {
        return planMapper.saveDetailPlan(detailPlan);
    }

    @Override
    public Optional<TripPlan> findById(Long tripPlanId) {
        return planMapper.findById(tripPlanId);
    }

    @Override
    public List<DetailPlan> findByTripPlanId(Long tripPlanId) {
        return planMapper.findByTripPlanId(tripPlanId);
    }

    @Override
    public Optional<DetailPlan> findByDetailPlanId(Long detailPlanId) {
        return planMapper.findByDetailPlanId(detailPlanId);
    }

    @Override
    public List<TripPlan> findAllByMemberId(Long memberId) {
        return planMapper.findAllByMemberId(memberId);
    }

    @Override
    public TripPlanDto findAllByTripPlanId(Long tripPlanId) {
        return planMapper.findAllByTripPlanId(tripPlanId);
    }

    @Override
    public List<PlanListDto> findByCondition(PlanSearch condition, int pageNum, int amount) {
        return planMapper.findByCondition(condition, pageNum, amount);
    }

    @Override
    public Long findByMemberId(Long memberId) {
        return planMapper.findByMemberId(memberId);
    }

    @Override
    public int findTotalCount() {
        return planMapper.findTotalCount();
    }

    @Override
    public int updateTripPlan(Long tripPlanId, TripPlan tripPlan) {
        return planMapper.updateTripPlan(tripPlanId, tripPlan);
    }

    @Override
    public int removeTripPlan(Long tripPlanId) {
        return planMapper.removeTripPlan(tripPlanId);
    }

    @Override
    public int removeDetailPlan(Long detailPlanId) {
        return planMapper.removeDetailPlan(detailPlanId);
    }

    @Override
    public void clear() {
        planMapper.clear();
    }

    private String dateFormat(LocalDateTime dateTime) {
        if (dateTime == null) {
            return null;
        }
        return dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
