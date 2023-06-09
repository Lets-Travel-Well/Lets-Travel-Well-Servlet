package com.ssafy.ltw.domain.attraction.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.ltw.domain.attraction.model.AttractionInfo;
import com.ssafy.ltw.domain.attraction.model.Gugun;
import com.ssafy.ltw.domain.attraction.model.Sido;
import com.ssafy.ltw.domain.attraction.model.service.AttractionService;
import com.ssafy.ltw.domain.attraction.model.service.AttractionServiceImpl;
import com.ssafy.ltw.domain.member.model.Member;
import com.ssafy.ltw.domain.member.model.service.MemberService;
import com.ssafy.ltw.domain.member.model.service.MemberServiceImpl;

@WebServlet("/attraction")
public class AttractionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private AttractionService attractionService;
    private MemberService memberService;
    private ObjectMapper objectMapper = new ObjectMapper();
    
    public AttractionController() {
    	super();
    }

    public void init() {
    	 attractionService  = AttractionServiceImpl.getAttractionService();
    	 memberService = MemberServiceImpl.getMemberService();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		String path = "";
		if("mvsearch".equals(action)) {
			path = mvSearch(request, response);
			forward(request, response, path);
		} else if("guguns".equals(action)) {
	        response.setContentType("application/json");
	        response.setCharacterEncoding("utf-8");
			
	        List<Gugun> guguns = guguns(request,response);
			
			String result = objectMapper.writeValueAsString(guguns);
			response.getWriter().write(result);
		} else if("search".equals(action)) {
	        response.setContentType("application/json");
	        response.setCharacterEncoding("utf-8");
			System.out.println("search");
			List<AttractionInfo> list = search(request,response);
			
			String result = objectMapper.writeValueAsString(list);
			response.getWriter().write(result);
			
		} else {
			redirect(request, response, path);
		}
	}	// TODO: sido, gugun, contentsType 받아서 관광지 뽑아서 넘기기 
	private List<AttractionInfo> search(HttpServletRequest request, HttpServletResponse response) {
		int sidoCode = Integer.parseInt(request.getParameter("sidoCode"));
		int gugunCode = Integer.parseInt(request.getParameter("gugunCode"));
		int contentTypeId = Integer.parseInt(request.getParameter("contentTypeId"));
		
		try {
			List<AttractionInfo> list = null;
			if(request.getSession(false) != null && request.getSession().getAttribute("userinfo") != null ) {
				Member member = (Member) request.getSession().getAttribute("userinfo");
				long memberId = memberService.findIdByUserId(member.getLoginId());
				System.out.println(member);
				list = attractionService.listAttractionInfoByCriterial(contentTypeId, sidoCode, gugunCode,memberId);
			} else {
				list = attractionService.listAttractionInfoByCriterial(contentTypeId, sidoCode, gugunCode);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}

	private List<Gugun> guguns(HttpServletRequest request, HttpServletResponse response) {
		
		try {
			return attractionService.listGugun(Integer.parseInt(request.getParameter("sidoCode")));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}


	
	private String mvSearch(HttpServletRequest request,HttpServletResponse response) {
		try {
			List<Sido> sidoList = attractionService.listSido();
			request.setAttribute("sidos", sidoList);

			return "/attraction/search_place.jsp#search-place";
		} catch (Exception e) {
			e.printStackTrace();
			return "/index.jsp";
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
