package com.ssafy.ltw.domain.member.model.dao;

import java.sql.SQLException;

import com.ssafy.ltw.domain.member.model.Member;

public interface MemberDao {

	int idCheck(String userId) throws SQLException;
	int joinMember(Member member) throws SQLException;
	Member loginMember(String userId, String userPw) throws SQLException;

	Member findUserNameById(Long id) throws SQLException;

    long findIdByUserId(String userId) throws SQLException;
    
    void modifyMember(Member member) throws SQLException;
}
