package kh.spring.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kh.spring.dao.MemberDAO;
import kh.spring.dto.MemberDTO;
import kh.spring.utils.EncryptionUtils;



@Controller
@RequestMapping("/member/")
public class MemberController {

	@Autowired
	private MemberDAO dao;

	@Autowired
	private HttpSession session;
	

		@RequestMapping("join")
		public String memberJoin() {
			System.out.println("Join 페이지 동작");
			return "member/join";
		}

		@ResponseBody
		@RequestMapping(value="idDuplCheck", produces="text/html;charset=utf8")
		public String idDuplCheck(String id) throws Exception  {
			// 중복검사를 하고, 
			// 결과를 Eclipse 콘솔에 출력하는 것까지 만드세요.
			int result =  dao.idDuplCheck(id);
			return String.valueOf(result);
		}

		@RequestMapping("signUpProc")
		public String signUpProc(MemberDTO dto) throws Exception{
			String encPw = EncryptionUtils.getSHA512(dto.getPw());
			dto.setPw(encPw);
			int result = dao.insert(dto);
			return "home";
		}

		@RequestMapping("login")
		public String login(String id, String pw) throws Exception{
			pw = EncryptionUtils.getSHA512(pw);
			int result = dao.login(id,pw);
			if(result > 0) {
				session.setAttribute("loginID", id);
			}
			return "redirect:/";

		}

		@RequestMapping("logout")
		public String logout() throws Exception{
			session.removeAttribute("loginID");
			return "redirect:/";

		}
		@RequestMapping("deleteMem")
		public String deleteMem() throws Exception{
			String id = (String) session.getAttribute("loginID");
			int result = dao.delete(id);

			if(result > 0) {
				System.out.println("계정이 탈퇴되었습니다.");
				session.removeAttribute("loginID");
			}
			return "redirect:/";

		}

		@RequestMapping("myPage")
		public String myPage(Model model) throws Exception{
			try {
				String id = (String) session.getAttribute("loginID");
				List<MemberDTO> list = dao.selectAll(id);
				model.addAttribute("list", list);
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			return "member/myPage";
		}
		@RequestMapping("updateMem")
		public String updateMem(MemberDTO dto) throws Exception{
			String id = (String) session.getAttribute("loginID");
			int result = dao.updateMem(dto,id);
			if(result > 0) {
				System.out.println("정보수정이 완료 되었습니다");	
			}
			return "redirect:/";
			
		}
	}



