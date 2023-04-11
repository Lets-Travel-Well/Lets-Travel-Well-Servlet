package com.ssafy.ltw.domain.member.model.service;

import com.ssafy.ltw.domain.member.model.Salt;
import com.ssafy.ltw.domain.member.model.dao.SaltDao;
import com.ssafy.ltw.domain.member.model.dao.SaltDaoImpl;

import java.sql.SQLException;

public class SaltServiceImpl implements SaltService{

    private static SaltService saltService = new SaltServiceImpl();
    private SaltDao saltDao;
    private SaltServiceImpl(){
        saltDao = SaltDaoImpl.getInstance();
    }
    public static SaltService getInstance(){
        return saltService;
    }
    @Override
    public int createSalt(Long memberId, String salt) throws SQLException {
        return saltDao.createSalt(memberId, salt);
    }

    @Override
    public Salt findByMember(Long memberId) throws SQLException {
        return saltDao.findByMember(memberId);
    }
}
