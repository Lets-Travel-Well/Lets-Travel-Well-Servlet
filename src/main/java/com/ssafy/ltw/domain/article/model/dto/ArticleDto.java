package com.ssafy.ltw.domain.article.model.dto;

import com.ssafy.ltw.domain.article.model.Article;
import com.ssafy.ltw.domain.member.model.Member;
import lombok.*;

@Setter
@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArticleDto {
    private Long id;
    private String createdDate;
    private String subject;
    private String content;
    // 조회수
    private int hit;

    private Long memberId;
    private String memberName;

    public ArticleDto(Article article, Member member){
        this.id = article.getId();
        this.createdDate = article.getCreatedDate();
        this.subject = article.getSubject();
        this.content = article.getContent();
        this.hit = article.getHit();
        this.memberId = member.getId();
        this.memberName = member.getUsername();
    }
}
