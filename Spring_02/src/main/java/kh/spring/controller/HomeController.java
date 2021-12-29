package kh.spring.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kh.spring.dao.ContactDAO;
import kh.spring.dto.ContactDTO;


@Controller
public class HomeController {
	
	@Autowired
	public ContactDAO dao;
	
	@RequestMapping("/")
	public String home() {
		
	
		return "home";
	}
	
	@RequestMapping("toInput")
	public String toInput() {
		System.out.println("toInput으로 가는 메서드가 실행되었습니다.");
		return "inputForm";
	}
	
	@RequestMapping("inputProc")
	public String inputProc(ContactDTO dto) {
		try {
			int result = dao.insert(dto);
		}catch(Exception e) {
			e.printStackTrace();
			return "error";
		}

		return "home";
	}
	
}
