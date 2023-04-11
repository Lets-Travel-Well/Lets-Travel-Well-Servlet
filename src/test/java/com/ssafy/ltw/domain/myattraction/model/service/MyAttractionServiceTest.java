package com.ssafy.ltw.domain.myattraction.model.service;

import com.ssafy.ltw.domain.myattraction.model.dto.MyAttractionDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MyAttractionServiceTest {
    private final MyAttractionService myAttractionService = MyAttractionServiceImpl.getInstance();
    @Test
    @DisplayName("좋아요한 컬럼이 없을경우")
    public void isExisted() throws Exception{
        boolean findMyAttractionID = myAttractionService.isExisted(-1L, -1);

        assertThat(findMyAttractionID).isEqualTo(false);
    }

    @Test
    @DisplayName("좋아요 테스트")
    public void like() throws Exception{
        boolean isLike = myAttractionService.changeLike(1L, 125266);

        assertThat(isLike).isEqualTo(true);
    }

    @Test
    @DisplayName("로그인 멤버 조회 테스트")
    public void list() throws Exception{
        List<MyAttractionDto> list = myAttractionService.listMyAttraction(1L);
        for(MyAttractionDto temp : list){
            System.out.println(temp);
        }
        assertThat(list.size()).isEqualTo(2);
    }
}