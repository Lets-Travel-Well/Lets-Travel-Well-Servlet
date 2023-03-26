package com.ssafy.ltw.domain.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssafy.ltw.domain.member.model.service.MemberService;
import com.ssafy.ltw.domain.member.model.service.MemberServiceImpl;

/**
 * Servlet implementation class MemberController
 */
@WebServlet("/member")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private MemberService memberService;
	
    public MemberController() {
        super();
        // TODO Auto-generated constructor stub
    }

    public void init() {
    	memberService = MemberServiceImpl.getMemberService();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		String path = "";
		if("mvjoin".equals(action)) {
			path = "/member/sign_up.jsp";
			redirect(request,response,path);
		} else if("idCheck".equals(action)) {
			
			int cnt = idCheck(request,response);
			System.out.print(cnt);
			response.setContentType("text/plain");
			response.getWriter().print(cnt);
		} else if("join".equals(action)) {
			path = join(request,response);
			forward(request, response, path);
		} else {
			redirect(request,response, path);
		}
	}
	private int idCheck(HttpServletRequest request, HttpServletResponse response) {
		String userId = request.getParameter("userid");
		try {
			return memberService.idCheck(userId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}


	private String join(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}


	private void forward(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(path);
		dispatcher.forward(request, response);
	}

	private void redirect(HttpServletRequest request, HttpServletResponse response, String path) throws IOException {
		response.sendRedirect(request.getContextPath() + path);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		doGet(request, response);
	}

}
