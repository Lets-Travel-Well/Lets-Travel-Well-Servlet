package com.ssafy.ltw.domain.article.model.dao;

import com.ssafy.ltw.domain.article.ArticleDto;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class ArticleDaoImpl implements ArticleDao {

    @Override
    public void writeArticle(ArticleDto articleDto) throws SQLException {

    }

    @Override
    public List<ArticleDto> listArticle(Map<String, Object> param) throws SQLException {
        return null;
    }

    @Override
    public int getTotalArticleCount(Map<String, Object> param) throws SQLException {
        return 0;
    }

    @Override
    public ArticleDto getArticle(int id) throws SQLException {
        return null;
    }

    @Override
    public void updateHit(int id) throws SQLException {

    }

    @Override
    public void modifyArticle(ArticleDto articleDto) throws SQLException {

    }

    @Override
    public void deleteArticle(int id) throws SQLException {

    }
}
