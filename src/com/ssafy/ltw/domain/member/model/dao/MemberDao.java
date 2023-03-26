package com.ssafy.ltw.domain.member.model.dao;

import java.sql.SQLException;

import com.ssafy.ltw.domain.member.model.MemberDto;

public interface MemberDao {

	int idCheck(String userId) throws SQLException;
	int joinMember(MemberDto memberDto) throws SQLException;
	MemberDto loginMember(String userId, String userPw) throws SQLException;
}
