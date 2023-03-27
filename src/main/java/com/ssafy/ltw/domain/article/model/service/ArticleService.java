package com.ssafy.ltw.domain.article.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.ltw.domain.article.model.Article;
import com.ssafy.ltw.global.util.page.PageNavigation;

public interface ArticleService {

    int writeArticle(Article article) throws Exception;
    List<Article> listArticle() throws Exception;
    PageNavigation makePageNavigation(Map<String, String> map) throws Exception;
    Article getArticle(Long id) throws Exception;
    void updateHit(Long id) throws Exception;

    void modifyArticle(Article article) throws Exception;
    void deleteArticle(Long id) throws Exception;

}
