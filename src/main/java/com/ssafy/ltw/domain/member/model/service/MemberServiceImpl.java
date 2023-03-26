package com.ssafy.ltw.domain.member.model.service;

import com.ssafy.ltw.domain.member.model.Member;
import com.ssafy.ltw.domain.member.model.dao.MemberDao;
import com.ssafy.ltw.domain.member.model.dao.MemberDaoImpl;

public class MemberServiceImpl implements MemberService{
	
	private static MemberService memberService = new MemberServiceImpl();
	private MemberDao memberDao;
	
	private MemberServiceImpl() {
		memberDao = MemberDaoImpl.getMemberDao();
		
	}
	public static MemberService getMemberService() {
		return memberService;
	}

	@Override
	public int idCheck(String userId) throws Exception {
		return memberDao.idCheck(userId);
	}
	
	@Override
	public int joinMember(Member member) throws Exception {
		return memberDao.joinMember(member);
	}
	@Override
	public Member loginMember(String loginId, String loginPw) throws Exception {
		return memberDao.loginMember(loginId, loginPw);
	}

	@Override
	public Member findUserNameById(Long id) throws Exception {
		return memberDao.findUserNameById(id);
	}

}
