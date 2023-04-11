package com.ssafy.ltw.domain.myattraction.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.ltw.domain.article.model.service.ArticleServiceImpl;
import com.ssafy.ltw.domain.attraction.model.AttractionInfo;
import com.ssafy.ltw.domain.attraction.model.Gugun;
import com.ssafy.ltw.domain.member.model.Member;
import com.ssafy.ltw.domain.member.model.service.MemberService;
import com.ssafy.ltw.domain.member.model.service.MemberServiceImpl;
import com.ssafy.ltw.domain.myattraction.model.service.MyAttractionService;
import com.ssafy.ltw.domain.myattraction.model.service.MyAttractionServiceImpl;
import com.ssafy.ltw.global.util.validation.ParameterCheck;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

@WebServlet("/attraction")
public class MyAttractionController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private MyAttractionService myAttractionService;
    private MemberService memberService;
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        myAttractionService = MyAttractionServiceImpl.getInstance();
        memberService = MemberServiceImpl.getMemberService();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        String path = "";
        if("like".equals(action)) {
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            boolean like = changeLike(request,response);

            String result = objectMapper.writeValueAsString(like);
            response.getWriter().write(result);
        }
        else {
            redirect(request, response, path);
        }
    }

    private boolean changeLike(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();
        Member member = (Member) session.getAttribute("userinfo");
        int contentId = Integer.parseInt(request.getParameter("contentId"));
        try {
            return myAttractionService.changeLike(member.getId(),contentId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        doGet(request, response);
    }
    private void forward(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(path);
        dispatcher.forward(request, response);
    }

    private void redirect(HttpServletRequest request, HttpServletResponse response, String path) throws IOException {
        response.sendRedirect(request.getContextPath() + path);
    }
}
