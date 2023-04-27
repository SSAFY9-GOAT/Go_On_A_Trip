package com.ssafy.goat.trend.repository;

import attraction.AttractionInfo;
import trend.Trend;
import trend.dto.TrendViewDto;
import util.DBConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class TrendJdbcRepository implements TrendRepository {

    private static final TrendRepository trendRepository = new TrendJdbcRepository();
    private final DBConnectionUtil dbConnectionUtil;

    private TrendJdbcRepository() {
        dbConnectionUtil = DBConnectionUtil.getInstance();
    }

    public static TrendRepository getTrendRepository() {
        return trendRepository;
    }

    @Override
    public int save(int contentId) {
        int count = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = dbConnectionUtil.getConnection();
            String sql = "insert into trend(content_id) values(?)";

            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, contentId);

            count = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnectionUtil.close(pstmt, conn);
        }
        return count;
    }

    @Override
    public Optional<Trend> findByContentId(int contentId) {
        Trend trend = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = dbConnectionUtil.getConnection();
            String sql = "";

            pstmt = conn.prepareStatement(sql);

            rs = pstmt.executeQuery();
            if (rs.next()) {
                trend = Trend.builder()
                        .id(rs.getLong("trend_id"))
                        .teenage(rs.getInt("teenage"))
                        .twenty(rs.getInt("twenty"))
                        .thirty(rs.getInt("thirty"))
                        .male(rs.getInt("male"))
                        .female(rs.getInt("female"))
                        .content(new AttractionInfo(rs.getInt("content_id")))
                        .build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnectionUtil.close(rs, pstmt, conn);
        }
        return Optional.ofNullable(trend);
    }

    @Override
    public TrendViewDto findPopularByTeenage() {
        TrendViewDto trend = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = dbConnectionUtil.getConnection();
            String sql = "select * from trend t join attraction_info a on t.content_id=a.content_id order by teenage desc limit 0, 1";

            pstmt = conn.prepareStatement(sql);

            rs = pstmt.executeQuery();
            if (rs.next()) {
                trend = TrendViewDto.builder()
                        .title(rs.getString("title"))
                        .firstImage(rs.getString("first_image"))
                        .addr1(rs.getString("addr1"))
                        .zipcode(rs.getString("zipcode"))
                        .build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnectionUtil.close(rs, pstmt, conn);
        }
        return trend;
    }

    @Override
    public TrendViewDto findPopularByTwenty() {
        TrendViewDto trend = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = dbConnectionUtil.getConnection();
            String sql = "select * from trend t join attraction_info a on t.content_id=a.content_id order by twenty desc limit 0, 1";

            pstmt = conn.prepareStatement(sql);

            rs = pstmt.executeQuery();
            if (rs.next()) {
                trend = TrendViewDto.builder()
                        .title(rs.getString("title"))
                        .firstImage(rs.getString("first_image"))
                        .addr1(rs.getString("addr1"))
                        .zipcode(rs.getString("zipcode"))
                        .build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnectionUtil.close(rs, pstmt, conn);
        }
        return trend;
    }

    @Override
    public TrendViewDto findPopularByThirty() {
        TrendViewDto trend = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = dbConnectionUtil.getConnection();
            String sql = "select * from trend t join attraction_info a on t.content_id=a.content_id order by thirty desc limit 0, 1";

            pstmt = conn.prepareStatement(sql);

            rs = pstmt.executeQuery();
            if (rs.next()) {
                trend = TrendViewDto.builder()
                        .title(rs.getString("title"))
                        .firstImage(rs.getString("first_image"))
                        .addr1(rs.getString("addr1"))
                        .zipcode(rs.getString("zipcode"))
                        .build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnectionUtil.close(rs, pstmt, conn);
        }
        return trend;
    }

    @Override
    public TrendViewDto findPopularByMale() {
        TrendViewDto trend = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = dbConnectionUtil.getConnection();
            String sql = "select * from trend t join attraction_info a on t.content_id=a.content_id order by male desc limit 0, 1";

            pstmt = conn.prepareStatement(sql);

            rs = pstmt.executeQuery();
            if (rs.next()) {
                trend = TrendViewDto.builder()
                        .title(rs.getString("title"))
                        .firstImage(rs.getString("first_image"))
                        .addr1(rs.getString("addr1"))
                        .zipcode(rs.getString("zipcode"))
                        .build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnectionUtil.close(rs, pstmt, conn);
        }
        return trend;
    }

    @Override
    public TrendViewDto findPopularByFemale() {
        TrendViewDto trend = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = dbConnectionUtil.getConnection();
            String sql = "select * from trend t join attraction_info a on t.content_id=a.content_id order by female desc limit 0, 1";

            pstmt = conn.prepareStatement(sql);

            rs = pstmt.executeQuery();
            if (rs.next()) {
                trend = TrendViewDto.builder()
                        .title(rs.getString("title"))
                        .firstImage(rs.getString("first_image"))
                        .addr1(rs.getString("addr1"))
                        .zipcode(rs.getString("zipcode"))
                        .build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnectionUtil.close(rs, pstmt, conn);
        }
        return trend;
    }

    @Override
    public int update(Trend trend) {
        int count = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = dbConnectionUtil.getConnection();
            String sql = "update trend" +
                    " set teenage=?, twenty=?, thirty=?, male=?, female=?" +
                    " where content_id=?";

            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, trend.getTeenage());
            pstmt.setInt(2, trend.getTwenty());
            pstmt.setInt(3, trend.getThirty());
            pstmt.setInt(4, trend.getMale());
            pstmt.setInt(5, trend.getFemale());
            pstmt.setLong(6, trend.getContent().getId());

            count = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnectionUtil.close(pstmt, conn);
        }
        return count;
    }
}
