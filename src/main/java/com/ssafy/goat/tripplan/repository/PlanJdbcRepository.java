package com.ssafy.goat.tripplan.repository;

import attraction.AttractionInfo;
import member.Member;
import tripplan.DetailPlan;
import tripplan.TripPlan;
import tripplan.dto.DetailPlanDto;
import tripplan.dto.PlanListDto;
import tripplan.dto.PlanSearch;
import tripplan.dto.TripPlanDto;
import util.DBConnectionUtil;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PlanJdbcRepository implements PlanRepository {
    private static final PlanRepository planRepository = new PlanJdbcRepository();

    private final DBConnectionUtil dbConnectionUtil;

    private PlanJdbcRepository() {
        dbConnectionUtil = DBConnectionUtil.getInstance();
    }

    public static PlanRepository getPlanRepository() {
        return planRepository;
    }

    @Override
    public int save(TripPlan tripPlan) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int count = 0;
        try {
            conn = dbConnectionUtil.getConnection();
            String sql = "insert into trip_plan (member_id, title) values (? , ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, tripPlan.getMember().getId());
            pstmt.setString(2, tripPlan.getTitle());
            count = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnectionUtil.close(pstmt, conn);
        }
        return count;
    }

    @Override
    public int save(DetailPlan detailPlan) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int count = 0;
        try {
            conn = dbConnectionUtil.getConnection();
            String sql = "insert into detail_plan (trip_plan_id, content_id) values (? , ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, detailPlan.getTripPlan().getId());
            pstmt.setInt(2, detailPlan.getAttractionInfo().getId());
            count = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnectionUtil.close(pstmt, conn);
        }
        return count;
    }

    @Override
    public Optional<TripPlan> findById(Long tripPlanId) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        TripPlan tripPlan = null;
        try {
            conn = dbConnectionUtil.getConnection();
            String sql = "select * from trip_plan where trip_plan_id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, tripPlanId);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                tripPlan = createTripPlan(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnectionUtil.close(rs, pstmt, conn);
        }
        return Optional.ofNullable(tripPlan);
    }

    @Override
    public Optional<DetailPlan> findByDetailPlanId(Long detailPlanId) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        DetailPlan detailPlan = null;
        try {
            conn = dbConnectionUtil.getConnection();
            String sql = "select *" +
                    " from detail_plan dp" +
                    " join trip_plan tp" +
                    " on dp.trip_plan_id = tp.trip_plan_id" +
                    " where detail_plan_id = ?";

            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, detailPlanId);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                detailPlan = createJoinDetailPlan(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnectionUtil.close(rs, pstmt, conn);
        }
        return Optional.ofNullable(detailPlan);
    }

    @Override
    public List<TripPlan> findAllByMemberId(Long memberId) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<TripPlan> tripPlans = new ArrayList<>();
        try {
            conn = dbConnectionUtil.getConnection();
            String sql = "select * from trip_plan where member_id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, memberId);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                tripPlans.add(createTripPlan(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnectionUtil.close(rs, pstmt, conn);
        }
        return tripPlans;
    }

    @Override
    public TripPlanDto findAllByTripPlanId(Long tripPlanId) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        TripPlanDto tripPlanDto = null;
        List<DetailPlanDto> detailPlans = new ArrayList<>();
        String title = null;
        try {
            conn = dbConnectionUtil.getConnection();
            String sql = "select * " +
                    "from trip_plan tp " +
                    "left outer join detail_plan dp on tp.trip_plan_id=dp.trip_plan_id " +
                    "join attraction_info a on dp.content_id=a.content_id " +
                    "where tp.trip_plan_id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, tripPlanId);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                if (title == null) {
                    title = rs.getString("tp.title");
                }
                DetailPlanDto detailPlanDto = DetailPlanDto.builder()
                        .title(rs.getString("a.title"))
                        .detailPlanId(rs.getLong("detail_plan_id"))
                        .longitude(rs.getDouble("longitude"))
                        .latitude(rs.getDouble("latitude"))
                        .build();
                detailPlans.add(detailPlanDto);
            }
            if (title != null) {
                tripPlanDto = TripPlanDto.builder()
                        .tripPlanId(tripPlanId)
                        .title(title)
                        .detailPlans(detailPlans)
                        .build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnectionUtil.close(rs, pstmt, conn);
        }
        return tripPlanDto;
    }

    @Override
    public List<PlanListDto> findByCondition(PlanSearch condition, int pageNum, int amount) {
        List<PlanListDto> planListDtos = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = dbConnectionUtil.getConnection();
            String sql = "select * " +
                    "from trip_plan tp " +
                    "join member m " +
                    "on tp.member_id = m.member_id " +
                    "where tp.title like ? or m.nickname like ? " +
                    "order by tp.created_date desc limit ?, ?";

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "%" + condition.getCondition() + "%");
            pstmt.setString(2, "%" + condition.getCondition() + "%");
            pstmt.setInt(3, (pageNum - 1) * amount);
            pstmt.setInt(4, amount);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                PlanListDto planListDto = PlanListDto.builder()
                        .tripPlanId(rs.getLong("trip_plan_id"))
                        .title(rs.getString("title"))
                        .nickname(rs.getString("nickname"))
                        .createdDate(dateFormat(rs.getTimestamp("created_date").toLocalDateTime()))
                        .build();
                planListDtos.add(planListDto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnectionUtil.close(rs, pstmt, conn);
        }
        return planListDtos;
    }

    @Override
    public Long findByMemberId(Long memberId) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Long tripPlan = null;
        try {
            conn = dbConnectionUtil.getConnection();
            String sql = "select trip_plan_id" +
                    " from trip_plan" +
                    " where member_id = ?" +
                    " order by created_date desc";

            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, memberId);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                tripPlan = rs.getLong("trip_plan_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnectionUtil.close(rs, pstmt, conn);
        }
        return tripPlan;
    }

    @Override
    public int findTotalCount() {
        int result = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = dbConnectionUtil.getConnection();
            String sql = "select count(*) as total from trip_plan;";

            pstmt = conn.prepareStatement(sql);

            rs = pstmt.executeQuery();
            if (rs.next()) {
                result = rs.getInt("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnectionUtil.close(rs, pstmt, conn);
        }
        return result;
    }


    @Override
    public int updateTripPlan(Long tripPlanId, TripPlan tripPlan) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int count = 0;
        try {
            conn = dbConnectionUtil.getConnection();
            String sql = "update trip_plan set title=?, last_modified_date=? where trip_plan_id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, tripPlan.getTitle());
            pstmt.setTimestamp(2, Timestamp.valueOf(tripPlan.getLastModifiedDate()));
            pstmt.setLong(3, tripPlanId);
            count = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnectionUtil.close(pstmt, conn);
        }
        return count;
    }

    @Override
    public int removeTripPlan(Long tripPlanId) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int count = 0;
        try {
            conn = dbConnectionUtil.getConnection();
            String sql = "delete from trip_plan where trip_plan_id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, tripPlanId);
            count = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnectionUtil.close(pstmt, conn);
        }
        return count;
    }

    @Override
    public int removeDetailPlan(Long detailPlanId) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int count = 0;
        try {
            conn = dbConnectionUtil.getConnection();
            String sql = "delete from detail_plan where detail_plan_id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, detailPlanId);
            count = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnectionUtil.close(pstmt, conn);
        }
        return count;
    }

    @Override
    public void clear() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = dbConnectionUtil.getConnection();
            String sql = "delete from detail_plan";
            pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();

            sql = "delete from trip_plan";
            pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnectionUtil.close(pstmt, conn);
        }
    }

    private TripPlan createTripPlan(ResultSet rs) throws SQLException {
        return TripPlan.builder()
                .id(rs.getLong("trip_plan_id"))
                .member(Member.builder()
                        .id(rs.getLong("member_id"))
                        .build()
                )
                .title(rs.getString("title"))
                .createdDate(rs.getTimestamp("created_date").toLocalDateTime())
                .lastModifiedDate(rs.getTimestamp("last_modified_date").toLocalDateTime())
                .build();
    }

    private TripPlan createJoinTripPlan(ResultSet rs) throws SQLException {
        return TripPlan.builder()
                .id(rs.getLong("trip_plan_id"))
                .member(Member.builder()
                        .id(rs.getLong("member_id"))
                        .nickname(rs.getString("nickname"))
                        .build()
                )
                .title(rs.getString("title"))
                .createdDate(rs.getTimestamp("created_date").toLocalDateTime())
                .lastModifiedDate(rs.getTimestamp("last_modified_date").toLocalDateTime())
                .build();
    }

    private static DetailPlan createDetailPlan(ResultSet rs) throws SQLException {
        return DetailPlan.builder()
                .id(rs.getLong("detail_plan_id"))
                .tripPlan(TripPlan.builder()
                        .id(rs.getLong("trip_plan_id"))
                        .build()
                )
                .attractionInfo(AttractionInfo.builder()
                        .id(rs.getInt("content_id"))
                        .build())
                .sequence(rs.getInt("sequence"))
                .createdDate(rs.getTimestamp("created_date").toLocalDateTime())
                .lastModifiedDate(rs.getTimestamp("last_modified_date").toLocalDateTime())
                .build();
    }

    private DetailPlan createJoinDetailPlan(ResultSet rs) throws SQLException {
        return DetailPlan.builder()
                .id(rs.getLong("detail_plan_id"))
                .tripPlan(createTripPlan(rs))
                .attractionInfo(AttractionInfo.builder()
                        .id(rs.getInt("content_id"))
                        .build())
                .sequence(rs.getInt("sequence"))
                .createdDate(rs.getTimestamp("created_date").toLocalDateTime())
                .lastModifiedDate(rs.getTimestamp("last_modified_date").toLocalDateTime())
                .build();
    }

    private String dateFormat(LocalDateTime dateTime) {
        if (dateTime == null) {
            return null;
        }
        return dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
