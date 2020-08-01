package com.edifice.lora.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.edifice.lora.entity.Device;
import com.edifice.lora.entity.GroupDevices;
import com.edifice.lora.mapper.DeviceMapper;
import com.edifice.lora.mapper.GroupDeviceMapper;

@Repository
@Component(value="deviceDao")
public class DeviceDaoImpl {
	
	@Autowired
	JdbcTemplate template;  

	public DeviceDaoImpl(JdbcTemplate template) 
	{  
        this.template = template;  
	}
	
	public DeviceDaoImpl()
	{
		
	}

	public List<Device> findAll() 
	{
		// TODO Auto-generated method stub
		String sql = "select * from devices left outer join devicetype on devicetype.id = devices.deviceTypeId" ;
					
				  
		return template.query(sql, new DeviceMapper());
	}
	
	public List<Device> getDeviceByDeviceId(String deviceId) 
	{
		// TODO Auto-generated method stub
		String sql = "select * from devices left outer join devicetype on devicetype.id = devices.deviceTypeId where deviceId = ?" ;
					
				  
		return template.query(sql,new PreparedStatementSetter() {
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setString(1, deviceId);
            }
        } ,new DeviceMapper());
	}
	
	public List<GroupDevices> getInfoByDeviceToken(String accessToken) 
	{
		// TODO Auto-generated method stub
		String sql = "select * " + 
				"from  group_devices " + 
				"left outer join device on device.id = group_devices.device_id " + 
				"left outer join solutions_group on solutions_group.id = group_devices.solution_group_id " + 
				"left outer join groups on groups.id = solutions_group.group_id " + 
				"left outer join solutions on solutions.id = solutions_group.solution_id  " + 
				"where is_deleted = false and access_token = ?" ;
					
				  
		return template.query(sql,new PreparedStatementSetter() {
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setString(1, accessToken);
            }
        } ,new GroupDeviceMapper());
	}

	
	

}
