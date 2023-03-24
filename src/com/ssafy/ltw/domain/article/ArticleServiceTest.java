package com.ssafy.ltw.domain.article;

import com.ssafy.ltw.domain.article.model.service.ArticleService;
import com.ssafy.ltw.domain.article.model.service.ArticleServiceImpl;

import java.util.List;

// TODO : 수정 필요
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

        // getArticleTest
        ArticleDto findArticleDto = articleService.getArticle(1L);
        System.out.println(findArticleDto.toString());

        // getArticleTest
        List<ArticleDto> articles = articleService.listArticle();

        // updateHit
        for(ArticleDto articleDto1 : articles){
            System.out.println(articleDto1);
        }
        articleService.updateHit(1L);

        // ModifyArticle
        ArticleDto articleDt02 = new ArticleDto().builder()
                .id(1L)
                .subject("modify123123")
                .content("modifyContetnt@@@@@123123")
                .build();
        articleService.modifyArticle(articleDt02);

        ArticleDto modifyArticleDto = articleService.getArticle(1L);
        System.out.println(modifyArticleDto.toString());

        articleService.deleteArticle(1L);
    }
}
