package com.ssafy.goat.attraction.repository;

import attraction.Gugun;
import attraction.Sido;
import util.DBConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GugunJdbcRepository implements GugunRepository {

    private static final GugunRepository gugunRepository = new GugunJdbcRepository();
    private final DBConnectionUtil dbConnectionUtil;

    private GugunJdbcRepository() {
        dbConnectionUtil = DBConnectionUtil.getInstance();
    }

    public static GugunRepository getGugunRepository() {
        return gugunRepository;
    }

    @Override
    public List<Gugun> findBySidoCode(int sidoCode) {
        List<Gugun> guguns = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = dbConnectionUtil.getConnection();
            String sql = "select * from gugun where sido_code=?;";

            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, sidoCode);

            rs = pstmt.executeQuery();
            while (rs.next()) {
                guguns.add(createGugun(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnectionUtil.close(rs, pstmt, conn);
        }
        return guguns;
    }

    private Gugun createGugun(ResultSet rs) throws SQLException {
        return Gugun.builder()
                .code(rs.getInt("gugun_code"))
                .name(rs.getString("gugun_name"))
                .sido(Sido.builder()
                        .code(rs.getInt("sido_code"))
                        .build())
                .build();
    }
}
