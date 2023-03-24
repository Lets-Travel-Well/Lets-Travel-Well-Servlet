package com.ssafy.ltw.domain.article;

import com.ssafy.ltw.domain.article.model.service.ArticleService;
import com.ssafy.ltw.domain.article.model.service.ArticleServiceImpl;

public class ArticleServiceTest {

    public static void main(String[] args) throws Exception{
        ArticleService articleService = ArticleServiceImpl.getArticleService();
        // WriteTest
        ArticleDto articleDto = new ArticleDto().builder()
                .subject("test")
                .content("testContente")
                .memberId(1)
                .build();
        articleService.writeArticle(articleDto);

        //
    }
}
