package com.ssafy.ltw.domain.article;

import lombok.*;

@Setter
@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArticleDto {
    private long id;
    private String createdDate;
    private String modifiedDate;
    private String subject;
    private String content;
    // 조회수 
    private String hit;
    // TODO : 좋아요 기능 추가 구현
    private int memberId;
}
