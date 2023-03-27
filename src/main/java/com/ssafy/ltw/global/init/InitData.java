package com.ssafy.ltw.global.init;

import com.ssafy.ltw.domain.article.model.Article;
import com.ssafy.ltw.domain.article.model.service.ArticleService;
import com.ssafy.ltw.domain.article.model.service.ArticleServiceImpl;

public class InitData {
    public static void main(String[] args) throws Exception {
        ArticleService articleService = ArticleServiceImpl.getArticleService();
        // 입력데이터 수
        int cnt = 100;
        for(int i = 0; i < 100; i++){
            Article article = new Article().builder()
                    .subject("qwer" + i)
                    .content("qwercontent" + i)
                    .memberId(1)
                    .build();
            articleService.writeArticle(article);
        }
    }
}
