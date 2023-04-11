package com.ssafy.ltw.domain.myattraction.model.service;

import com.ssafy.ltw.domain.myattraction.model.dto.MyAttractionDto;

import java.util.List;

public interface MyAttractionService {
    // 좋아요 변경
    // 좋아요로 변경 되었을 때 true
    boolean changeLike(Long memberId, int attractionInfoId) throws Exception;
    // 좋아요 한 게시물 보기
    List<MyAttractionDto> listMyAttraction(Long memberId, int attractionInfoId) throws Exception;
    // 좋아요 한가 확인 => -1이면 없음 나머지는 테이블의 PK값
    boolean isExisted(Long memberId, int attractionInfoId) throws Exception;
    Long findIdByMemberAndAttractionInfo(Long memberId, int attractionInfoId) throws Exception;
}
