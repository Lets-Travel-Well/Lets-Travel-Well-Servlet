package com.ssafy.ltw.domain.article.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Article {
    private Long id;
    private String createdDate;
    private String modifiedDate;
    private String subject;
    private String content;
    // 조회수 
    private int hit;
    // TODO : 좋아요 기능 추가 구현
    private long memberId;
}
