package kh.spring.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kh.spring.dto.FileDTO;

@Repository
public class FileDAO {
	
	//Mybatis 방식
	
	@Autowired
	private SqlSessionTemplate mybatis;
	
	public int insert(FileDTO dto) {
		return mybatis.insert("Files.insert",dto);
	}
	
	public List<FileDTO> selectAll(int parentSeq){
		return mybatis.selectList("Files.selectAll",parentSeq);
	}

}
