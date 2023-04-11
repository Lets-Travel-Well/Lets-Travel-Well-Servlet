package com.ssafy.ltw.domain.myattraction.model.service;

import com.ssafy.ltw.domain.myattraction.model.dto.MyAttractionDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
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

    @Test
    @DisplayName("ID로 찾기")
    public void findById() throws Exception{
        MyAttractionDto myAttractionDto = myAttractionService.findById(125266);
        System.out.println(myAttractionDto);
    }

    @Test
    @DisplayName("TSP")
    public void tsp() throws Exception{
        List<Integer> path = new ArrayList<>();
        path.add(125266);
        path.add(125455);
        path.add(125814);
        path.add(126281);
        path.add(126515);
        List<MyAttractionDto> list = myAttractionService.findShortestPath(path);
        for(MyAttractionDto temp : list){
            System.out.println(temp);
        }
    }
}