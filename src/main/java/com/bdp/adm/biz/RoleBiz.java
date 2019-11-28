package com.bdp.adm.biz;

import com.bdp.adm.vo.RoleVO;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface RoleBiz {
	// UserVO Setting
	public RoleVO setRoleVO(String[] data);
	
	// Select All
	public String selectAllRole(String action) throws JsonProcessingException;
	
	// Select One
	public RoleVO selectOneRole(String DT_RowId, String columnName, String newValue);
	
	// Insert
	public int insertRole(String[] data, String currentUser);
	
	// Update
	public int updateRole(String[] data, String currentUser);
	
	// Delete
	public int deleteRole(String[] data, String currentUser);
}
