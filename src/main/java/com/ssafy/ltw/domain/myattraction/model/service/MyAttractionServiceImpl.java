package com.ssafy.ltw.domain.myattraction.model.service;

import com.ssafy.ltw.domain.attraction.model.service.AttractionService;
import com.ssafy.ltw.domain.attraction.model.service.AttractionServiceImpl;
import com.ssafy.ltw.domain.myattraction.model.MyAttraction;
import com.ssafy.ltw.domain.myattraction.model.dao.MyAttractionDao;
import com.ssafy.ltw.domain.myattraction.model.dao.MyAttractionDaoImpl;
import com.ssafy.ltw.domain.myattraction.model.dto.MyAttractionDto;

import java.util.List;

public class MyAttractionServiceImpl implements MyAttractionService{

    private static MyAttractionService myAttractionService = new MyAttractionServiceImpl();
    private MyAttractionDao myAttractionDao;
    private MyAttractionServiceImpl(){
        myAttractionDao = MyAttractionDaoImpl.getInstance();
    }
    public static MyAttractionService getInstance(){
        return myAttractionService;
    }
    @Override
    public boolean changeLike(Long memberId, int attractionInfoId) throws Exception {
        boolean isLike = isExisted(memberId, attractionInfoId);
        if(!isLike){
            myAttractionDao.writeMyAttraction(memberId, attractionInfoId);
            return true;
        }
        Long findMyAttractionId = findIdByMemberAndAttractionInfo(memberId, attractionInfoId);
        myAttractionDao.removeMyAttraction(findMyAttractionId);
        return false;
    }

    @Override
    public List<MyAttractionDto> listMyAttraction(Long memberId) throws Exception {
        return myAttractionDao.findAllByMemberId(memberId);
    }


    @Override
    public boolean isExisted(Long memberId, int attractionInfoId) throws Exception {
        MyAttraction findMyAttraction = myAttractionDao.findByMemberIdAndAttractionInfoId(memberId, attractionInfoId);
        if(findMyAttraction == null){
            return false;
        }
        return true;
    }

    @Override
    public Long findIdByMemberAndAttractionInfo(Long memberId, int attractionInfoId) throws Exception {
        return myAttractionDao.findByMemberIdAndAttractionInfoId(memberId, attractionInfoId).getId();
    }
}
