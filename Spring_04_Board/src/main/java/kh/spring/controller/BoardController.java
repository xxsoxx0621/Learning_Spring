package kh.spring.controller;

import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


import kh.spring.dto.BoardDTO;
import kh.spring.dto.FileDTO;
import kh.spring.service.BoardService;
import kh.spring.service.FileService;

@Controller
@RequestMapping("/board/")
public class BoardController {
	
	@Autowired
	private BoardService bService;
	
	@Autowired
	private FileService fService;
	
	@Autowired
	private HttpSession session;
	
	
	@RequestMapping("list")
	public String list(Model model) {
		List<BoardDTO> list = bService.selectAll();
		model.addAttribute("list", list);
	
		return "board/list";
	}
	
	@RequestMapping("writeForm")
	public String writeForm() {
		return "board/writeForm";
	}
	
	@RequestMapping("saveWrite")
	public String saveWrite(BoardDTO dto,  MultipartFile[] file) throws Exception {
		String id = (String) session.getAttribute("loginID");
		dto.setWriter(id);
		int parentSeq = bService.saveWrite(dto); // 게시판 작성된 내용을 DB에 저장하는 구문
		
		if(!file[0].isEmpty()) {	 // 업로드 된 파일 중 첫번째 파일이 비어있지 않다면
				
				for(MultipartFile mf : file) {
					
					// if(mf.isEmpty()) {} !file[0].isEmpty 대신에 for문 내부에 넣어줘도 된다.				
					String realPath = session.getServletContext().getRealPath("upload");
					File realPathFile = new File(realPath);
					if(!realPathFile.exists()) { realPathFile.mkdir();} // 폴더가 없으면 만들어라
					String oriName  = mf.getOriginalFilename(); // 사용자가 업로드 한 파일의 원본 내용
					String sysName = UUID.randomUUID() + "_" +oriName; // 서버 쪽에 저장할 실제 파일 이름
					
					// 서버에 업로드 되어 메모리에 적재된 파일의 내용을 어디에 저장할지 결정하는 부분
					mf.transferTo(new File (realPath + "/" + sysName ));
//					FileDTO fdto = new FileDTO(0, oriName,sysName,parentSeq);
					
					fService.insert(new FileDTO(0, oriName,sysName,parentSeq)); // 첨부된 파일 정보를 DB에 저장하는 부분
					
					
					
				}
			}
		
		
		
		return "redirect:list";
	}
	@RequestMapping("detail")
	public String detail(@RequestParam int seq, Model model) {
		List<BoardDTO> seqList = bService.selectBySeq(seq);
		List<FileDTO> fileList = fService.selectAll(seq);
		model.addAttribute("seqList", seqList);
		model.addAttribute("fileList", fileList);
		return "board/detail";
		
	}
	
	@RequestMapping("deleteProc")
	public String deleteProc(int seq) {
		int result = bService.delete(seq);
		return "redirect:list";
	}
	
	
	@RequestMapping("updateProc")
	public String updateProc( BoardDTO dto) {
		int result = bService.update(dto);
		if(result > 0) {
			System.out.println("수정이 완료되었습니다.");
		}
		return "redirect:list";
	}
	
	
	
}
