package kr.co.interceptor;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import kr.co.domain.MemberDTO;


public class DeleteMyselfInterceptor extends HandlerInterceptorAdapter{
    
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
	
		
		return true;
	}
    
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		HttpSession session =  request.getSession();
		MemberDTO login = (MemberDTO) session.getAttribute("login");
		
		if (login != null && login.getUserGrade() == 0) {
	         session.invalidate(); 
		}

	}

}
