package kh.spring.controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;


import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import kh.spring.service.FileService;

@Controller
@RequestMapping("/file/")

public class FileController {
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private FileService fileService;
	
	@RequestMapping("download")
	public void download(HttpServletResponse response, String oriName, String sysName) throws Exception {
		
		String realPath = session.getServletContext().getRealPath("upload"); 	// 파일 위치 경로를 획득 	
		
		fileService.download(response, oriName, sysName, realPath);
		
	}

}
