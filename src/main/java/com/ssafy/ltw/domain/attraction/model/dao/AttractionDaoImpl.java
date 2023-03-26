package com.ssafy.ltw.domain.attraction.model.dao;

import com.ssafy.ltw.domain.attraction.AttractionInfo;
import com.ssafy.ltw.global.util.db.DBUtil;

public class AttractionDaoImpl implements AttractionDao{
    private static AttractionDao attractionDao;

    private DBUtil dbUtil;

    private AttractionDaoImpl(){
        dbUtil = DBUtil.getInstance();
    }

    public static AttractionDao getAttractionDao() {
        if(attractionDao == null)
            attractionDao = new AttractionDaoImpl();
        return attractionDao;
    }


    @Override
    public AttractionInfo findById(int contentId) {
        return null;
    }
}
