package kh.spring.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class HomeController {
	

	
	@Autowired
	private HttpSession session;
	

	@RequestMapping("/")
	public String home(HttpServletRequest request) {
	
		//session.setAttribute("loginID", "sohyun");
		session.setAttribute("loginID", request.getRemoteAddr()); //IP 주소로 확인 하는 방법
		return "home";
	}
	
}
