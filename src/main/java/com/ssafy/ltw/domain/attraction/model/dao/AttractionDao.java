package com.ssafy.ltw.domain.attraction.model.dao;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.ltw.domain.attraction.model.AttractionInfo;
import com.ssafy.ltw.domain.attraction.model.Gugun;
import com.ssafy.ltw.domain.attraction.model.Sido;

public interface AttractionDao {

    AttractionInfo findById(int contentId);
    
    List<Sido> listSidos() throws SQLException;

	List<Gugun> listGuguns(int sidoCode) throws SQLException;
}
