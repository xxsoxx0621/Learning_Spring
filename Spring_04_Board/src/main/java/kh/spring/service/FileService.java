package kh.spring.service;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kh.spring.dao.FileDAO;
import kh.spring.dto.FileDTO;

@Service
public class FileService {
	
	@Autowired
	private FileDAO fdao;
	
	public int insert(FileDTO dto) {
		return fdao.insert(dto);
	}
//	public int insert(String oriName,String sysName, int parentSeq) {
//		return fdao.insert(new FileDTO(0, oriName,sysName,parentSeq));
//	}
//	
	public List<FileDTO> selectAll(int seq){
		return fdao.selectAll(seq);
	}

	public void download(HttpServletResponse response, String oriName, String sysName,String realPath) throws Exception {
		
		File target = new File(realPath+"/"+sysName);							// sysName과 결합하여 대상 파일 객체 생성 
		
		try(DataInputStream dis = new DataInputStream(new FileInputStream(target)); // 대상 파일에 대한 InputStream 개방	
				DataOutputStream dos = new DataOutputStream(response.getOutputStream());){
			
			byte[] fileContents = new byte[(int)target.length()]; 					//  대상 파일을 적재할 메모리 공간 확보 
			dis.readFully(fileContents); 											// 대상 파일 로딩 
			
			response.reset();
			response.setHeader("Content-Disposition", "attachment;filename="+oriName);
			
			dos.write(fileContents);
			dos.flush();
			}
		
		}
	}

	
	

