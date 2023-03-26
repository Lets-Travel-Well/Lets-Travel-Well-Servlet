package com.ssafy.ltw.domain.article.controller;

import com.ssafy.ltw.domain.article.model.Article;
import com.ssafy.ltw.domain.article.model.dto.ArticleDto;
import com.ssafy.ltw.domain.article.model.service.ArticleService;
import com.ssafy.ltw.domain.article.model.service.ArticleServiceImpl;
import com.ssafy.ltw.domain.member.model.Member;
import com.ssafy.ltw.domain.member.model.service.MemberService;
import com.ssafy.ltw.domain.member.model.service.MemberServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/article")
public class ArticleController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private ArticleService articleService;
    private MemberService memberService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        articleService = ArticleServiceImpl.getArticleService();
        memberService = MemberServiceImpl.getMemberService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
            path = write(request, response);
            redirect(request, response, path);
        } else if ("mvmodify".equals(action)) {
            path = mvModify(request, response);
            forward(request, response, path);
        } else if ("modify".equals(action)) {
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
                List<Article> articles = articleService.listArticle();
                List<ArticleDto> list = new ArrayList<>();
                for(Article article : articles){
                    Member findMember = memberService.findUserNameById(article.getMemberId());
                    // TODO : 유니크한 키값도 같이 가져와야함..
                    ArticleDto articleDto = new ArticleDto(article, findMember);
                    list.add(articleDto);
                }
                request.setAttribute("articles", list);
                return "/article/list.jsp";
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
//                return "/index.jsp";
//            }
//        } else
//            return "/user/login.jsp";
        return null;
    }

    private String mvModify(HttpServletRequest request, HttpServletResponse response) {
        // TODO : 수정하고자하는 글의 글번호를 얻는다.
        // TODO : 글번호에 해당하는 글정보를 얻고 글정보를 가지고 modify.jsp로 이동.
        return null;
    }

    private String modify(HttpServletRequest request, HttpServletResponse response) {
        // TODO : 수정 할 글정보를 얻고 BoardDto에 set.
        // TODO : boardDto를 파라미터로 service의 modifyArticle() 호출.
        // TODO : 글수정 완료 후 view.jsp로 이동.(이후의 프로세스를 생각해 보세요.)
        return null;
    }

    private String delete(HttpServletRequest request, HttpServletResponse response) {
        // TODO : 삭제할 글 번호를 얻는다.
        // TODO : 글번호를 파라미터로 service의 deleteArticle()을 호출.
        // TODO : 글삭제 완료 후 list.jsp로 이동.(이후의 프로세스를 생각해 보세요.)
        return null;
    }

    private void forward(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(path);
        dispatcher.forward(request, response);
    }

    private void redirect(HttpServletRequest request, HttpServletResponse response, String path) throws IOException {
        response.sendRedirect(request.getContextPath() + path);
    }
}
