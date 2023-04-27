package com.ssafy.goat.hotplace.repository;

import hotplace.dto.HotPlaceDetailDto;
import hotplace.dto.HotPlaceListDto;
import hotplace.dto.HotPlaceSearch;
import util.DBConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class HotPlaceQueryJdbcRepository implements HotPlaceQueryRepository {

  private static final HotPlaceQueryRepository hotPlaceQueryRepository = new HotPlaceQueryJdbcRepository();
  private final DBConnectionUtil dbConnectionUtil;

  private HotPlaceQueryJdbcRepository() {
    dbConnectionUtil = DBConnectionUtil.getInstance();
  }

  public static HotPlaceQueryRepository getHotPlaceQueryRepository() {
    return hotPlaceQueryRepository;
  }

  @Override
  public Optional<HotPlaceDetailDto> findDetailById(Long hotPlaceId) {
    HotPlaceDetailDto hotPlaceDetailDto = null;
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    try {
      conn = dbConnectionUtil.getConnection();
      String sql =
          "select hp.hot_place_id, hp.visited_date, hp.name, hp.desc, hp.store_file_name," +
              " m.member_id, m.nickname, a.title, a.first_image, a.addr1, a.zipcode, a.latitude, a.longitude"
              +
              " from hot_place hp" +
              " join member m on hp.member_id=m.member_id" +
              " join attraction_info a on hp.content_id=a.content_id" +
              " where hp.hot_place_id=?";

      pstmt = conn.prepareStatement(sql);
      pstmt.setLong(1, hotPlaceId);

      rs = pstmt.executeQuery();
      if (rs.next()) {
        hotPlaceDetailDto = HotPlaceDetailDto.builder()
            .hotPlaceId(rs.getLong("hot_place_id"))
            .visitedDate(rs.getString("visited_date"))
            .name(rs.getString("name"))
            .desc(rs.getString("desc"))
            .storeFileName(rs.getString("store_file_name"))
            .memberId(rs.getLong("member_id"))
            .nickname(rs.getString("nickname"))
            .title(rs.getString("title"))
            .firstImage(rs.getString("first_image"))
            .addr1(rs.getString("addr1"))
            .zipcode(rs.getString("zipcode"))
            .latitude(rs.getDouble("latitude"))
            .longitude(rs.getDouble("longitude"))
            .build();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      dbConnectionUtil.close(rs, pstmt, conn);
    }

    return Optional.ofNullable(hotPlaceDetailDto);
  }

  @Override
  public List<HotPlaceListDto> findByMemberId(Long memberId) {
    List<HotPlaceListDto> hotPlaces = new ArrayList<>();
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    try {
      conn = dbConnectionUtil.getConnection();
      String sql =
          "select hp.hot_place_id, hp.name, hp.desc, hp.hit, hp.store_file_name, m.nickname, hp.created_date"
              +
              " from hot_place hp" +
              " join member m" +
              " on hp.member_id = m.member_id" +
              " where m.member_id = ?" +
              " order by hp.created_date desc";

      //작성자, 제목, 내용
      pstmt = conn.prepareStatement(sql);
      pstmt.setLong(1, memberId);

      rs = pstmt.executeQuery();
      while (rs.next()) {
        HotPlaceListDto hotPlaceListDto = HotPlaceListDto.builder()
            .hotPlaceId(rs.getLong("hot_place_id"))
            .name(rs.getString("name"))
            .desc(rs.getString("desc"))
            .hit(rs.getInt("hit"))
            .storeFileName(rs.getString("store_file_name"))
            .nickname(rs.getString("nickname"))
            .createdDate(dateFormat(rs.getTimestamp("created_date").toLocalDateTime()))
            .build();
        hotPlaces.add(hotPlaceListDto);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      dbConnectionUtil.close(rs, pstmt, conn);
    }
    return hotPlaces;
  }

  @Override
  public List<HotPlaceListDto> findFavoritesByMemberId(Long memberId, int pageNum, int amount) {
    List<HotPlaceListDto> hotPlaces = new ArrayList<>();
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    try {
      conn = dbConnectionUtil.getConnection();
      String sql =
          "select hp.hot_place_id, hp.name, hp.desc, hp.hit, hp.store_file_name, m.nickname, hp.created_date"
              +
              " from hot_place hp" +
              " join favorite f" +
              " on hp.member_id = f.member_id" +
              " join member m" +
              " on f.member_id = m.member_id" +
              " where f.member_id = ?" +
              " order by hp.created_date desc";

      sql += " limit ?, ?";

      //작성자, 제목, 내용
      pstmt = conn.prepareStatement(sql);
      pstmt.setLong(1, memberId);
      pstmt.setInt(2, pageNum);
      pstmt.setInt(3, amount);

      rs = pstmt.executeQuery();
      while (rs.next()) {
        HotPlaceListDto hotPlaceListDto = HotPlaceListDto.builder()
            .hotPlaceId(rs.getLong("hot_place_id"))
            .name(rs.getString("name"))
            .desc(rs.getString("desc"))
            .hit(rs.getInt("hit"))
            .storeFileName(rs.getString("store_file_name"))
            .nickname(rs.getString("nickname"))
            .createdDate(dateFormat(rs.getTimestamp("created_date").toLocalDateTime()))
            .build();
        hotPlaces.add(hotPlaceListDto);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      dbConnectionUtil.close(rs, pstmt, conn);
    }
    return hotPlaces;
  }

  @Override
  public int doFavorite(Long memberId, Long hotPlaceId) {
    Connection conn = null;
    PreparedStatement pstmt = null;
    int result = 0;
    ResultSet rs = null;
    try {
      conn = dbConnectionUtil.getConnection();
      String sql = "insert into favorite(member_id, hotplace_id)" +
          " values ( ?, ? )";

      //작성자, 제목, 내용
      pstmt = conn.prepareStatement(sql);
      pstmt.setLong(1, memberId);
      pstmt.setLong(2, hotPlaceId);

      result = pstmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      dbConnectionUtil.close(rs, pstmt, conn);
    }
    return result;
  }


  @Override
  public List<HotPlaceListDto> findByCondition(HotPlaceSearch condition) {
    List<HotPlaceListDto> hotPlaces = new ArrayList<>();
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    try {
      conn = dbConnectionUtil.getConnection();
      String sql =
          "select hp.hot_place_id, hp.name, hp.desc, hp.hit, hp.store_file_name, m.nickname, hp.created_date"
              +
              " from hot_place hp" +
              " join member m" +
              " on hp.member_id = m.member_id" +
              " where m.nickname like ?" +
              " or hp.name like ?" +
              " or hp.desc like ?";

      if (condition.getSortCondition() == 2) {
        sql += " order by hp.hit desc";
      } else {
        sql += " order by hp.created_date desc";
      }

      //작성자, 제목, 내용
      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, '%' + condition.getName() + '%');
      pstmt.setString(2, '%' + condition.getName() + '%');
      pstmt.setString(3, '%' + condition.getName() + '%');

      rs = pstmt.executeQuery();
      while (rs.next()) {
        HotPlaceListDto hotPlaceListDto = HotPlaceListDto.builder()
            .hotPlaceId(rs.getLong("hot_place_id"))
            .name(rs.getString("name"))
            .desc(rs.getString("desc"))
            .hit(rs.getInt("hit"))
            .storeFileName(rs.getString("store_file_name"))
            .nickname(rs.getString("nickname"))
            .createdDate(dateFormat(rs.getTimestamp("created_date").toLocalDateTime()))
            .build();
        hotPlaces.add(hotPlaceListDto);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      dbConnectionUtil.close(rs, pstmt, conn);
    }
    return hotPlaces;
  }

  private String dateFormat(LocalDateTime dateTime) {
    if (dateTime == null) {
      return null;
    }
    return dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
  }
}
