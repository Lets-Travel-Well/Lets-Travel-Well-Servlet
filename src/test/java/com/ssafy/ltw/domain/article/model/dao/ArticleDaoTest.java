package com.ssafy.ltw.domain.article.model.dao;

import com.ssafy.ltw.domain.attraction.model.AttractionInfo;
import com.ssafy.ltw.domain.attraction.model.dao.AttractionDaoImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ArticleDaoTest {
    private final ArticleDao articleDao = ArticleDaoImpl.getArticleDao();

    @Test
    @DisplayName("전체 article 수 조회 테스트")
    public void article_count_test() throws Exception {
        //Given
        Map<String, Object> param = new HashMap<>();
        param.put("key", "subject");
        param.put("word", "t1");
        //When
        int cnt = articleDao.getTotalArticleCount(param);

        // TODO : 의존성이 안 생기도록 수정해야함
        //Then
        System.out.println(cnt);
    }
}