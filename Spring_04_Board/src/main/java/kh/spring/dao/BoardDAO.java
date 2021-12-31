package kh.spring.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import kh.spring.dto.BoardDTO;

@Repository
public class BoardDAO {
	
	@Autowired
	private JdbcTemplate jdbc;
	
	public List<BoardDTO> selectAll(){
		
		String sql = "select * from board order by seq desc";
		return jdbc.query(sql, new RowMapper<BoardDTO>() {

			@Override
			public BoardDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
				BoardDTO dto = new BoardDTO();
				dto.setSeq(rs.getInt("seq"));
				dto.setWriter(rs.getString("writer"));
				dto.setTitle(rs.getString("title"));
				dto.setContents(rs.getString("contents"));
				dto.setWrite_date(rs.getTimestamp("write_date"));
				dto.setView_count(rs.getInt("view_count"));
				
				return dto;
			}
		}, null);
		
	}
	public int saveWrite(BoardDTO dto, String id) {
		String sql = "insert into board(seq,writer,title,contents,write_date,view_count) values(board_seq.nextval,?,?,?,sysdate,default)";
		return jdbc.update(sql,id,dto.getTitle(),dto.getContents());
				
	}
	
public List<BoardDTO> selectBySeq(int seq){
		
		String sql = "select * from board where seq = ?";
		return jdbc.query(sql, new RowMapper<BoardDTO>() {

			@Override
			public BoardDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
				BoardDTO dto = new BoardDTO();
				dto.setSeq(rs.getInt("seq"));
				dto.setWriter(rs.getString("writer"));
				dto.setTitle(rs.getString("title"));
				dto.setContents(rs.getString("contents"));
				dto.setWrite_date(rs.getTimestamp("write_date"));
				dto.setView_count(rs.getInt("view_count"));
				
				return dto;
			}
		}, seq);
		
	}

	public int delete(int seq) {
		String sql = "delete from board where seq = ? ";
		return jdbc.update(sql,seq);
		
	}
	
	public int update(BoardDTO dto ) {
		String sql = "update board set title = ?, contents = ? where seq = ? ";
		return jdbc.update(sql,dto.getTitle(),dto.getContents(),dto.getSeq());
		
	}
}
