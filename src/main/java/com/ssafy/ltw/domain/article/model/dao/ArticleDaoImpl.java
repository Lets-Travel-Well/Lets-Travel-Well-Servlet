package com.ssafy.ltw.domain.article.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ssafy.ltw.domain.article.model.Article;
import com.ssafy.ltw.global.util.db.DBUtil;

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
    public int writeArticle(Article article) throws SQLException {
        int res = -1;
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = dbUtil.getConnection();
            StringBuilder sql = new StringBuilder();
            sql.append("insert into article (member_id, subject, content) \n");
            sql.append("values (?, ?, ?)");

            pstmt = conn.prepareStatement(sql.toString());
            pstmt.setLong(1, article.getMemberId());
            pstmt.setString(2, article.getSubject());
            pstmt.setString(3, article.getContent());

            res = pstmt.executeUpdate();
        } finally {
            dbUtil.close(pstmt, conn);
        }
        return res;
    }
    @Override
    public Article getArticle(Long id) throws SQLException {
        Article findArticle = null;
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
            if(rs.next()) {
                findArticle = new Article().builder()
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
        return findArticle;
    }
    @Override
    public List<Article> listArticle(Map<String, Object> param) throws SQLException {
        List<Article> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = dbUtil.getConnection();
            StringBuilder sql = new StringBuilder();
            sql.append("select * \n");
            sql.append("from article \n");
            String key = (String) param.get("key");
            String word = (String) param.get("word");
            if(!key.isEmpty() && !word.isEmpty()) {
                if("subject".equals(key)) {
                    sql.append("where subject like concat('%', ?, '%') \n");
                }
//                else {
//                    sql.append("where ").append(key).append(" = ? \n");
//                }
            }
            sql.append("order by id desc \n");
            sql.append("limit ?, ?");
            pstmt = conn.prepareStatement(sql.toString());
            int idx = 0;
            if(!key.isEmpty() && !word.isEmpty())
                pstmt.setString(++idx, word);
            pstmt.setInt(++idx, (Integer) param.get("start"));
            pstmt.setInt(++idx, (Integer) param.get("listsize"));
            rs = pstmt.executeQuery();
            while(rs.next()) {
                Article findArticle = new Article().builder()
                        .id(rs.getLong("id"))
                        .createdDate(rs.getString("created_date"))
                        .modifiedDate(rs.getString("modified_date"))
                        .subject(rs.getString("subject"))
                        .content(rs.getString("content"))
                        .hit(rs.getInt("hit"))
                        .memberId(rs.getLong("member_id"))
                        .build();
                list.add(findArticle);
            }
        } finally {
            dbUtil.close(rs, pstmt, conn);
        }
        return list;
    }
    @Override
    public void updateHit(Long id) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = dbUtil.getConnection();
            StringBuilder sql = new StringBuilder();
            sql.append("update article \n");
            sql.append("set hit = hit + 1 \n");
            sql.append("where id = ?");
            pstmt = conn.prepareStatement(sql.toString());
            pstmt.setLong(1, id);
            pstmt.executeUpdate();
        } finally {
            dbUtil.close(pstmt, conn);
        }
    }

    @Override
    public void modifyArticle(Article article) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = dbUtil.getConnection();
            StringBuilder sql = new StringBuilder();
            sql.append("update article \n");
            sql.append("set subject=?, content=? \n");
            sql.append("where id = ?");
            pstmt = conn.prepareStatement(sql.toString());
            pstmt.setString(1, article.getSubject());
            pstmt.setString(2, article.getContent());
            pstmt.setLong(3, article.getId());
            pstmt.executeUpdate();
        } finally {
            dbUtil.close(pstmt, conn);
        }
    }

    @Override
    public void deleteArticle(Long id) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = dbUtil.getConnection();
            StringBuilder sql = new StringBuilder();
            sql.append("delete from article \n");
            sql.append("where id = ?");
            pstmt = conn.prepareStatement(sql.toString());
            pstmt.setLong(1, id);
            pstmt.executeUpdate();
        } finally {
            dbUtil.close(pstmt, conn);
        }
    }

    @Override
    public int getTotalArticleCount(Map<String, Object> param) throws SQLException {
        int cnt = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = dbUtil.getConnection();
            StringBuilder sql = new StringBuilder();
            sql.append("select count(id) \n");
            sql.append("from article \n");
            String key = (String) param.get("key");
            String word = (String) param.get("word");
            if(!key.isEmpty() && !word.isEmpty()) {
                if("subject".equals(key)) {
                    sql.append("where subject like concat('%', ?, '%') \n");
                }
//                else {
//                    sql.append("where ").append(key).append(" = ? \n");
//                }
            }
            pstmt = conn.prepareStatement(sql.toString());
            if(!key.isEmpty() && !word.isEmpty())
                pstmt.setString(1, word);
            rs = pstmt.executeQuery();
            if(rs.next()) {
                cnt = rs.getInt(1);
            }
        } finally {
            dbUtil.close(rs, pstmt, conn);
        }
        return cnt;
    }

    @Override
    public void clear() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = dbUtil.getConnection();
            StringBuilder sql = new StringBuilder();
            sql.append("delete from article");

            pstmt = conn.prepareStatement(sql.toString());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbUtil.close(pstmt, conn);
        }
    }
}
