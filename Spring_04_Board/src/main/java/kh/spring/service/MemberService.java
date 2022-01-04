package kh.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kh.spring.dao.MemberDAO;
import kh.spring.dto.MemberDTO;

@Service
public class MemberService {
	
	@Autowired
	private MemberDAO mdao;
	
	public int idDuplCheck(String id) throws Exception  {
		return mdao.idDuplCheck(id);
	}
	
	public int insert(MemberDTO dto) throws Exception {
		return mdao.insert(dto);
	}
	
	public int login(String id, String pw) {
		return mdao.login(id,pw);
	}
	
	public int delete(String id) {
		return mdao.delete(id);
		
	}
	
	public List<MemberDTO> selectAll(String id){
		return mdao.selectAll(id);
	}
	
	public int updateMem(MemberDTO dto) {
		return mdao.updateMem(dto);
	}
	
}
