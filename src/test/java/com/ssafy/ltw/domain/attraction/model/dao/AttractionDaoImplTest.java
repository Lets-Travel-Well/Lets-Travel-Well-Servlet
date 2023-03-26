package com.ssafy.ltw.domain.attraction.model.dao;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.ssafy.ltw.domain.attraction.AttractionInfo;
import com.ssafy.ltw.domain.attraction.model.dao.AttractionDao;
import com.ssafy.ltw.domain.attraction.model.dao.AttractionDaoImpl;

class AttractionDaoImplTest {
    private final AttractionDao attractionDao = AttractionDaoImpl.getAttractionDao();
    
    @Test
    @DisplayName("attraction_info 조회 테스트")
    //@Test
    public void attraction_info_단건_조회() throws Exception {
        //Given
        int findContentId = 125407;
        int findSidoCode = 35;
        int findGugunCode = 7;
        String findTitle = "불정자연휴양림";
        //When
        AttractionInfo attractionInfo = attractionDao.findById(findContentId);

        //Then
        assertThat(attractionInfo.getContentId()).isEqualTo(findContentId);
        assertThat(attractionInfo.getSidoCode()).isEqualTo(findContentId);
        assertThat(attractionInfo.getGugunCode()).isEqualTo(findGugunCode);
        assertThat(attractionInfo.getTitle()).isEqualTo(findTitle);
        }
}