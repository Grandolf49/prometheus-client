package com.edifice.lora.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.edifice.lora.entity.Device;
import com.edifice.lora.entity.DeviceType;


public class DeviceMapper implements RowMapper<Device> 
{
	public Device mapRow(ResultSet rs, int arg1) throws SQLException 
	{
		// TODO Auto-generated method stub
		Device device = new Device();
		
		device.setId(rs.getInt("id"));
		device.setDeviceId(rs.getString("deviceId"));
		device.setName(rs.getString("name"));
		device.setDescription(rs.getString("description"));
		device.setDeviceTypeId(rs.getInt("deviceTypeId"));
		device.setStatus(rs.getString("status"));
		
		DeviceType deviceType = new DeviceType();
		deviceType.setId(rs.getInt("deviceTypeId"));
		
		device.setDeviceType(deviceType);
		
		
		return device;
		
	}

}
