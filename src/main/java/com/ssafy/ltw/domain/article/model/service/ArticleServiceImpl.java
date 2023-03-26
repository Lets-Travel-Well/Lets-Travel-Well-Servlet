package com.ssafy.ltw.domain.article.model.service;

import com.ssafy.ltw.domain.article.ArticleDto;
import com.ssafy.ltw.global.util.PageNavigation;
import com.ssafy.ltw.domain.article.model.dao.ArticleDao;
import com.ssafy.ltw.domain.article.model.dao.ArticleDaoImpl;

import java.util.List;
import java.util.Map;

public class ArticleServiceImpl implements ArticleService {

    private static ArticleService articleService = new ArticleServiceImpl();

    private ArticleDao articleDao;

    private ArticleServiceImpl() {
        articleDao = ArticleDaoImpl.getArticleDao();
    }

    public static ArticleService getArticleService() {
        return articleService;
    }
    @Override
    public int writeArticle(ArticleDto articleDto) throws Exception {
        return articleDao.writeArticle(articleDto);
    }

    @Override
    public List<ArticleDto> listArticle() throws Exception {
        return articleDao.listArticle();
    }

    @Override
    public PageNavigation makePageNavigation(Map<String, String> map) throws Exception {
        return null;
    }

    @Override
    public ArticleDto getArticle(Long id) throws Exception {
        updateHit(id);
        return articleDao.getArticle(id);
    }

    @Override
    public void updateHit(Long id) throws Exception {
        articleDao.updateHit(id);
    }

    @Override
    public void modifyArticle(ArticleDto articleDto) throws Exception {
        articleDao.modifyArticle(articleDto);
    }

    @Override
    public void deleteArticle(Long id) throws Exception {
        articleDao.deleteArticle(id);
    }
}
