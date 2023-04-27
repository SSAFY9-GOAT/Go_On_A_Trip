package com.ssafy.goat.member.repository;

import member.Member;
import member.dto.MemberAddDto;
import util.DBConnectionUtil;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.Optional;

import static member.Authority.ADMIN;
import static member.Authority.CLIENT;

public class MemberJdbcRepository implements MemberRepository {

    private static final MemberRepository memberRepository = new MemberJdbcRepository();
    private final DBConnectionUtil dbConnectionUtil;

    private MemberJdbcRepository() {
        dbConnectionUtil = DBConnectionUtil.getInstance();
    }

    public static MemberRepository getMemberRepository() {
        return memberRepository;
    }

    @Override
    public int save(Member member) {
        int count = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = dbConnectionUtil.getConnection();
            String sql = "insert into member(login_id, login_pw, username, email, phone, birth, gender, nickname, nickname_last_modified_date, authority)" +
                    " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, member.getLoginId());
            pstmt.setString(2, member.getLoginPw());
            pstmt.setString(3, member.getUsername());
            pstmt.setString(4, member.getEmail());
            pstmt.setString(5, member.getPhone());
            pstmt.setString(6, member.getBirth());
            pstmt.setString(7, member.getGender());
            pstmt.setString(8, member.getNickname());
            pstmt.setTimestamp(9, Timestamp.valueOf(LocalDateTime.now().minusDays(60)));
            pstmt.setString(10, member.getAuthority().toString());

            count = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnectionUtil.close(pstmt, conn);
        }
        return count;
    }

    @Override
    public Optional<Member> findById(Long memberId) {
        Member member = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = dbConnectionUtil.getConnection();
            String sql = "select * from member where member_id = ?";

            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, memberId);

            rs = pstmt.executeQuery();
            if (rs.next()) {
                member = createMember(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnectionUtil.close(rs, pstmt, conn);
        }
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByLoginId(String loginId) {
        Member member = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = dbConnectionUtil.getConnection();
            String sql = "select * from member where login_id = ?";

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, loginId);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                member = createMember(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnectionUtil.close(rs, pstmt, conn);
        }
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByLoginIdAndLoginPw(String loginId, String loginPw) {
        Member member = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = dbConnectionUtil.getConnection();
            String sql = "select * from member where login_id = ? and login_pw = ?";

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, loginId);
            pstmt.setString(2, loginPw);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                member = createMember(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnectionUtil.close(rs, pstmt, conn);
        }
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> duplicateSignup(MemberAddDto memberAddDto) {
        Member member = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = dbConnectionUtil.getConnection();
            String sql = "select * from member where login_id = ? or email = ? or phone = ? or nickname = ?";

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, memberAddDto.getLoginId());
            pstmt.setString(2, memberAddDto.getEmail());
            pstmt.setString(3, memberAddDto.getPhone());
            pstmt.setString(4, memberAddDto.getNickname());

            rs = pstmt.executeQuery();

            if (rs.next()) {
                member = createMember(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnectionUtil.close(rs, pstmt, conn);
        }
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByEmail(String email) {
        Member member = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = dbConnectionUtil.getConnection();
            String sql = "select * from member where email = ?";

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, email);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                member = createMember(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnectionUtil.close(rs, pstmt, conn);
        }
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByPhone(String phone) {
        Member member = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = dbConnectionUtil.getConnection();
            String sql = "select * from member where phone = ?";

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, phone);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                member = createMember(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnectionUtil.close(rs, pstmt, conn);
        }
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByNickname(String nickname) {
        Member member = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = dbConnectionUtil.getConnection();
            String sql = "select * from member where nickname = ?";

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, nickname);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                member = createMember(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnectionUtil.close(rs, pstmt, conn);
        }
        return Optional.ofNullable(member);
    }

    @Override
    public int update(Long memberId, Member member) {
        int count = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = dbConnectionUtil.getConnection();
            String sql = "update member" +
                    " set login_pw=?, email=?, phone=?, nickname=?, nickname_last_modified_date=?, last_modified_date=?" +
                    " where member_id=?";

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, member.getLoginPw());
            pstmt.setString(2, member.getEmail());
            pstmt.setString(3, member.getPhone());
            pstmt.setString(4, member.getNickname());
            pstmt.setTimestamp(5, Timestamp.valueOf(member.getNicknameLastModifiedDate()));
            pstmt.setTimestamp(6, Timestamp.valueOf(member.getLastModifiedDate()));
            pstmt.setLong(7, memberId);

            count = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnectionUtil.close(pstmt, conn);
        }
        return count;
    }

    @Override
    public int remove(Long memberId) {
        int count = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = dbConnectionUtil.getConnection();
            String sql = "delete from member where member_id=?";

            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, memberId);

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
            String sql = "delete from member";

            pstmt = conn.prepareStatement(sql);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnectionUtil.close(pstmt, conn);
        }
    }

    private Member createMember(ResultSet rs) throws SQLException {
        return Member.builder()
                .id(rs.getLong("member_id"))
                .loginId(rs.getString("login_id"))
                .loginPw(rs.getString("login_pw"))
                .username(rs.getString("username"))
                .email(rs.getString("email"))
                .phone(rs.getString("phone"))
                .birth(rs.getString("birth"))
                .gender(rs.getString("gender"))
                .nickname(rs.getString("nickname"))
                .nicknameLastModifiedDate(
                        rs.getTimestamp("nickname_last_modified_date").toLocalDateTime()
                )
                .authority(
                        rs.getString("authority").equals("CLIENT") ? CLIENT : ADMIN
                )
                .createdDate(rs.getTimestamp("created_date").toLocalDateTime())
                .lastModifiedDate(rs.getTimestamp("last_modified_date").toLocalDateTime())
                .build();
    }
}
