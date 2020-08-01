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

import com.edifice.lora.entity.Readings;


@Repository
@Component(value="readingsDao")
public class ReadingsDaoImpl 
{
	@Autowired
	JdbcTemplate template;  

	public ReadingsDaoImpl(JdbcTemplate template) 
	{  
        this.template = template;  
	}
	
	public ReadingsDaoImpl()
	{
		
	}
	
	public Readings addTelemetry(Readings readings) 
	{
		final KeyHolder holder = new GeneratedKeyHolder();

       MapSqlParameterSource parameters = new MapSqlParameterSource() ;

		
       final String insertTelemetry = "INSERT INTO readings( device_id, endpoint_key, group_id, reading_date, latitude, longitude, value) " + 
       		"	VALUES ( ?, ?, ?, ?, ?, ?, ?);";
       PreparedStatementCreator statement = new PreparedStatementCreator() {

			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				// TODO Auto-generated method stub
				PreparedStatement psc = connection.prepareStatement(insertTelemetry.toString(),
	                       new String[] {"id"});
				
				
				if(readings.getDeviceId() == null )
					psc.setNull(1,java.sql.Types.INTEGER);
				else
					psc.setInt(1,readings.getDeviceId());
				
				if(readings.getEndpointKey() == null )
					psc.setNull(2,java.sql.Types.VARCHAR);
				else
					psc.setString(2,readings.getEndpointKey());
				
				if(readings.getGroupId() == null )
					psc.setNull(3,java.sql.Types.VARCHAR);
				else
					psc.setInt(3,readings.getGroupId());
				
				if(readings.getReadingDate() == null )
					psc.setNull(4,java.sql.Types.DATE);
				else
					psc.setDate(4,readings.getReadingDate());
				
				if(readings.getLatitude() == null )
					psc.setNull(5,java.sql.Types.DOUBLE);
				else
					psc.setDouble(5,readings.getLatitude());
				
				if(readings.getLongitude() == null )
					psc.setNull(6,java.sql.Types.DOUBLE);
				else
					psc.setDouble(6,readings.getLongitude());
				
				if(readings.getValues() == null )
					psc.setNull(7,java.sql.Types.DOUBLE);
				else
					psc.setDouble(7,readings.getValues());

				
				
				return psc;
			}
           
       };
		
	        
       
		template.update(statement,holder);
		
		 Integer id = holder.getKey().intValue();
		
		 readings.setId(id); 
		
		return readings ;


		
	}


}
