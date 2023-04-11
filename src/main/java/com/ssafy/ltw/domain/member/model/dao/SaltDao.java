package com.ssafy.ltw.domain.member.model.dao;

import com.ssafy.ltw.domain.member.model.Salt;

import java.sql.SQLException;

public interface SaltDao {
    int createSalt(Long memberId, String salt) throws SQLException;
    Salt findByMember(Long memberId) throws SQLException;
}
