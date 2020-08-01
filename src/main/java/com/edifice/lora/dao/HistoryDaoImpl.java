package com.edifice.lora.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.edifice.lora.entity.Log;
import com.edifice.lora.entity.Readings;

@Repository
@Component(value="historyDao")
public class HistoryDaoImpl 
{
	@Autowired
	JdbcTemplate template;  

	public HistoryDaoImpl(JdbcTemplate template) 
	{  
        this.template = template;  
	}
	
	public HistoryDaoImpl()
	{
		
	}

	public Log addReqHistory(Log readings) 
	{
		final KeyHolder holder = new GeneratedKeyHolder();

       MapSqlParameterSource parameters = new MapSqlParameterSource() ;

		
       final String insertTelemetry = "INSERT INTO logs( device_id, things_request, created)" + 
       		"	VALUES ( ?, ?, now());";
       PreparedStatementCreator statement = new PreparedStatementCreator() {

			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				// TODO Auto-generated method stub
				PreparedStatement psc = connection.prepareStatement(insertTelemetry.toString(),
	                       new String[] {"logid"});
				
				
				if(readings.getDeviceId() == null )
					psc.setNull(1,java.sql.Types.VARCHAR);
				else
					psc.setString(1,readings.getDeviceId());
				
				if(readings.getThingsRequest() == null )
					psc.setNull(2,java.sql.Types.VARCHAR);
				else
					psc.setString(2,readings.getThingsRequest());
								
				
				return psc;
			}
           
       };
		
	        
       
		template.update(statement,holder);
		
		 Integer id = holder.getKey().intValue();
		
		 readings.setLogId(id); 
		
		return readings ;


		
	}



}
