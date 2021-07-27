package kr.co.interceptor;

import java.lang.reflect.Method;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


public class TestInterceptor extends HandlerInterceptorAdapter{
    
	//알트 쉬프트 s - 오버라이드 임플리먼트 - HandlerInterceptorAdapter(preHandle, postHandle)
	
	//컨트롤러 작동 전 실행
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
//		return super.preHandle(request, response, handler);
		System.out.println("preHandle");
		HandlerMethod method = (HandlerMethod) handler;
		Method methodObj = method.getMethod();
		
		//여기서 작동하는 핸들러 위치
		System.out.println(methodObj);
		System.out.println(method.getBean());
		
		return true;
	}
    
	//컨트롤러 작동 후 실행
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		System.out.println("postHandle");
		
		Map<String, Object> map = modelAndView.getModel();
		
		//컨트롤러에서 바인등한 개체가 없기에 null값이 출력됨
		//컨트롤러에 해당 이름으로 바인딩한 개체가 있다면 그 값이 출력도ㅔㅁ
		Object obj = map.get("hello");
		System.out.println(obj);
		
		
		if (obj != null) {
			HttpSession	session = request.getSession(true);
			session.setAttribute("hello", obj);
			response.sendRedirect("home");
		}
		
		
		super.postHandle(request, response, handler, modelAndView);
	}

	
	
	
}
