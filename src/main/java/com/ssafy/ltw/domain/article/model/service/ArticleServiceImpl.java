package com.ssafy.ltw.domain.article.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ssafy.ltw.domain.article.model.Article;
import com.ssafy.ltw.domain.article.model.dao.ArticleDao;
import com.ssafy.ltw.domain.article.model.dao.ArticleDaoImpl;
import com.ssafy.ltw.global.util.page.PageNavigation;
import com.ssafy.ltw.global.util.page.SizeConstant;

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
    public int writeArticle(Article article) throws Exception {
        return articleDao.writeArticle(article);
    }

    @Override
    public List<Article> listArticle(Map<String, String> map) throws Exception {
        Map<String, Object> param = new HashMap<String, Object>();
        String key = map.get("key");
//		if("userid".equals(key))
//			key = "user_id";
        param.put("key", key.isEmpty() ? "" : key);
        param.put("word", map.get("word").isEmpty() ? "" : map.get("word"));
        int pgno = Integer.parseInt(map.get("pgno"));
        int start = pgno * SizeConstant.LIST_SIZE - SizeConstant.LIST_SIZE;
        param.put("start", start);
        param.put("listsize", SizeConstant.LIST_SIZE);
        return articleDao.listArticle(param);
    }

    @Override
    public PageNavigation makePageNavigation(Map<String, String> map) throws Exception {
        PageNavigation pageNavigation = new PageNavigation();

        int naviSize = SizeConstant.NAVIGATION_SIZE;
        int sizePerPage = SizeConstant.LIST_SIZE;
        int currentPage = Integer.parseInt(map.get("pgno"));

        pageNavigation.setCurrentPage(currentPage);
        pageNavigation.setNaviSize(naviSize);
        Map<String, Object> param = new HashMap<String, Object>();
        String key = map.get("key");
//		if ("userid".equals(key))
//			key = "user_id";
        param.put("key", key.isEmpty() ? "" : key);
        param.put("word", map.get("word").isEmpty() ? "" : map.get("word"));
        int totalCount = articleDao.getTotalArticleCount(param);
        pageNavigation.setTotalCount(totalCount);
        int totalPageCount = (totalCount - 1) / sizePerPage + 1;
        pageNavigation.setTotalPageCount(totalPageCount);
        boolean startRange = currentPage <= naviSize;
        pageNavigation.setStartRange(startRange);
        boolean endRange = (totalPageCount - 1) / naviSize * naviSize < currentPage;
        pageNavigation.setEndRange(endRange);
        pageNavigation.makeNavigator();

        return pageNavigation;
    }

    @Override
    public Article getArticle(Long id) throws Exception {
        updateHit(id);
        return articleDao.getArticle(id);
    }

    @Override
    public void updateHit(Long id) throws Exception {
        articleDao.updateHit(id);
    }

    @Override
    public void modifyArticle(Article article) throws Exception {
        articleDao.modifyArticle(article);
    }

    @Override
    public void deleteArticle(Long id) throws Exception {
        articleDao.deleteArticle(id);
    }
}
