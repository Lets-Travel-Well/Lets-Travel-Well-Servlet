package com.ssafy.ltw.domain.article.model.service;

import com.ssafy.ltw.domain.article.ArticleDto;
import com.ssafy.ltw.domain.article.model.dao.ArticleDao;
import com.ssafy.ltw.domain.article.model.dao.ArticleDaoImpl;
import com.ssafy.ltw.global.util.PageNavigation;

import java.util.List;
import java.util.Map;

public class ArticleServiceImpl implements ArticleService {

    private static ArticleService articleService = new ArticleServiceImpl();

    private ArticleDao articleDao;

    private ArticleServiceImpl() {
        articleDao = ArticleDaoImpl.getArticleDao();
    }
    @Override
    public void writeArticle(ArticleDto articleDto) throws Exception {

    }

    @Override
    public List<ArticleDto> listArticle(Map<String, String> map) throws Exception {
        return null;
    }

    @Override
    public PageNavigation makePageNavigation(Map<String, String> map) throws Exception {
        return null;
    }

    @Override
    public ArticleDto getArticle(int id) throws Exception {
        return null;
    }

    @Override
    public void updateHit(int id) throws Exception {

    }

    @Override
    public void modifyArticle(ArticleDto articleDto) throws Exception {

    }

    @Override
    public void deleteArticle(int id) throws Exception {

    }
}
