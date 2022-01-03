package kh.spring.controller;

import java.io.File;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/file/")
public class FileController {
	
	@Autowired
	private HttpSession session;
	
	@RequestMapping("upload")
	public String upload(String title, String contents, MultipartFile[] file) throws Exception {
		
		if(!file[0].isEmpty()) {	 // 업로드 된 파일 중 첫번째 파일이 비어있지 않다면
			
			for(MultipartFile mf : file) {
				
				// if(mf.isEmpty()) {} !file[0].isEmpty 대신에 for문 내부에 넣어줘도 된다.
				
				String realPath = session.getServletContext().getRealPath("upload");
				
				File realPathFile = new File(realPath);
				if(!realPathFile.exists()) { realPathFile.mkdir();} // 폴더가 없으면 만들어라
				 
				String oriName = mf.getOriginalFilename(); // 사용자가 업로드 한 파일의 원본 내용
				String sysName = UUID.randomUUID() + "_" +oriName; // 서버 쪽에 저장할 실제 파일 이름
				
				// 서버에 업로드 되어 메모리에 적재된 파일의 내용을 어디에 저장할지 결정하는 부분
				mf.transferTo(new File (realPath + "/" + sysName ));
				
				
//				System.out.println(title + " : " + contents);
//				System.out.println(file.getOriginalFilename());
				
			}
		}
		
		
		return "redirect:/";
	
	}
}
