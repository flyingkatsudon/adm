package com.bdp.adm.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.bdp.adm.vo.UserRoleVO;

@Repository
public interface UserRoleDAO {
	// Select All
	public List<UserRoleVO> selectAllUserRole(Map<String, String> param);
	
	// Select One
	public UserRoleVO selectOneUserRole(String DT_RowId);
	
	// Insert
	public int insertUserRole(UserRoleVO userRoleVO);
	
	// Update
	public int updateUserRole(UserRoleVO userRoleVO);
	
	// Delete
	public int deleteUserRole(UserRoleVO userRoleVO);
}
