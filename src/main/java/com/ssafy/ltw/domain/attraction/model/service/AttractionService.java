package com.ssafy.ltw.domain.attraction.model.service;

import java.util.List;

import com.ssafy.ltw.domain.attraction.model.AttractionInfo;
import com.ssafy.ltw.domain.attraction.model.Gugun;
import com.ssafy.ltw.domain.attraction.model.Sido;

public interface AttractionService {
	List<Sido> listSido() throws Exception;
	List<Gugun> listGugun(int sidoCod) throws Exception;
	List<AttractionInfo> listAttractionInfoByCriterial(int contentTypeId, int sidoCode, int gugunCode) throws Exception;
	List<AttractionInfo> listAttractionInfoByCriterial(int contentTypeId, int sidoCode, int gugunCode, Long memberId) throws Exception;

}
