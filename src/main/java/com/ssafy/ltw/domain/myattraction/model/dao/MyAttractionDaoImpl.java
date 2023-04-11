package com.ssafy.ltw.domain.myattraction.model.dao;

import com.ssafy.ltw.domain.myattraction.model.MyAttraction;
import com.ssafy.ltw.global.util.db.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MyAttractionDaoImpl implements MyAttractionDao{
    private static MyAttractionDao myAttractionDao;
    private DBUtil dbUtil;
    private MyAttractionDaoImpl(){
        dbUtil = DBUtil.getInstance();
    }
    public static MyAttractionDao getInstance(){
        if(myAttractionDao == null){
            myAttractionDao = new MyAttractionDaoImpl();
        }
        return myAttractionDao;
    }
    @Override
    public int writeMyAttraction(Long memberId, int attractionInfoId) throws SQLException {
        int res = -1;
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = dbUtil.getConnection();
            StringBuilder sql = new StringBuilder();
            sql.append("insert into My_Attraction (member_id, attraction_id) \n");
            sql.append("values (?, ?)");

            pstmt = conn.prepareStatement(sql.toString());
            pstmt.setLong(1, memberId);
            pstmt.setInt(2, attractionInfoId);

            res = pstmt.executeUpdate();
        } finally {
            dbUtil.close(pstmt, conn);
        }
        return res;
    }

    @Override
    public void removeMyAttraction(Long myAttractionId) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = dbUtil.getConnection();
            StringBuilder sql = new StringBuilder();
            sql.append("delete from My_Attraction \n");
            sql.append("where id = ?");
            pstmt = conn.prepareStatement(sql.toString());
            pstmt.setLong(1, myAttractionId);
            pstmt.executeUpdate();
        } finally {
            dbUtil.close(pstmt, conn);
        }
    }

    @Override
    public MyAttraction findByMemberIdAndAttractionInfoId(Long memberId, int attractionInfoId) throws SQLException {
        MyAttraction findMyAttraction = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = dbUtil.getConnection();
            StringBuilder sql = new StringBuilder();
            sql.append("select *\n");
            sql.append("from My_Attraction \n");
            sql.append("where member_Id = ? and attraction_id = ?");
            pstmt = conn.prepareStatement(sql.toString());
            pstmt.setLong(1, memberId);
            pstmt.setLong(2, attractionInfoId);
            rs = pstmt.executeQuery();
            if(rs.next()) {
                findMyAttraction = new MyAttraction().builder()
                        .id(rs.getLong("id"))
                        .createdDate(rs.getString("created_date"))
                        .modifiedDate(rs.getString("modified_date"))
                        .memberId(rs.getLong("member_id"))
                        .attractionInfoId(rs.getLong("attraction_id"))
                        .build();
            }
        } finally {
            dbUtil.close(rs, pstmt, conn);
        }
        return findMyAttraction;
    }

    @Override
    public List<MyAttraction> findAllByMemberId(Long memberId) throws SQLException {
        List<MyAttraction> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = dbUtil.getConnection();
            StringBuilder sql = new StringBuilder();
            sql.append("select *\n");
            sql.append("from My_Attraction \n");
            sql.append("where memberId = ?");
            pstmt = conn.prepareStatement(sql.toString());
            pstmt.setLong(1, memberId);
            rs = pstmt.executeQuery();
            while (rs.next()){

                MyAttraction findMyAttraction = new MyAttraction().builder()
                        .id(rs.getLong("id"))
                        .createdDate(rs.getString("created_date"))
                        .modifiedDate(rs.getString("modified_date"))
                        .memberId(rs.getLong("member_id"))
                        .attractionInfoId(rs.getLong("attraction_id"))
                        .build();
                list.add(findMyAttraction);
            }
        } finally {
            dbUtil.close(rs, pstmt, conn);
        }
        return list;
    }
}
