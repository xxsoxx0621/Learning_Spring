package kh.spring.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

public class Testinterceptor implements HandlerInterceptor{
	
	@Autowired
	private HttpSession session;
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		String id = (String) session.getAttribute("loginID");
		
		if(id == null) {
			response.sendRedirect("/");
			return false;
		}
		
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}

}
