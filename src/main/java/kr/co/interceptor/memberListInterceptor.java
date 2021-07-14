package kr.co.interceptor;

import java.lang.reflect.Method;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import kr.co.domain.LoginDTO;
import kr.co.domain.MemberDTO;

public class memberListInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		HttpSession session = request.getSession();
		// 로그인으로 바인딩된 개체를 가져옴
		MemberDTO login = (MemberDTO) session.getAttribute("login");

		if (login != null) {

			Integer grade = login.getUserGrade();

			if (grade.equals(1)) {
				return true;
			} else {

				response.sendRedirect("/member/noadmin");

				return true;
			}

		} else {

			response.sendRedirect("/member/noadmin");

			return true;
		}
//deadcode
//		System.out.println("inter"+grade);
//		if (grade.equals(1)) {
//			return true;
//		}else {
//			
//			response.sendRedirect("/member/noadmin");
//			
//			return true;
//		}
//		

	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		// 실제 로그인 작업

	}

}
