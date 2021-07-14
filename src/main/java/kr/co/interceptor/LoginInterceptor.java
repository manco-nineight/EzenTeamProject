package kr.co.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import kr.co.domain.MemberDTO;

public class LoginInterceptor extends HandlerInterceptorAdapter{
	// 로그인 기능 인터셉터
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		MemberDTO login = (MemberDTO) session.getAttribute("login");
		
		if (login != null) {	// 로그인 되어있으면 로그아웃
			session.invalidate();
		}
		
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		HttpSession session = request.getSession();
		Map<String, Object> map = modelAndView.getModel();	// model에 바인딩된 데이터를 Map에 가져온다
		Object dto = map.get("login");	// 컨트롤러에 model로 바인딩한 데이터를 가져온다
		
		if (dto != null) {	// DB와 비교해 로그인 데이터가 동일한다면
			session.setAttribute("login", dto);	// 로그인 세션 설정
			
			response.sendRedirect("/member/list");	// 로그인 성공 시 이동할 주소
			
		} else {	// 로그인 데이터가 없으면
			session.setAttribute("errMsg", "아이디 혹은 비밀번호를 잘못 입력하셨거나 등록되지 않은 아이디입니다.");	// 로그인 실패시 메시지
			
			response.sendRedirect("/member/loginGet");	// 다시 로그인 화면으로..
		}
	}
	
}
