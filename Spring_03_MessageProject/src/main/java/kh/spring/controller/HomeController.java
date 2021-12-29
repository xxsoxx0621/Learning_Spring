package kh.spring.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kh.spring.dao.MessageDAO;
import kh.spring.dto.MessageDTO;


@Controller
public class HomeController {
	
	@Autowired
	public MessageDAO dao;
	
	@RequestMapping("/")
	public String home() {
		
		return "home";
	}
	
	@RequestMapping("inputForm")
	public String inputFrom() {
		return "inputForm";
	}
	
	@RequestMapping("outputView")
	public String outputView(Model model) throws Exception {
		List<MessageDTO> list = dao.selectAll();
		model.addAttribute("list", list);
		return "outputView";
	}
	

	@RequestMapping("inputProc")
	public String inputProc(MessageDTO dto) throws Exception{
		int result = dao.insert(dto);
		return "redirect:/";
	}
	
	@RequestMapping("deleteProc")
	public String deleteProc(int seq) throws Exception{
		int result = dao.delete(seq);
		return "redirect:outputView";
	}
	
	@RequestMapping("updateProc")
	public String deleteProc(MessageDTO dto) throws Exception{
		int result = dao.update(dto);
		return "redirect:outputView";
	}
	
	
	
	// 예외의 종류를 나눌 수 있음.
		@ExceptionHandler(Exception.class)
			public String exceptionHandler(Exception e) {
				e.printStackTrace();
				System.out.println("예외 코드가 실행되었습니다.");
				return "redirect:/";
				
			}
}
