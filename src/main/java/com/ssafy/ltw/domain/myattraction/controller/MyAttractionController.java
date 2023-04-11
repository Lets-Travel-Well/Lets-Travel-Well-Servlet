package com.ssafy.ltw.domain.myattraction.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.ltw.domain.article.model.service.ArticleServiceImpl;
import com.ssafy.ltw.domain.attraction.model.AttractionInfo;
import com.ssafy.ltw.domain.attraction.model.Gugun;
import com.ssafy.ltw.domain.member.model.Member;
import com.ssafy.ltw.domain.member.model.service.MemberService;
import com.ssafy.ltw.domain.member.model.service.MemberServiceImpl;
import com.ssafy.ltw.domain.myattraction.model.dto.MyAttractionDto;
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

@WebServlet("/myattraction")
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
        System.out.println(action);
        String path = "";
        if("like".equals(action)) {
        	
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            boolean like = changeLike(request,response);

            String result = objectMapper.writeValueAsString(like);
            response.getWriter().write(result);
        }
        else if("list".equals(action)) {
        	path = list(request,response);
        	forward(request,response, path);
        } else if ("find".equals(action)) {
        	path = find(request, response);
        }
        else {
            redirect(request, response, path);
        }
    }

    private String find(HttpServletRequest request, HttpServletResponse response) {
    	//TODO: 최단 경로 찾아서 반환하는 로직 구현 
		return null;
	}
	private String list(HttpServletRequest request, HttpServletResponse response) {
    	System.out.println("list");
    	HttpSession session = request.getSession();
    	Member member = (Member) session.getAttribute("userinfo");
    	if(member != null) {
    		// 해당 id의 모든 장소 정보를 가져오기
    		long memberId;
			try {
				memberId = memberService.findIdByUserId(member.getLoginId());
				List<MyAttractionDto> list = myAttractionService.listMyAttraction(memberId);
				System.out.println(list);
				request.setAttribute("myattractions", list);
			} catch (Exception e) {
				e.printStackTrace();
			}
    		
    	}
    	return "/attraction/my_travel.jsp#my-travel";
	}
	private boolean changeLike(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();
        Member member = (Member) session.getAttribute("userinfo");
        
        int contentId = Integer.parseInt(request.getParameter("contentId"));
        try {
        	long memberId = memberService.findIdByUserId(member.getLoginId());
            return myAttractionService.changeLike(memberId,contentId);
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
