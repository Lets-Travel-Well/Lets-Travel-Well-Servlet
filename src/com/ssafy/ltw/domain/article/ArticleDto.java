package com.ssafy.ltw.domain.article;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class ArticleDto {
    private int id;
    private String createDate;
    private String modifyDate;
    private int memberId;
    private String subject;
    private String content;
    // 조회수 
    private String hit;
    // TODO : 좋아요 기능 추가 구현
}
