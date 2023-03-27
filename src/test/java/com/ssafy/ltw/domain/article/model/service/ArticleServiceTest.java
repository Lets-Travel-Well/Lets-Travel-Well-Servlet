package com.ssafy.ltw.domain.article.model.service;

import com.ssafy.ltw.domain.article.model.Article;
import com.ssafy.ltw.domain.article.model.dao.ArticleDao;
import com.ssafy.ltw.domain.article.model.dao.ArticleDaoImpl;
import com.ssafy.ltw.global.util.page.PageNavigation;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ArticleServiceTest {
    // 레포 테스르를 하는중 수정 필요
    private final ArticleService articleService = ArticleServiceImpl.getArticleService();

    @Test
    @DisplayName("")
    public void pageNavigation() throws Exception {
        // Given
        Map<String, String> param = new HashMap<>();
        param.put("key", "subject");
        param.put("word", "");
        param.put("pgno", "1");

        // When
        PageNavigation pageNavigation = articleService.makePageNavigation(param);

        // Then
        System.out.println(pageNavigation);
    }
}