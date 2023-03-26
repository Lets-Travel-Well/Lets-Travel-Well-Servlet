package com.ssafy.ltw.domain.article.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.ltw.domain.article.ArticleDto;
import com.ssafy.ltw.global.util.PageNavigation;

public interface ArticleService {

    int writeArticle(ArticleDto articleDto) throws Exception;
    List<ArticleDto> listArticle() throws Exception;
    PageNavigation makePageNavigation(Map<String, String> map) throws Exception;
    ArticleDto getArticle(Long id) throws Exception;
    void updateHit(Long id) throws Exception;

    void modifyArticle(ArticleDto articleDto) throws Exception;
    void deleteArticle(Long id) throws Exception;

}
