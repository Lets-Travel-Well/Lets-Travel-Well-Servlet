package com.ssafy.ltw.domain.attraction.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ssafy.ltw.domain.attraction.model.AttractionInfo;
import com.ssafy.ltw.domain.attraction.model.Gugun;
import com.ssafy.ltw.domain.attraction.model.Sido;
import com.ssafy.ltw.global.util.db.DBUtil;

public class AttractionDaoImpl implements AttractionDao{
    private static AttractionDao attractionDao;

    private DBUtil dbUtil;

    private AttractionDaoImpl(){
        dbUtil = DBUtil.getInstance();
    }

    public static AttractionDao getAttractionDao() {
        if(attractionDao == null)
            attractionDao = new AttractionDaoImpl();
        return attractionDao;
    }


    @Override
    public AttractionInfo findById(int contentId) {
        return null;
    }

	@Override
	public List<Sido> listSidos() throws SQLException{
        List<Sido> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = dbUtil.getConnection();
            StringBuilder sql = new StringBuilder();
            sql.append("select * \n");
            sql.append("from sido \n");
            pstmt = conn.prepareStatement(sql.toString());
            rs = pstmt.executeQuery();

            while(rs.next()) {
                Sido findSido  = new Sido().builder()
                		.sidoCode(rs.getInt("sido_code"))
                		.sidoName(rs.getString("sido_name"))
                		.build();
                list.add(findSido);
            }
        } finally {
            dbUtil.close(rs, pstmt, conn);
        }
        return list;
	}

	@Override
	public List<Gugun> listGuguns(int sidoCode) throws SQLException {
        List<Gugun> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = dbUtil.getConnection();
            StringBuilder sql = new StringBuilder();
            sql.append("select * \n");
            sql.append("from gugun \n");
            sql.append("where sido_code = ? ");
            pstmt = conn.prepareStatement(sql.toString());
            pstmt.setInt(1, sidoCode);
            rs = pstmt.executeQuery();

            while(rs.next()) {
                Gugun findGugun  = new Gugun().builder()
                		.gugunCode(rs.getInt("gugun_code"))
                		.gugunName(rs.getString("gugun_name"))
                		.sidoCode(rs.getInt("sido_code"))
                		.build();
                list.add(findGugun);
            }
        } finally {
            dbUtil.close(rs, pstmt, conn);
        }
        return list;
	}

	@Override
	public List<AttractionInfo> listAttractionInfoByCriterial(int contentTypeId, int sidoCode, int gugunCode)
			throws SQLException {
		
        List<AttractionInfo> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = dbUtil.getConnection();
            StringBuilder sql = new StringBuilder();
            sql.append("select * \n");
            sql.append("from attraction_info \n");
            sql.append("where content_type_id = ? and sido_code = ? and gugun_code = ?");
            pstmt = conn.prepareStatement(sql.toString());
            pstmt.setInt(1, contentTypeId);
            pstmt.setInt(2, sidoCode);
            pstmt.setInt(3, gugunCode);
            rs = pstmt.executeQuery();
         // title, addr1, zipcode, first_image, latitude, longitude, 
            while(rs.next()) {
            	AttractionInfo attractionInfo = new AttractionInfo().builder()
            			.title(rs.getString("title"))
            			.addr1(rs.getString("addr1"))
            			.zipcode(rs.getString("zipcode"))
            			.firstImage(rs.getString("first_image"))
            			.latitude(rs.getDouble("latitude"))
            			.longitude(rs.getDouble("longitude"))
            			.build();
                list.add(attractionInfo);
            }
        } finally {
            dbUtil.close(rs, pstmt, conn);
        }
        return list;
	}
}
