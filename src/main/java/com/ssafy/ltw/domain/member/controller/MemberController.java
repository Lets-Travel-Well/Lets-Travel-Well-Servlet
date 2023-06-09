package com.ssafy.ltw.domain.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ssafy.ltw.domain.member.model.Member;
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
		System.out.println(action);
		String path = "";
		if("mvjoin".equals(action)) {
			path = "/member/sign_up.jsp#signup-serction";
			redirect(request,response,path);
		} else if("idCheck".equals(action)) {
			int cnt = idCheck(request,response);
			response.setContentType("text/plain");
			response.getWriter().print(cnt);
		} else if("join".equals(action)) {
			path = join(request,response);
			redirect(request, response, path);
		} else if("mvlogin".equals(action)) {
			path = "/member/login.jsp";
			redirect(request,response,path);
		} else if ("login".equals(action)) {
			path = login(request,response);
			forward(request, response, path);
		} else if ("logout".equals(action)) {
			path = logout(request,response);
			redirect(request,response, path);
		} else if ("mypage".equals(action)) {
			path = mypage(request,response);
			forward(request, response, path);
		} else if ("modify".equals(action)) {
			// 수정하고, mypage forward
			System.out.println("modify");
			path = modify(request,response);
			forward(request, response, path);
		} else {
			redirect(request,response, path);
		}
	}
	
	
	private String modify(HttpServletRequest request, HttpServletResponse response) {
		String loginId = request.getParameter("loginId");
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");

		try {
			Member member = new Member().builder()
					.loginId(loginId)
					.username(username)
					.email(email)
					.phone(phone)
					.build();
			memberService.modifyMember(member);
			request.setAttribute("member", member);
			return "/member?action=mypage#mypage-section";
		} catch (Exception e) {
			e.printStackTrace();
			return "/error/error.jsp";
		}
	}

	private String mypage(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		Member loginMember = (Member) session.getAttribute("userinfo");
		

			try {
				if(loginMember != null) {
					// 로그인 되어있는 상태
					long id = memberService.findIdByUserId(loginMember.getLoginId());
					Member member = memberService.findUserNameById(id);
					System.out.println(member);
					request.setAttribute("member", member);
					return "/member/my_page.jsp#mypage-section";
				} else {
					// 로그인이 되어있지 않음 
					request.setAttribute("msg", "로그인이 필요합니다.");
					return "/member/login.jsp#login-section";
				}
			} catch (Exception e) {
				e.printStackTrace();
				return "/error/error.jsp";
			} 
			

		
	}

	private String logout(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
//		session.removeAttribute("userinfo");
		session.invalidate();
		return "";
	}

	private String login(HttpServletRequest request, HttpServletResponse response) {
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userpwd");
		try {
			Member member = memberService.loginMember(userId, userPwd);
			if(member != null) {
				HttpSession session = request.getSession();
				session.setAttribute("userinfo", member);
				
				String idsave = request.getParameter("saveid");
				if("ok".equals(idsave)) { //아이디 저장을 체크 했다면.
					Cookie cookie = new Cookie("userId", userId);
					cookie.setPath(request.getContextPath());
					cookie.setMaxAge(60 * 60 * 24 * 365 * 40); //40년간 저장.
					response.addCookie(cookie);
				} else { //아이디 저장을 해제 했다면.
					Cookie cookies[] = request.getCookies();
					if(cookies != null) {
						for(Cookie cookie : cookies) {
							if("userId".equals(cookie.getName())) {
								cookie.setMaxAge(0);
								response.addCookie(cookie);
								break;
							}
						}
					}
				}
				return "/index.jsp";
			} else {
				request.setAttribute("msg", "아이디 또는 비밀번호 확인 후 다시 로그인하세요.");
				
				return "/member/login.jsp#login-section";
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "로그인 중 에러 발생!!!");
			// TODO: 에러페이지 제작
			return "/error/error.jsp";
		}
	}

	private int idCheck(HttpServletRequest request, HttpServletResponse response) {
		String userId = request.getParameter("userid");
		try {
			return memberService.idCheck(userId);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}


	private String join(HttpServletRequest request, HttpServletResponse response) {
		String username = request.getParameter("username");
		String loginId = request.getParameter("loginId");
		String loginPw = request.getParameter("loginPw");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		
		Member member = new Member();
		member.setUsername(username);
		member.setLoginId(loginId);
		member.setLoginPw(loginPw);
		member.setEmail(email);
		member.setPhone(phone);
		
		try {
			memberService.joinMember(member);
			System.out.println("joinMember");
			return "/member/login.jsp#login-section";
		} catch (Exception e) {
			e.printStackTrace();
			return "/member/sign_up.jsp#signup-section";
		}

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
