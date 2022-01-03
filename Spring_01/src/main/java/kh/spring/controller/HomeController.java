package kh.spring.controller;




import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kh.spring.dao.ContactDAO;
import kh.spring.dto.ContactDTO;



@Controller
public class HomeController {
	
	@Autowired
	public ContactDAO dao;
			
	// HttpSession 객체를 가져와서 사용 할 수 있게 만들어줌
	@Autowired
	private HttpSession session;
	
	@RequestMapping("/")
	public String home() {
	
		System.out.print("/로 들어온 요청은 이 메서드를 실행합니다.");
		
		
		// return 값은 세가지만 가능하다.
		// void
		// String
		// ModelAndView
		
		return "home";
		
		//RequestMapping 메서드에서 return 값은 default가 foward이다.
	}
	@RequestMapping("toInput")
	public String toInput()  {
		System.out.println("toInput으로 가는 메서드가 실행되었습니다.");
		return "inputForm";
	}
	
//	@RequestMapping("inputProc")
//	public String inputProc(String name, String contact) {
//		
////		String name = request.getParameter("name");
////		String contact = request.getParameter("contact");
//		System.out.println(name + " : " + contact);
////		System.out.println("데이터 잘 도착!");
//		return "home";
//	}
//	
	
//	// 만약 session 값의 로그인 아이디를 가져오고 싶으면 매개변수로 넣어주면 된다.
//	@RequestMapping("inputProc")
//	public String inputProc(ContactDTO dto, HttpSession session) {
//		
//	
//		System.out.println(dto.getName() + " : " + dto.getContact());
//		
//		String id = (String)session.getAttribute("loginId");
//
//		return "home";
//	}
	// 만약 session 값의 로그인 아이디를 가져오고 싶으면 매개변수로 넣어주면 된다.
	
	
	@RequestMapping("inputProc")
	public String inputProc(ContactDTO dto) throws Exception {
	
			int result = dao.insert(dto);

//		return "home";
		return "redirect:/";
	}
	
	// 스프링에 foward가 redirect로 페이지를 이동해야 할 경우 어떻게 하는가?
	
//	목록을 담아 JSP로 보내는 방법 1	
//	@RequestMapping("outputProc")
//	public ModelAndView outputProc() {
//		ModelAndView mav = new ModelAndView();
//		
//		try {
//			List<ContactDTO> list = dao.selectAll();
//			mav.addObject("list",list);
//			mav.setViewName("output");
//		}catch(Exception e) {
//			e.printStackTrace();
//			return "error";
//		}
//
//		return mav;
//	}
//	}
	

//	목록을 담아 JSP로 보내는 방법 2	
		@RequestMapping("toOutput")
		public String outputProc(Model model)throws Exception {
			
	
			List<ContactDTO> list = dao.selectAll();
			int count = dao.selectCount();
			model.addAttribute("list", list);
			model.addAttribute("count", count);
	
			return "output";
		}
		
		@RequestMapping("deleteProc")
		public String deleteProc(int delTarget)throws Exception {
			
			int result = dao.deleteBySeq(delTarget);
	
			return "redirect:toOutput";
		}
		@RequestMapping("updateProc")
		public String updateProc(String column, String value, int seq) throws Exception{
			int result = dao.update(column, value, seq);
			return "redirect:toOutput";
		}
		
		@RequestMapping("search")
		public String search(int searchSeq, Model model) throws Exception{
			List<ContactDTO> list = dao.search(searchSeq);
			model.addAttribute("list",list);
			return "output";
		}
		
		@RequestMapping("toSearch")
		public String toSearch() {
			return "search";
		}
		
		@RequestMapping("searchByMultiCon")
		public String toSearch(ContactDTO dto) {
			
			List<ContactDTO> list = dao.searchByMultiCon(dto);
			
			for(ContactDTO dtos : list) {
				System.out.println(dtos.getSeq() + " : " + dtos.getName() + " : " + dtos.getContact());
			}
			return "home";
		}
		// 예외의 종류를 나눌 수 있음.
		@ExceptionHandler(Exception.class)
		public String exceptionHandler(Exception e) {
			e.printStackTrace();
			System.out.println("예외 코드가 실행되었습니다.");
			return "redirect:/";
			
		}
//		@ExceptionHandler(NumberFormatException.class)
//		public String exceptionHandler1() {
//			System.out.println("예외 코드가 실행되었습니다.");
//			return "home";
//			
//		}
}
