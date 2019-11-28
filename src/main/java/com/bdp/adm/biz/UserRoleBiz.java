package com.bdp.adm.biz;

import com.bdp.adm.vo.UserRoleVO;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface UserRoleBiz {
	// UserVO Setting
	public UserRoleVO setUserRoleVO(String[] data);
	
	// Select All
	public String selectAllUserRole(String action) throws JsonProcessingException;
	
	// Select One
	public UserRoleVO selectOneUserRole(String DT_RowId, String columnName, String newValue);
	
	// Insert
	public int insertUserRole(String[] data, String currentUser);
	
	// Update
	public int updateUserRole(String[] data, String currentUser);
	
	// Delete
	public int deleteUserRole(String[] data, String currentUser);
}
