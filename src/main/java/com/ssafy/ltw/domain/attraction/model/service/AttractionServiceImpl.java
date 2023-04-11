package com.ssafy.ltw.domain.attraction.model.service;

import java.util.List;

import com.ssafy.ltw.domain.attraction.model.AttractionInfo;
import com.ssafy.ltw.domain.attraction.model.Gugun;
import com.ssafy.ltw.domain.attraction.model.Sido;
import com.ssafy.ltw.domain.attraction.model.dao.AttractionDao;
import com.ssafy.ltw.domain.attraction.model.dao.AttractionDaoImpl;
import com.ssafy.ltw.domain.myattraction.model.dao.MyAttractionDao;
import com.ssafy.ltw.domain.myattraction.model.dao.MyAttractionDaoImpl;
import com.ssafy.ltw.domain.myattraction.model.service.MyAttractionService;
import com.ssafy.ltw.domain.myattraction.model.service.MyAttractionServiceImpl;

public class AttractionServiceImpl implements AttractionService{
    private static AttractionService attractionService = new AttractionServiceImpl();

    private AttractionDao attractionDao;
	private MyAttractionService myAttractionService;

    private AttractionServiceImpl() {
        attractionDao = AttractionDaoImpl.getAttractionDao();
		myAttractionService = MyAttractionServiceImpl.getInstance();
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

	@Override
	public List<AttractionInfo> listAttractionInfoByCriterial(int contentTypeId, int sidoCode, int gugunCode)
			throws Exception {

		return attractionDao.listAttractionInfoByCriterial(contentTypeId, sidoCode, gugunCode);
	}
	@Override
	public List<AttractionInfo> listAttractionInfoByCriterial(int contentTypeId, int sidoCode, int gugunCode, Long memberId)
			throws Exception {
		List<AttractionInfo> list = attractionDao.listAttractionInfoByCriterial(contentTypeId, sidoCode, gugunCode);
		for(AttractionInfo temp : list){
			temp.setScrap(myAttractionService.isExisted(memberId, temp.getContentId()));
		}
		return list;
	}
}
