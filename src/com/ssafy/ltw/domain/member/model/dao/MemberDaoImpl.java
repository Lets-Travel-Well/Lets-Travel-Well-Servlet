package com.ssafy.ltw.domain.member.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ssafy.ltw.domain.member.model.MemberDto;
import com.ssafy.ltw.global.util.DBUtil;

public class MemberDaoImpl implements MemberDao{

	private static MemberDao memberDao;
	private DBUtil dbUtil;
	
	private MemberDaoImpl()  {
		dbUtil = DBUtil.getInstance();
	}
	
	public static MemberDao getMemberDao() {
		if(memberDao != null) {
			return memberDao;
		}
		return new MemberDaoImpl();
	}

	@Override
	public int idCheck(String userId) throws SQLException {
		MemberDto memberDto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = dbUtil.getConnection();
			StringBuilder idCheck = new StringBuilder();
			idCheck.append("select * \n");
			idCheck.append("from member \n");
			idCheck.append("where login_id = ?");
			pstmt = conn.prepareStatement(idCheck.toString());
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return 1;
			} 
		} finally {
			dbUtil.close(pstmt, conn);
		}
		return 0;
	}

	@Override
	public int joinMember(MemberDto memberDto) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dbUtil.getConnection();
			StringBuilder join = new StringBuilder();
			join.append("insert into member(login_id, login_pw, username, email, phone) \n");
			join.append("values (?, ?, ?, ?, ?)");
			
			pstmt = conn.prepareStatement(join.toString());
			pstmt.setString(1, memberDto.getLoginId());
			pstmt.setString(2, memberDto.getLoginPw());
			pstmt.setString(3, memberDto.getUsername());
			pstmt.setString(4, memberDto.getEmail());
			pstmt.setString(5, memberDto.getPhone());
			
			return pstmt.executeUpdate();
		} finally {
			dbUtil.close(pstmt, conn);
		}
	}
}
