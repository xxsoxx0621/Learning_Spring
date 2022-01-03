package kh.spring.dao;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import kh.spring.dto.MemberDTO;


@Repository
public class MemberDAO {

	@Autowired
	private SqlSession mybatis;
	

	public int idDuplCheck(String id) throws Exception{
		   return mybatis.selectOne("Member.idDuplCheck",id);
		
	}
	
	public int insert(MemberDTO dto) throws Exception{
		return mybatis.insert("Member.insert",dto);
	}
	
	public int login(String id, String pw) {
		
		Map<String,String> map = new HashMap<>();
		map.put("id", id);
		map.put("pw", pw);
		
		return mybatis.selectOne("Member.login",map);
	}
	
	public int delete(String id) {
		return mybatis.delete("Member.delete",id);
	}
	
	public List<MemberDTO> selectAll(String id){
		return mybatis.selectList("Member.selectAll",id);
	}
	
	public int updateMem(MemberDTO dto) {		
		return mybatis.update("Member.updateMem",dto);
	}
}
	
