package kh.spring.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import kh.spring.dto.BoardDTO;

@Repository
public class BoardDAO {
	
	//Mybatis 방식
	
	@Autowired
	private SqlSessionTemplate mybatis;
	
	
	public List<BoardDTO> selectAll(){
		
		return mybatis.selectList("Board.selectAll");
	}
	public int saveWrite(BoardDTO dto) {
		int result = mybatis.insert("Board.saveWrite",dto);
		return dto.getSeq();
				
	}
	
	public List<BoardDTO> selectBySeq(int seq){
		return mybatis.selectList("Board.selectBySeq",seq);
	}
	

	public int delete(int seq) {
		return mybatis.delete("Board.delete",seq);
		
	}
	
	public int update(BoardDTO dto ) {
		return mybatis.update("Board.update",dto);
		
	}
}
