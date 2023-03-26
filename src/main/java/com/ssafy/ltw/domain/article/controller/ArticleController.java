package com.ssafy.ltw.domain.article.controller;

import com.ssafy.ltw.domain.article.model.service.ArticleService;
import com.ssafy.ltw.domain.article.model.service.ArticleServiceImpl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/article")
public class ArticleController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private ArticleService articleService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        articleService = ArticleServiceImpl.getArticleService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("doGet");
        String action = request.getParameter("action");

        String path = "";
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        doGet(request, response);
    }
//    private void forward(HttpServletRequest request, HttpServletResponse response, String path)
//            throws ServletException, IOException {
//        RequestDispatcher dispatcher = request.getRequestDispatcher(path);
//        dispatcher.forward(request, response);
//    }
//
//    private void redirect(HttpServletRequest request, HttpServletResponse response, String path) throws IOException {
//        response.sendRedirect(request.getContextPath() + path);
//    }
//    private String write(HttpServletRequest request, HttpServletResponse response) {
//        HttpSession session = request.getSession();
//        MemberDto memberDto = (MemberDto) session.getAttribute("userinfo");
//        if (memberDto != null) {
//            BoardDto boardDto = new BoardDto();
//            boardDto.setUserId(memberDto.getUserId());
//            boardDto.setSubject(request.getParameter("subject"));
//            boardDto.setContent(request.getParameter("content"));
//            try {
//                boardService.writeArticle(boardDto);
//                return "/article?action=list";
//            } catch (Exception e) {
//                e.printStackTrace();
//                request.setAttribute("msg", "글작성 중 문제 발생!!!");
//                return "/error/error.jsp";
//            }
//        } else
//            return "/user/login.jsp";
//    }
}
