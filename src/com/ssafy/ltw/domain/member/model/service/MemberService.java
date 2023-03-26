package com.ssafy.ltw.domain.member.model.service;

import com.ssafy.ltw.domain.member.model.MemberDto;

public interface MemberService {
	//id 중복검사
	int idCheck(String userId) throws Exception;
	// 회원가입
	int joinMember(MemberDto memberDto) throws Exception;
	
	// 로그인 
	MemberDto loginMember(String loginId, String loginPw) throws Exception;
}
