package kh.spring.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbcp2.BasicDataSource;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import kh.spring.dto.ContactDTO;

@Component		
public class ContactDAO {
	
	//Mybatis 방식
	
	@Autowired
	private SqlSessionTemplate mybatis;
	
	public int insert(ContactDTO dto) {
		return mybatis.insert("Contact.insert",dto);
		
	}
	
	public List<ContactDTO> selectAll(){
		return mybatis.selectList("Contact.selectAll");
	}
	
	public int selectCount() {
		return mybatis.selectOne("Contact.selectCount");
	}
	public int deleteBySeq (int seq) {
		return mybatis.delete("Contact.deleteBySeq",seq);
	}
	
	public int update(String column,String value, int seq) {
		
		Map<String,String> map = new HashMap<>();
		map.put("column", column);
		map.put("value", value);
		map.put("seq", String.valueOf(seq));
		
		// HashMap 컬렉션이며, key와 value로 이루어진 JAVASCRIPT와 비슷한 형태의 자료형태
		return mybatis.update("Contact.update",map);
	}
	
	public List<ContactDTO> search(int seq){
		return mybatis.selectList("Message.selectBySeq",seq);
		
		// return mybatis.selectOne("Contact.selectByKeyword",keyword); // DTO 하나만 바꾸는 상황이라면,
	}
	
	public List<ContactDTO> searchByMultiCon(ContactDTO dto){
		return mybatis.selectList("Contact.selectByMultiCon", dto);
	}
	
}
	//Spring JDBC 방식
//	
//	// DBCP가 세팅이 되어 있기 때문에 DBCP를 직접 사용하는 것과 동일하다.
//	@Autowired
//	private JdbcTemplate jdbc;
//	
//	
//	public int insert(ContactDTO dto) throws Exception{
//		
//		String sql = "insert into contact values(contact_seq.nextval,?,?)";
//		return jdbc.update(sql,dto.getName(),dto.getContact());
//		
//	}
//	public int delete(int seq) throws Exception {
//		String sql = "delete from contact where seq = ?";
//		return jdbc.update(sql, seq);
//		
//	}
//	public int update(ContactDTO dto) throws Exception{
//		String sql ="update contact set name =?, contact =? where seq = ?";
//		return jdbc.update(sql, dto.getName(),dto.getContact(),dto.getSeq());
//	}
//	public int selectCount() throws Exception{
//		String sql = "select count(*) from contact";
//		return jdbc.queryForObject(sql, Integer.class);
//	}
//	
//	public List<ContactDTO> search(int seq) throws Exception{
//		String sql = "select * from contact where seq = ?";
//		
//		
//		return jdbc.query(sql, new RowMapper<ContactDTO>(){
//			@Override
//			public ContactDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
//				
//				ContactDTO dto = new ContactDTO();
//				dto.setSeq(rs.getInt("seq"));
//				dto.setName(rs.getString("name"));
//				dto.setContact(rs.getString("contact"));
//				return dto;
//				
//			}
//		},seq);
//		
//		// 하나의 데이터를 검색하는게 확정되는 경우 (ex: 마이페이지 or 내 정보 보기)
////		return jdbc.queryForObject(sql, new RowMapper<ContactDTO>() {
////
////			@Override
////			public ContactDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
////				ContactDTO dto = new ContactDTO();
////				dto.setSeq(rs.getInt("seq"));
////				dto.setName(rs.getString("name"));
////				dto.setContact(rs.getString("contact"));
////				return dto;
////			}
////			
////		},seq);
//	}
//	public List<ContactDTO> selectAll() throws Exception{
//		
//		String sql = "select * from contact";
//		
//		return jdbc.query(sql, new RowMapper<ContactDTO>(){
//			@Override
//			public ContactDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
//				
//				ContactDTO dto = new ContactDTO();
//				dto.setSeq(rs.getInt("seq"));
//				dto.setName(rs.getString("name"));
//				dto.setContact(rs.getString("contact"));
//				return dto;
//				
//			}
//		});
//		
//
//		
	  	// 바닐라 DBCP 방식
		// jdbc.query :  List 데이터를 가져올 때 사용
		// jdbc.queryForObject : 한개의 데이터를 가져 올 때, 사용한다. ( DTO, int) 
		
	
//	public int insert(ContactDTO dto) throws Exception {
//		
//		String sql = "insert into contact values(contact_seq.nextval,?,?)";
//		try(Connection con = bds.getConnection();
//		PreparedStatement pstat = con.prepareStatement(sql);){
//			pstat.setString(1, dto.getName());
//			pstat.setString(2, dto.getContact());
//			return pstat.executeUpdate();
//			
//		}
//	}
//	
//	public List<ContactDTO> selectAll() throws Exception{
//		String sql = "select * from contact";
//		try(Connection con = bds.getConnection();
//				PreparedStatement pstat = con.prepareStatement(sql);
//				ResultSet rs = pstat.executeQuery();){
//			
//			List<ContactDTO> list = new ArrayList<>();
//			while(rs.next()) {
//				int seq = rs.getInt("seq");
//				String name = rs.getString("name");
//				String contact = rs.getString("contact");
//				
//				list.add(new ContactDTO(seq,name,contact));
//			}
//			return list;
//		}
//		
//	}
//	public int delete(int num) throws Exception{
//		
//		String sql = "delete from contact where seq = ?";
//		
//		try(Connection con = bds.getConnection();
//				PreparedStatement pstat = con.prepareStatement(sql);){
//			
//				pstat.setInt(1, num);
//				int result = pstat.executeUpdate();
//				return result;
//			}
//			
//		}
//	
//	public int update(ContactDTO dto) throws Exception{
//		
//		String sql ="update contact set name =?, contact =? where seq = ?";
//		
//		try(Connection con = bds.getConnection();
//				PreparedStatement pstat = con.prepareStatement(sql);){
//			
//			
//			pstat.setString(1, dto.getName());
//			pstat.setString(2, dto.getContact());
//			pstat.setInt(3, dto.getSeq());
//			
//			int result = pstat.executeUpdate();
//			return result;
//		}
//		
//	}
	
	

