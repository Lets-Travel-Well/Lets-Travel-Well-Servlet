package com.ssafy.ltw.domain.member.model.service;

import com.ssafy.ltw.domain.member.model.Member;

public interface MemberService {
	//id 중복검사
	int idCheck(String userId) throws Exception;
	// 회원가입
	int joinMember(Member member) throws Exception;
	
	// 로그인 
	Member loginMember(String loginId, String loginPw) throws Exception;

	Member findUserNameById(Long id) throws Exception;
}
