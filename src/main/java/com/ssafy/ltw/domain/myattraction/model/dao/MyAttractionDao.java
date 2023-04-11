package com.ssafy.ltw.domain.myattraction.model.dao;

import com.ssafy.ltw.domain.myattraction.model.MyAttraction;
import com.ssafy.ltw.domain.myattraction.model.dto.MyAttractionDto;

import java.sql.SQLException;
import java.util.List;

public interface MyAttractionDao {
    int writeMyAttraction(Long memberId, int attractionInfoId) throws SQLException;
    void removeMyAttraction(Long myAttractionId) throws SQLException;
    MyAttraction findByMemberIdAndAttractionInfoId(Long memberId, int attractionInfoId) throws SQLException;
    List<MyAttraction> findAllByMemberId(Long memberId) throws SQLException;
}
