package com.ssafy.ltw.domain.member.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ssafy.ltw.domain.member.model.Member;
import com.ssafy.ltw.global.util.db.DBUtil;

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
		Member member = null;
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
	public int joinMember(Member member) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dbUtil.getConnection();
			StringBuilder join = new StringBuilder();
			join.append("insert into member(login_id, login_pw, username, email, phone) \n");
			join.append("values (?, ?, ?, ?, ?)");
			
			pstmt = conn.prepareStatement(join.toString());
			pstmt.setString(1, member.getLoginId());
			pstmt.setString(2, member.getLoginPw());
			pstmt.setString(3, member.getUsername());
			pstmt.setString(4, member.getEmail());
			pstmt.setString(5, member.getPhone());
			
			return pstmt.executeUpdate();
		} finally {
			dbUtil.close(pstmt, conn);
		}
	}

	@Override
	public Member loginMember(String userId, String userPw) throws SQLException {
		Member member = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = dbUtil.getConnection();
			StringBuilder loginMember = new StringBuilder();
			loginMember.append("select login_id, username \n");
			loginMember.append("from member \n");
			loginMember.append("where login_id = ? and login_pw = ? \n");
			pstmt = conn.prepareStatement(loginMember.toString());
			pstmt.setString(1, userId);
			pstmt.setString(2, userPw);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				member = new Member();
				member.setLoginId(rs.getString("login_id"));
				member.setUsername(rs.getString("username"));
			}
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}
		return member;
	}

	@Override
	public Member findUserNameById(Long id) throws SQLException {
		Member member = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = dbUtil.getConnection();
			StringBuilder loginMember = new StringBuilder();
			loginMember.append("select * \n");
			loginMember.append("from member \n");
			loginMember.append("where id = ?\n");/*
			/*
			select username
			from member
			where id = 1;
			*/
			pstmt = conn.prepareStatement(loginMember.toString());
			pstmt.setLong(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				member = new Member().builder()
						.id(id)
						.loginId(rs.getString("login_id"))
						.loginPw(rs.getString("login_pw"))
						.username(rs.getString("username"))
						.email(rs.getString("email"))
						.phone(rs.getString("phone"))
						.build();
			}
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}
		return member;
	}

	@Override
	public long findIdByUserId(String userId) throws SQLException {
		long findId = -1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = dbUtil.getConnection();
			StringBuilder loginMember = new StringBuilder();
			loginMember.append("select id \n");
			loginMember.append("from member \n");
			loginMember.append("where login_id = ?\n");/*
			/*
			select username
			from member
			where id = 1;
			*/
			pstmt = conn.prepareStatement(loginMember.toString());
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				findId = rs.getLong("id");
			}
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}
		return findId;
	}
}
