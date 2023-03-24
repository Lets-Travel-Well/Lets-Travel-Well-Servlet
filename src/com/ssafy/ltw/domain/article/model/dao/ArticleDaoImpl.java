package com.ssafy.ltw.domain.article.model.dao;

import com.ssafy.ltw.domain.article.ArticleDto;
import com.ssafy.ltw.global.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

// TODO : 유저 구현되면 바꿔야함 현재 MEMBER_ID 다 -1로 넣어둘 예정
public class ArticleDaoImpl implements ArticleDao {

    private static ArticleDao articleDao;

    private DBUtil dbUtil;

    private ArticleDaoImpl() {
        dbUtil = DBUtil.getInstance();
    }

    public static ArticleDao getArticleDao() {
        if(articleDao == null){
            articleDao = new ArticleDaoImpl();
        }
        return articleDao;
    }
    @Override
    public void writeArticle(ArticleDto articleDto) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = dbUtil.getConnection();
            StringBuilder sql = new StringBuilder();
            sql.append("insert into board (user_id, subject, content) \n");
            sql.append("values (?, ?, ?)");

            pstmt = conn.prepareStatement(sql.toString());
            pstmt.setInt(1, articleDto.getMemberId());
            pstmt.setString(2, articleDto.getSubject());
            pstmt.setString(3, articleDto.getContent());

            pstmt.executeUpdate();
        } finally {
            dbUtil.close(pstmt, conn);
        }

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
