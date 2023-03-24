package com.ssafy.ltw.domain.article.model.dao;

import com.ssafy.ltw.domain.article.ArticleDto;
import com.ssafy.ltw.global.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
            sql.append("insert into article (member_id, subject, content) \n");
            sql.append("values (?, ?, ?)");

            pstmt = conn.prepareStatement(sql.toString());
            pstmt.setLong(1, articleDto.getMemberId());
            pstmt.setString(2, articleDto.getSubject());
            pstmt.setString(3, articleDto.getContent());

            pstmt.executeUpdate();
        } finally {
            dbUtil.close(pstmt, conn);
        }

    }

    @Override
    public List<ArticleDto> listArticle() throws SQLException {
        List<ArticleDto> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = dbUtil.getConnection();
            StringBuilder sql = new StringBuilder();
            sql.append("select * \n");
            sql.append("from article \n");
            pstmt = conn.prepareStatement(sql.toString());
            rs = pstmt.executeQuery();

            while(rs.next()) {
                ArticleDto findArticleDto = new ArticleDto().builder()
                        .id(rs.getLong("id"))
                        .createdDate(rs.getString("created_date"))
                        .modifiedDate(rs.getString("modified_date"))
                        .subject(rs.getString("subject"))
                        .content(rs.getString("content"))
                        .hit(rs.getInt("hit"))
                        .memberId(rs.getLong("member_id"))
                        .build();
                list.add(findArticleDto);
            }
        } finally {
            dbUtil.close(rs, pstmt, conn);
        }
        return list;
    }

    @Override
    public int getTotalArticleCount(Map<String, Object> param) throws SQLException {
        return 0;
    }

    @Override
    public ArticleDto getArticle(int id) throws SQLException {
        ArticleDto findArticleDto = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = dbUtil.getConnection();
            StringBuilder sql = new StringBuilder();
            sql.append("select *\n");
            sql.append("from article \n");
            sql.append("where id = ?");
            pstmt = conn.prepareStatement(sql.toString());
            pstmt.setLong(1, id);
            rs = pstmt.executeQuery();
            System.out.println("qw");
            if(rs.next()) {
                System.out.println("ho");
                findArticleDto = new ArticleDto().builder()
                        .id(rs.getLong("id"))
                        .createdDate(rs.getString("created_date"))
                        .modifiedDate(rs.getString("modified_date"))
                        .subject(rs.getString("subject"))
                        .content(rs.getString("content"))
                        .hit(rs.getInt("hit"))
                        .memberId(rs.getLong("member_id"))
                        .build();
            }
        } finally {
            dbUtil.close(rs, pstmt, conn);
        }
        return findArticleDto;
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
