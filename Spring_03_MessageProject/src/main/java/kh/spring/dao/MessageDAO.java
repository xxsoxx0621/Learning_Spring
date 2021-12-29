package kh.spring.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;


import kh.spring.dto.MessageDTO;

@Component
public class MessageDAO {
	
	@Autowired
	private JdbcTemplate jdbc;
	
	public int insert(MessageDTO dto) throws Exception{
		String sql = "insert into Messages values(Messages_seq.nextval,?,?,sysdate)";
		return jdbc.update(sql,dto.getWriter(),dto.getMessage());
		
	}
	
	public int delete(int seq) throws Exception {
		String sql = "delete from Messages where seq = ?";
		return jdbc.update(sql,seq);
	}
	
	public int update(MessageDTO dto)throws Exception{
		String sql = "update  Messages set writer = ?, message =? where seq = ?";
		return jdbc.update(sql,dto.getWriter(),dto.getMessage(),dto.getSeq());
	}
	public List<MessageDTO> selectAll() throws Exception{
		String sql = "select * from Messages order by seq";
		return jdbc.query(sql, new RowMapper<MessageDTO>() {

			@Override
			public MessageDTO mapRow(ResultSet rs, int rowNum) throws SQLException {

				MessageDTO dto = new MessageDTO();
				dto.setSeq(rs.getInt("seq"));
				dto.setWriter(rs.getString("writer"));
				dto.setMessage(rs.getString("message"));
				dto.setWrite_date(rs.getDate("write_date"));
				return dto;
				
			}
			
		});
	}
//	public int insert(MessageDTO dto) throws Exception{
//		
//		String sql = "insert into Messages values(Messages_seq.nextval,?,?,sysdate)";
//		
//		try(Connection con = bds.getConnection();
//		PreparedStatement pstat = con.prepareStatement(sql)){
//			
//			pstat.setString(1,dto.getWriter());
//			pstat.setString(2, dto.getMessage());
//			
//			int result = pstat.executeUpdate();
//			return result;
//		}
//	}
//	
//	public List<MessageDTO> selectAll() throws Exception {
//		String sql = "select * from Messages order by seq";
//		
//		try(Connection con = bds.getConnection();
//			PreparedStatement pstat = con.prepareStatement(sql);
//			ResultSet rs = pstat.executeQuery()){
//			
//			List<MessageDTO> list = new ArrayList();
//			
//			while(rs.next()) {
//				 
//				int seq = rs.getInt("seq");
//				String writer = rs.getString("writer");
//				String message = rs.getString("message");
//				Date write_date = rs.getDate("write_date");
//				
//				list.add(new MessageDTO(seq,writer,message,write_date));
//			}
//			return list;
//		}
//					
//	}
//	
//	public int delete(int seq) throws Exception{
//		String sql = "delete from Messages where seq = ?";
//		
//		try(Connection con = bds.getConnection();
//			PreparedStatement pstat = con.prepareStatement(sql);){
//			
//			pstat.setInt(1, seq);
//			
//			int result = pstat.executeUpdate();
//			return result;
//		}
//	}
//	
//	public int update(MessageDTO dto) throws Exception{
//		String sql = "update  Messages set writer = ?, message =? where seq = ?";
//		
//		try(Connection con = bds.getConnection();
//			PreparedStatement pstat = con.prepareStatement(sql);){
//			
//			pstat.setString(1, dto.getWriter());
//			pstat.setString(2, dto.getMessage());
//			pstat.setInt(3, dto.getSeq());
//			
//			int result = pstat.executeUpdate();
//			return result;
//		}
//	}
//	
	

}
