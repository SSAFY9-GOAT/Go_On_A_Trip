package com.ssafy.goat.attraction.repository;

import attraction.Sido;
import util.DBConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SidoJdbcRepository implements SidoRepository {

    private static final SidoRepository sidoRepository = new SidoJdbcRepository();
    private final DBConnectionUtil dbConnectionUtil;

    private SidoJdbcRepository() {
        dbConnectionUtil = DBConnectionUtil.getInstance();
    }

    public static SidoRepository getSidoRepository() {
        return sidoRepository;
    }

    @Override
    public List<Sido> findAll() {
        List<Sido> sidos = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = dbConnectionUtil.getConnection();
            String sql = "select * from sido;";

            pstmt = conn.prepareStatement(sql);

            rs = pstmt.executeQuery();
            while (rs.next()) {
                sidos.add(createSido(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnectionUtil.close(rs, pstmt, conn);
        }
        return sidos;
    }

    private Sido createSido(ResultSet rs) throws SQLException {
        return Sido.builder()
                .code(rs.getInt("sido_code"))
                .name(rs.getString("sido_name"))
                .build();
    }
}
