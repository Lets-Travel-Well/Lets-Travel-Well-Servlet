package com.ssafy.ltw.domain.article;

import static org.assertj.core.api.Assertions.assertThat;

import com.ssafy.ltw.domain.article.model.Article;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.ssafy.ltw.domain.article.model.dao.ArticleDao;
import com.ssafy.ltw.domain.article.model.dao.ArticleDaoImpl;

class ArticleServiceTestTest {
    // 레포 테스르를 하는중 수정 필요
    private final ArticleDao articleDao = ArticleDaoImpl.getArticleDao();
    
    // TODO : 유저 구현시 수정해야함
    @BeforeEach
    void beforeEach(){
    }

    @AfterEach
    void afterEach(){
        articleDao.clear();
    }
    @Test
    @DisplayName("게시물 등록한다")
    //@Test
    public void 게시물_등록() throws Exception {
        //Given
        Article article = new Article().builder()
                .subject("test")
                .content("testContente")
                .memberId(1)
                .build();

        //When
        int res = articleDao.writeArticle(article);
        
        //Then
        assertThat(res).isEqualTo(1);
        }
}