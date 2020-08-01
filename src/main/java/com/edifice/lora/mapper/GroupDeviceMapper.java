package com.edifice.lora.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.edifice.lora.entity.Device;
import com.edifice.lora.entity.DeviceType;
import com.edifice.lora.entity.Group;
import com.edifice.lora.entity.GroupDevices;
import com.edifice.lora.entity.Solutions;

public class GroupDeviceMapper implements RowMapper<GroupDevices> 
{
	public GroupDevices mapRow(ResultSet rs, int arg1) throws SQLException 
	{
		// TODO Auto-generated method stub
		GroupDevices grpDevice = new GroupDevices();
		
		grpDevice.setId(rs.getInt("group_device_id"));
		grpDevice.setDeviceId(rs.getInt("device_id"));
		grpDevice.setSolutionGroupId(rs.getInt("solution_group_id"));
		grpDevice.setIsDeleted(rs.getBoolean("is_deleted"));
		grpDevice.setEndpointKey(rs.getString("endpoint_key"));
		
		Device device = new Device();
		device.setId(rs.getInt("device_id"));
		device.setDeviceId(rs.getString("device_uid"));
		device.setName(rs.getString("device_name"));
		device.setDescription(rs.getString("description"));
		device.setDeviceTypeId(rs.getInt("device_type_id"));
		device.setStatus(rs.getString("status"));
		
		DeviceType deviceType = new DeviceType();
		//deviceType.setId(rs.getInt("deviceTypeId"));
		
		device.setDeviceType(deviceType);
		
		grpDevice.setDevice(device);
		
		
		Group grp = new Group();
		
		grp.setGroupName(rs.getString("group_name"));
		grp.setId(rs.getInt("group_id"));
		grpDevice.setGroup(grp);
		
		
		Solutions soln = new Solutions();
		
		soln.setDescription(rs.getString("description"));
		soln.setId(rs.getInt("solution_id"));
		soln.setName(rs.getString("name"));
		grpDevice.setSolution(soln);
		
		return grpDevice;
		
	}

}
