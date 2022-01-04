package kh.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kh.spring.dao.BoardDAO;
import kh.spring.dto.BoardDTO;

@Service
public class BoardService {
	
	@Autowired
	private BoardDAO bdao;
	
	public List<BoardDTO> selectAll(){
		return bdao.selectAll();
		
	}
	
//	public BoardDTO setWriter(String id) {
//		BoardDTO dto = new BoardDTO();
//		BoardDTO result = dto.setWriter(id);
//		return result;
//	}
	
	public int saveWrite(BoardDTO dto) {
		return bdao.saveWrite(dto);
	}
	
	public List<BoardDTO> selectBySeq(int seq){
		return bdao.selectBySeq(seq);
	}
	
	public int delete(int seq) {
		return bdao.delete(seq);
	}
	
	public int update(BoardDTO dto) {
		return bdao.update(dto);
	}
}
