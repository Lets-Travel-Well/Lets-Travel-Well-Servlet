package com.ssafy.ltw.domain.article.model.service;

import com.ssafy.ltw.domain.article.ArticleDto;
import com.ssafy.ltw.global.util.PageNavigation;

import java.util.List;
import java.util.Map;

public interface ArticleService {

    void writeArticle(ArticleDto articleDto) throws Exception;
    List<ArticleDto> listArticle() throws Exception;
    PageNavigation makePageNavigation(Map<String, String> map) throws Exception;
    ArticleDto getArticle(long id) throws Exception;
    void updateHit(long id) throws Exception;

    void modifyArticle(ArticleDto articleDto) throws Exception;
    void deleteArticle(long id) throws Exception;

}
