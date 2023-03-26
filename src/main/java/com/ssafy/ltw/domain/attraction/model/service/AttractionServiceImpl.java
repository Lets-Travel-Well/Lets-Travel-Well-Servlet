package com.ssafy.ltw.domain.attraction.model.service;

import com.ssafy.ltw.domain.attraction.model.dao.AttractionDao;
import com.ssafy.ltw.domain.attraction.model.dao.AttractionDaoImpl;

public class AttractionServiceImpl implements AttractionService{
    private static AttractionService attractionService = new AttractionServiceImpl();

    private AttractionDao attractionDao;

    private AttractionServiceImpl() {
        attractionDao = AttractionDaoImpl.getAttractionDao();
    }

    public static AttractionService getAttractionService(){
        return attractionService;
    }

}
