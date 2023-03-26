package com.ssafy.ltw.domain.attraction.model.service;

import java.util.List;

import com.ssafy.ltw.domain.attraction.model.Gugun;
import com.ssafy.ltw.domain.attraction.model.Sido;
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

	public List<Sido> listSido() throws Exception {
		return attractionDao.listSidos();
	}

	@Override
	public List<Gugun> listGugun(int sidoCode) throws Exception {
		return attractionDao.listGuguns(sidoCode);
	}

}
