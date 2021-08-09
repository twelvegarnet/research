package research.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import research.dto.Users;
import research.service.ResearchService;

public class UserInterceptor implements HandlerInterceptor {
	private static final Logger logger = LoggerFactory.getLogger(UserInterceptor.class);
	
	@Autowired
	ResearchService researchService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession();
		
		if(session.getAttribute("userNo")==null) {
			
			//로그인 페이지로 리다이렉트
			response.sendRedirect("/research/login");
			
			return false;
			
		} else {
			
			String userNo = (String) session.getAttribute("userNo");
			String userId = (String) session.getAttribute("userId");
			String userName = (String) session.getAttribute("userName");
			String userGrade = (String) session.getAttribute("userGrade");
			Users users  = new Users();
			users.setUserNo(userNo);
			users.setUserId(userId);
			users.setUserName(userName);
			users.setUserGrade(userGrade);
			
		}

		return true;
	}
	
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}
}
