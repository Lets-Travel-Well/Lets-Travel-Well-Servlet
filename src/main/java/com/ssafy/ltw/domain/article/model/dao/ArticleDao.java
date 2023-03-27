package com.ssafy.ltw.domain.article.model.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.ltw.domain.article.model.Article;

public interface ArticleDao {
    int writeArticle(Article article) throws SQLException;
    List<Article> listArticle(Map<String, Object> param) throws SQLException;
    int getTotalArticleCount(Map<String, Object> param) throws SQLException;
    Article getArticle(Long id) throws SQLException;
    void updateHit(Long id) throws SQLException;

    void modifyArticle(Article article) throws SQLException;
    void deleteArticle(Long id) throws SQLException;

    // TODO : 환경 분리하면 없어져야될 코드
    void clear();
}
