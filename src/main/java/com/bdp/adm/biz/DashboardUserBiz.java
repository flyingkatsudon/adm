package com.bdp.adm.biz;

import com.bdp.adm.vo.UserVO;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface DashboardUserBiz {
	// Taxonomy Setting
	public UserVO setUserVO(String[] data);
	
	// Select All
	public String selectAllUser() throws JsonProcessingException;
	
	// Select One
	public UserVO selectOneUser(String DT_RowId, String columnName, String newValue);
	
	// Insert
	public int insertUser(String[] data);
	
	// Update
	public int updateUser(String[] data);
	
	// Delete
	public int deleteUser(String[] data);

}
