package com.ssafy.ltw.domain.article.model.dao;

import com.ssafy.ltw.domain.article.ArticleDto;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface ArticleDao {
    int writeArticle(ArticleDto articleDto) throws SQLException;
    List<ArticleDto> listArticle() throws SQLException;
    int getTotalArticleCount(Map<String, Object> param) throws SQLException;
    ArticleDto getArticle(Long id) throws SQLException;
    void updateHit(Long id) throws SQLException;

    void modifyArticle(ArticleDto articleDto) throws SQLException;
    void deleteArticle(Long id) throws SQLException;

    // TODO : 환경 분리하면 없어져야될 코드
    void clear();
}