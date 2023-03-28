package com.ssafy.ltw.domain.article.controller;

import com.ssafy.ltw.domain.article.model.Article;
import com.ssafy.ltw.domain.article.model.dto.ArticleDto;
import com.ssafy.ltw.domain.article.model.service.ArticleService;
import com.ssafy.ltw.domain.article.model.service.ArticleServiceImpl;
import com.ssafy.ltw.domain.member.model.Member;
import com.ssafy.ltw.domain.member.model.service.MemberService;
import com.ssafy.ltw.domain.member.model.service.MemberServiceImpl;
import com.ssafy.ltw.global.util.page.PageNavigation;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/article")
public class ArticleController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private ArticleService articleService;
    private MemberService memberService;

    private int pgno;
    private String key;
    private String word;
    private String queryStrig;
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        articleService = ArticleServiceImpl.getArticleService();
        memberService = MemberServiceImpl.getMemberService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        pgno = ParameterCheck.notNumberToOne(request.getParameter("pgno"));
        key = ParameterCheck.nullToBlank(request.getParameter("key"));
        word = ParameterCheck.nullToBlank(request.getParameter("word"));

        queryStrig = "?pgno=" + pgno + "&key=" + key + "&word=" + URLEncoder.encode(word, "utf-8");

        System.out.println("doGet");
        String action = request.getParameter("action");
        String path = "";
        if ("list".equals(action)) {
            path = list(request, response);
            forward(request, response, path);
        } else if ("view".equals(action)) {
            path = view(request, response);
            forward(request, response, path);
        } else if ("mvwrite".equals(action)) {
            System.out.println("mvwrite");
            path = "/article/write.jsp";
            redirect(request, response, path);
        } else if ("write".equals(action)) {
            System.out.println("write");
            path = write(request, response);
            redirect(request, response, path);
        } else if ("mvmodify".equals(action)) {
            System.out.println("mvmodify");
            path = mvModify(request, response);
            forward(request, response, path);
        } else if ("modify".equals(action)) {
            System.out.println("modify");
            path = modify(request, response);
            forward(request, response, path);
        } else if ("delete".equals(action)) {
            path = delete(request, response);
            redirect(request, response, path);
        } else {
            redirect(request, response, path);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        doGet(request, response);
    }
    private String list(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("list");
        HttpSession session = request.getSession();
        Member member = (Member) session.getAttribute("userinfo");
        if (member != null) {
            try {
                Map<String, String> map = new HashMap<String, String>();
                map.put("pgno", pgno + "");
                map.put("key", key);
                map.put("word", word);
                List<Article> articles = articleService.listArticle(map);
                List<ArticleDto> list = new ArrayList<>();
                for(Article article : articles){
                    Member findMember = memberService.findUserNameById(article.getMemberId());
                    // TODO : 유니크한 키값도 같이 가져와야함..
                    ArticleDto articleDto = new ArticleDto(article, findMember);
                    list.add(articleDto);
                }
                request.setAttribute("articles", list);
                request.setAttribute("word", word);
                request.setAttribute("key", key);
                System.out.println(key);
                PageNavigation pageNavigation = articleService.makePageNavigation(map);
                request.setAttribute("navigation", pageNavigation);
                return "/article/list.jsp" + queryStrig;
            } catch (Exception e) {
                e.printStackTrace();
                return "/index.jsp";
            }
        } else
            return "/member/login.jsp";
    }

    private String view(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Member member = (Member) session.getAttribute("userinfo");
        if (member != null) {
            long articleId = Integer.parseInt(request.getParameter("articleId"));
            try {
                Article article = articleService.getArticle(articleId);
                articleService.updateHit(articleId);
                Member findMember = memberService.findUserNameById(article.getMemberId());
                ArticleDto articleDto = new ArticleDto(article, findMember);
                request.setAttribute("article", articleDto);

                return "/article/view.jsp";
            } catch (Exception e) {
                e.printStackTrace();
                return "/index.jsp";
            }
        } else
            return "/user/login.jsp";
    }

    private String write(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();
        Member member = (Member) session.getAttribute("userinfo");
        System.out.println(member.toString());
        if (member != null) {
            try {
                Article article = new Article().builder()
                        .subject(request.getParameter("subject"))
                        .content(request.getParameter("content"))
                        .memberId(memberService.findIdByUserId(member.getLoginId()))
                        .build();
                System.out.println(article);
                articleService.writeArticle(article);
                return "/article?action=list";
            } catch (Exception e) {
                e.printStackTrace();
                return "/index.jsp";
            }
        } else
            return "/user/login.jsp";
    }

    private String mvModify(HttpServletRequest request, HttpServletResponse response) {
        // TODO : 수정하고자하는 글의 글번호를 얻는다.
        // TODO : 글번호에 해당하는 글정보를 얻고 글정보를 가지고 modify.jsp로 이동.
        HttpSession session = request.getSession();
        Member loginMember = (Member) session.getAttribute("userinfo");
        if (loginMember != null) {
            long articleId = Integer.parseInt(request.getParameter("articleId"));
            try {
                Article article = articleService.getArticle(articleId);
                Member findMember = memberService.findUserNameById(article.getMemberId());
                System.out.println(loginMember);
                System.out.println(findMember);
                if(!loginMember.getLoginId().equals(findMember.getLoginId())){
                    request.setAttribute("msg", "해당글의 작성자가 아닙니다.");
                    return "/error/error.jsp";
                }

                ArticleDto articleDto = new ArticleDto(article, findMember);
                request.setAttribute("article", articleDto);
                return "/article/modify.jsp";

            } catch (Exception e) {
                e.printStackTrace();
                return "/index.jsp";
            }
        } else
            return "/user/login.jsp";
    }

    private String modify(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Member loginMember = (Member) session.getAttribute("userinfo");
        System.out.println(loginMember.toString());
        if (loginMember != null) {
            try {
                long articleId = Integer.parseInt(request.getParameter("articleId"));
                Article findArticle = articleService.getArticle(articleId);
                Member findMember = memberService.findUserNameById(findArticle.getMemberId());
                if(!loginMember.getLoginId().equals(findMember.getLoginId())){
                    request.setAttribute("msg", "해당글의 작성자가 아닙니다.");
                    return "/error/error.jsp";
                }
                Article article = new Article().builder()
                        .id(Long.parseLong(request.getParameter("articleId")))
                        .subject(request.getParameter("subject"))
                        .content(request.getParameter("content"))
                        .memberId(memberService.findIdByUserId(loginMember.getLoginId()))
                        .build();
                System.out.println(article);
                articleService.modifyArticle(article);
                return "/article?action=list";
            } catch (Exception e) {
                e.printStackTrace();
                return "/index.jsp";
            }
        } else
            return "/user/login.jsp";
    }

    private String delete(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Member loginMember = (Member) session.getAttribute("userinfo");
        System.out.println("delete");
        if (loginMember != null) {
            try {
                long articleId = Integer.parseInt(request.getParameter("articleId"));
                Article findArticle = articleService.getArticle(articleId);
                Member findMember = memberService.findUserNameById(findArticle.getMemberId());
                if(!loginMember.getLoginId().equals(findMember.getLoginId())){
                    request.setAttribute("msg", "해당글의 작성자가 아닙니다.");
                    return "/error/error.jsp";
                }
                articleService.deleteArticle(Long.parseLong(request.getParameter("articleId")));
                return "/article?action=list";
            } catch (Exception e) {
                e.printStackTrace();
                return "/index.jsp";
            }
        } else
            return "/user/login.jsp";
    }

    private void forward(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(path);
        dispatcher.forward(request, response);
    }

    private void redirect(HttpServletRequest request, HttpServletResponse response, String path) throws IOException {
        response.sendRedirect(request.getContextPath() + path);
    }
}
