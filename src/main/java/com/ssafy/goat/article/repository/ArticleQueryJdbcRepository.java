package com.ssafy.goat.article.repository;



import com.ssafy.goat.article.dto.ArticleDetailDto;
import com.ssafy.goat.article.dto.ArticleListDto;
import com.ssafy.goat.article.dto.ArticleSearch;
import com.ssafy.goat.util.DBConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ArticleQueryJdbcRepository implements ArticleQueryRepository {

    private static final ArticleQueryRepository articleQueryRepository = new ArticleQueryJdbcRepository();
    private final DBConnectionUtil dbConnectionUtil;

    private ArticleQueryJdbcRepository() {
        dbConnectionUtil = DBConnectionUtil.getInstance();
    }

    public static ArticleQueryRepository getArticleQueryRepository() {
        return articleQueryRepository;
    }

    @Override
    public Optional<ArticleDetailDto> findDetailById(Long articleId) {
        ArticleDetailDto articleDetailDto = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = dbConnectionUtil.getConnection();
            String sql = "select a.article_id, a.title, a.content, a.created_date, m.member_id, m.nickname" +
                    " from article a" +
                    " join member m on a.member_id=m.member_id" +
                    " where a.article_id=?";

            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, articleId);

            rs = pstmt.executeQuery();
            if (rs.next()) {
                articleDetailDto = ArticleDetailDto.builder()
                        .articleId(rs.getLong("article_id"))
                        .title(rs.getString("title"))
                        .content(rs.getString("content"))
                        .createdDate(dateFormat(rs.getTimestamp("created_date").toLocalDateTime()))
                        .memberId(rs.getLong("member_id"))
                        .nickname(rs.getString("nickname"))
                        .build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnectionUtil.close(rs, pstmt, conn);
        }

        return Optional.ofNullable(articleDetailDto);
    }

    @Override
    public List<ArticleListDto> findListByCondition(ArticleSearch condition, int pageNum, int amount) {
        List<ArticleListDto> articleListDtos = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = dbConnectionUtil.getConnection();
            String sql = "select a.article_id, a.title, a.created_date" +
                    " from article a" +
                    " join member m" +
                    " on a.member_id=m.member_id" +
                    " where m.nickname like ? or a.title like ? or a.content like ?";
            if (condition.getSortCondition() == 2) {
                sql += " order by hit desc";
            } else {
                sql += " order by created_date desc";
            }
            sql += " limit ?, ?";

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, '%' + condition.getCondition() + '%');
            pstmt.setString(2, '%' + condition.getCondition() + '%');
            pstmt.setString(3, '%' + condition.getCondition() + '%');
            pstmt.setInt(4, (pageNum - 1) * amount);
            pstmt.setInt(5, amount);

            rs = pstmt.executeQuery();
            while (rs.next()) {
                ArticleListDto articleListDto = ArticleListDto.builder()
                        .articleId(rs.getLong("article_id"))
                        .title(rs.getString("title"))
                        .createdDate(dateFormat(rs.getTimestamp("created_date").toLocalDateTime()))
                        .build();
                articleListDtos.add(articleListDto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnectionUtil.close(rs, pstmt, conn);
        }

        return articleListDtos;
    }

    @Override
    public List<ArticleListDto> findListByMemberId(Long memberId, int pageNum, int amount) {
        List<ArticleListDto> articleListDtos = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = dbConnectionUtil.getConnection();
            String sql = "select a.article_id, a.title, a.created_date" +
                    " from article a" +
                    " join member m" +
                    " on a.member_id=m.member_id" +
                    " where a.member_id = ? " +
                    "order by created_date desc";

            sql += " limit ?, ?";

            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, memberId);
            pstmt.setInt(2, (pageNum - 1) * amount);
            pstmt.setInt(3, amount);

            rs = pstmt.executeQuery();
            while (rs.next()) {
                ArticleListDto articleListDto = ArticleListDto.builder()
                        .articleId(rs.getLong("article_id"))
                        .title(rs.getString("title"))
                        .createdDate(dateFormat(rs.getTimestamp("created_date").toLocalDateTime()))
                        .build();
                articleListDtos.add(articleListDto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnectionUtil.close(rs, pstmt, conn);
        }

        return articleListDtos;
    }


    @Override
    public int findTotalCount() {
        int result = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = dbConnectionUtil.getConnection();
            String sql = "select count(*) as total from article;";

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

    private String dateFormat(LocalDateTime dateTime) {
        if (dateTime == null) {
            return null;
        }
        return dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
