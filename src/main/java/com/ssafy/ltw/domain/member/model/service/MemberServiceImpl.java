package com.ssafy.ltw.domain.member.model.service;

import com.ssafy.ltw.domain.member.model.Member;
import com.ssafy.ltw.domain.member.model.dao.MemberDao;
import com.ssafy.ltw.domain.member.model.dao.MemberDaoImpl;
import com.ssafy.ltw.global.util.encrypt.EncryptUtil;

public class MemberServiceImpl implements MemberService{
	
	private static MemberService memberService = new MemberServiceImpl();
	private MemberDao memberDao;
	private EncryptUtil encryptUtil;
	private SaltService saltService;
	
	private MemberServiceImpl() {
		memberDao = MemberDaoImpl.getMemberDao();
		encryptUtil = EncryptUtil.getInstance();
		saltService = SaltServiceImpl.getInstance();
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
		String salt = encryptUtil.getSALT();
		System.out.println(salt);
		member.encryptPassword(encryptUtil.Hashing(member.getLoginPw().getBytes(), salt));
		int isSuccess = memberDao.joinMember(member);
		if(isSuccess == 1){
			Long saveId = findIdByUserId(member.getLoginId());
			saltService.createSalt(saveId, salt);
		}

		return isSuccess;
	}
	@Override
	public Member loginMember(String loginId, String loginPw) throws Exception {
		Long saveId = findIdByUserId(loginId);
		if(saveId == -1){
			return null;
		}
		String salt = saltService.findByMember(saveId).getSalt();
		loginPw = encryptUtil.Hashing(loginPw.getBytes(), salt);
		return memberDao.loginMember(loginId, loginPw);
	}

	@Override
	public Member findUserNameById(Long id) throws Exception {
		return memberDao.findUserNameById(id);
	}

	@Override
	public long findIdByUserId(String userId) throws Exception {
		return memberDao.findIdByUserId(userId);
	}
	
	@Override
	public void modifyMember(Member member) throws Exception {
		memberDao.modifyMember(member);
	}

}
