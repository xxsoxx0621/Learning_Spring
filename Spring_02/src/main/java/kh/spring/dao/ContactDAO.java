package kh.spring.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kh.spring.dto.ContactDTO;

@Component
public class ContactDAO {
	
	@Autowired
	private BasicDataSource bds;
	
	public int insert(ContactDTO dto) throws Exception{
		String sql = "insert into contact values(contact_seq.nextval,?,?)";
		
		try(Connection con = bds.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
					pstat.setString(1, dto.getName());
					pstat.setString(2, dto.getContact());
					return pstat.executeUpdate();
					
				}
	}
	
}
