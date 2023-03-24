package com.ssafy.ltw.domain.article.model.service;

import com.ssafy.ltw.domain.article.ArticleDto;
import com.ssafy.ltw.global.util.PageNavigation;

import java.util.List;
import java.util.Map;

public interface ArticleService {

    void writeArticle(ArticleDto articleDto) throws Exception;
    List<ArticleDto> listArticle(Map<String, String> map) throws Exception;
    PageNavigation makePageNavigation(Map<String, String> map) throws Exception;
    ArticleDto getArticle(int id) throws Exception;
    void updateHit(int id) throws Exception;

    void modifyArticle(ArticleDto articleDto) throws Exception;
    void deleteArticle(int id) throws Exception;

}
