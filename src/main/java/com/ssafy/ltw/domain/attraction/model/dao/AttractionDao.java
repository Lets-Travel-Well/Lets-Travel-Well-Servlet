package com.ssafy.ltw.domain.attraction.model.dao;

import com.ssafy.ltw.domain.attraction.AttractionInfo;

public interface AttractionDao {

    AttractionInfo findById(int contentId);
}
