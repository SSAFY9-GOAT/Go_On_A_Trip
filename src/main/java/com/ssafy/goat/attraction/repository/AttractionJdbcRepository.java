package com.ssafy.goat.attraction.repository;

import attraction.AttractionInfo;
import attraction.dto.AttractionSearch;
import util.DBConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AttractionJdbcRepository  implements AttractionRepository {

    private static final AttractionRepository attractionRepository = new AttractionJdbcRepository();
    private final DBConnectionUtil dbConnectionUtil;

    private AttractionJdbcRepository() {
        dbConnectionUtil = DBConnectionUtil.getInstance();
    }

    public static AttractionRepository getAttractionRepository() {
        return attractionRepository;
    }


    @Override
    public Optional<AttractionInfo> findById(int contentId) {
        AttractionInfo attractionInfo = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = dbConnectionUtil.getConnection();
            String sql = "select * from attraction_info where content_id = ?";

            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, contentId);

            rs = pstmt.executeQuery();
            if (rs.next()) {
                attractionInfo = createAttractionInfo(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnectionUtil.close(rs, pstmt, conn);
        }
        return Optional.ofNullable(attractionInfo);
    }

    @Override
    public List<AttractionInfo> findByConditions(AttractionSearch condition) {
        List<AttractionInfo> attractionInfos = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = dbConnectionUtil.getConnection();
            String sql = "select * from attraction_info" +
                    " where sido_code=? and gugun_code=? and content_type_id=?";

            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, condition.getSidoCode());
            pstmt.setInt(2, condition.getGugunCode());
            pstmt.setInt(3, condition.getContentTypeId());

            rs = pstmt.executeQuery();
            while (rs.next()) {
                attractionInfos.add(createAttractionInfo(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnectionUtil.close(rs, pstmt, conn);
        }
        return attractionInfos;
    }

    @Override
    public List<AttractionInfo> findByTitle(String title) {
        List<AttractionInfo> attractionInfos = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = dbConnectionUtil.getConnection();
            String sql = "select * from attraction_info where title like ?";

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, '%' + title + '%');

            rs = pstmt.executeQuery();
            while (rs.next()) {
                attractionInfos.add(createAttractionInfo(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnectionUtil.close(rs, pstmt, conn);
        }
        return attractionInfos;
    }

    @Override
    public List<AttractionInfo> findByContentIds(List<Integer> contentIds) {
        List<AttractionInfo> attractionInfos = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = dbConnectionUtil.getConnection();
            String sql = "select * from attraction_info where content_id in ";
            StringBuilder sb = new StringBuilder();
            sb.append("(");
            for (int contentId : contentIds) {
                sb.append("?").append(",");
            }
            sb.setCharAt(sb.length() - 1, ')');
            sql += sb;

            pstmt = conn.prepareStatement(sql);
            for (int i = 0; i < contentIds.size(); i++) {
                pstmt.setInt(i + 1, contentIds.get(i));
            }

            rs = pstmt.executeQuery();
            while (rs.next()) {
                attractionInfos.add(createAttractionInfo(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnectionUtil.close(rs, pstmt, conn);
        }
        return attractionInfos;
    }

    private AttractionInfo createAttractionInfo(ResultSet rs) throws SQLException {
        return AttractionInfo.builder()
                .id(rs.getInt("content_id"))
                .contentTypeId(rs.getInt("content_type_id"))
                .title(rs.getString("title"))
                .addr1(rs.getString("addr1"))
                .addr2(rs.getString("addr2"))
                .zipcode(rs.getString("zipcode"))
                .tel(rs.getString("tel"))
                .firstImage(rs.getString("first_image"))
                .firstImage2(rs.getString("first_image2"))
                .readCount(rs.getInt("readcount"))
                .latitude(rs.getDouble("latitude"))
                .longitude(rs.getDouble("longitude"))
                .mlevel(rs.getString("mlevel"))
                .build();
    }
}
