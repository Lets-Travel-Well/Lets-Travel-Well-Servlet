package com.ssafy.ltw.domain.member.model.dao;

import com.ssafy.ltw.domain.member.model.Salt;
import com.ssafy.ltw.global.util.db.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SaltDaoImpl implements SaltDao{
    private static SaltDao instance = new SaltDaoImpl();
    private DBUtil dbUtil;
    private SaltDaoImpl(){
        dbUtil = DBUtil.getInstance();
    }
    public static SaltDao getInstance(){
        return instance;
    }
    @Override
    public int createSalt(Long memberId, String salt) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = dbUtil.getConnection();
            StringBuilder sql = new StringBuilder();
            sql.append("insert into salt(member_id, salt) \n");
            sql.append("values (?,?)");

            pstmt = conn.prepareStatement(sql.toString());
            pstmt.setLong(1, memberId);
            pstmt.setString(2, salt);

            return pstmt.executeUpdate();
        } finally {
            dbUtil.close(pstmt, conn);
        }
    }

    @Override
    public Salt findByMember(Long memberId) throws SQLException {
        Salt salt = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try{
            conn = dbUtil.getConnection();
            StringBuilder sql = new StringBuilder();
            sql.append("select * \n");
            sql.append("from Salt \n");
            sql.append("where member_id = ?");

            pstmt = conn.prepareStatement(sql.toString());
            pstmt.setLong(1, memberId);
            rs = pstmt.executeQuery();
            if(rs.next()){
                salt = new Salt().builder()
                        .id(rs.getLong("id"))
                        .memberId(rs.getLong("member_id"))
                        .salt(rs.getString("salt"))
                        .build();
            }
        } finally {
            dbUtil.close(rs, pstmt, conn);
        }
        return salt;
    }
}
