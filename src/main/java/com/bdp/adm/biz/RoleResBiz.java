package com.bdp.adm.biz;

import java.util.Map;

import com.bdp.adm.vo.RoleResVO;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface RoleResBiz {
	// UserVO Setting
	public RoleResVO setRoleResVO(String[] data);
	
	// Select All
	public String selectAllRoleRes(String action) throws JsonProcessingException;
	
	// Select One
	public RoleResVO selectOneRoleRes(String DT_RowId, String columnName, String newValue);
	
	// Insert
	public int insertRoleRes(String[] data, String currentUser);
	
	// Update
	public int updateRoleRes(String[] data, String currentUser);
	
	// Delete
	public int deleteRoleRes(String[] data, String currentUser);
	
	// Meta
	public Map<String, Object> getRoleResMeta();
}
