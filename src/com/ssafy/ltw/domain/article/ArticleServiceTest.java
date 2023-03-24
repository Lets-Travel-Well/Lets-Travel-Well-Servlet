package com.ssafy.ltw.domain.article;

import com.ssafy.ltw.domain.article.model.service.ArticleService;
import com.ssafy.ltw.domain.article.model.service.ArticleServiceImpl;

import java.util.List;

// TODO : 수정 필요
public class ArticleServiceTest {
    public static void main(String[] args) throws Exception{
        ArticleService articleService = ArticleServiceImpl.getArticleService();

        // WriteTest
//        ArticleDto articleDto = new ArticleDto().builder()
//                .subject("test")
//                .content("testContente")
//                .memberId(1)
//                .build();
//        articleService.writeArticle(articleDto);

        // getArticleTest
        ArticleDto articleDto = articleService.getArticle(1);
        System.out.println(articleDto.toString());

        // getArticleTest
        List<ArticleDto> articles = articleService.listArticle();
        
        for(ArticleDto articleDto1 : articles){
            System.out.println(articleDto1);
        }
    }
}
