package kr.co.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import kr.co.domain.MemberDTO;

public class LoginInterceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler)
			throws Exception {
		//로그인시 기준에 로그인이 있을 시에 로그아웃하는 코드(ex 도서관 pc)
		HttpSession session = request.getSession();
		MemberDTO login = (MemberDTO) session.getAttribute("login");
		if(login != null) {
			session.invalidate();//로그아웃 역할
		}
	
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) 
			throws Exception {
		
		HttpSession session = request.getSession();
		Map<String, Object> map = modelAndView.getModel();
		Object dto = map.get("login");
		
		if(dto != null) {
			session.setAttribute("login", dto);
			
			response.sendRedirect("/board/list");
		}else {
			response.sendRedirect("/member/loginGet");
		}
		
		
	}

	
	
}
