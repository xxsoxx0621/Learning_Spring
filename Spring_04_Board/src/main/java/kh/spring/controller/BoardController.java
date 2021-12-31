package kh.spring.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kh.spring.dao.BoardDAO;
import kh.spring.dto.BoardDTO;

@Controller
@RequestMapping("/board/")
public class BoardController {
	
	@Autowired
	private BoardDAO dao;
	
	@Autowired
	private HttpSession session;
	
	
	@RequestMapping("list")
	public String list(Model model) {
		List<BoardDTO> list = dao.selectAll();
		model.addAttribute("list", list);
		return "board/list";
	}
	
	@RequestMapping("writeForm")
	public String writeForm() {
		return "board/writeForm";
	}
	@RequestMapping("saveWrite")
	public String saveWrite(BoardDTO dto) {
		String id = (String) session.getAttribute("loginID");
		int result = dao.saveWrite(dto,id);
		if(result > 0) {
			System.out.println("글이 저장되었습니다.");
		}
		return "redirect:list";
	}
	@RequestMapping("detail")
	public String detail(@RequestParam int seq, Model model) {
		List<BoardDTO> seqList = dao.selectBySeq(seq);
		model.addAttribute("seqList", seqList);
		return "board/detail";
		
	}
	
	@RequestMapping("deleteProc")
	public String deleteProc(int seq) {
		int result = dao.delete(seq);
		return "redirect:list";
	}
	
	
	@RequestMapping("updateProc")
	public String updateProc( BoardDTO dto) {
		int result = dao.update(dto);
		if(result > 0) {
			System.out.println("수정이 완료되었습니다.");
		}
		return "redirect:list";
	}
	
}
