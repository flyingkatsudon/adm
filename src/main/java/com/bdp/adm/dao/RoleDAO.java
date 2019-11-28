package com.bdp.adm.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.bdp.adm.vo.RoleVO;

@Repository
public interface RoleDAO {
	
	// Select All
	public List<RoleVO> selectAllRole(String action);
	
	// Select One
	public RoleVO selectOneRole(String DT_RowId);
	
	// Insert
	public int insertRole(RoleVO roleVO);
	
	// Update
	public int updateRole(RoleVO roleVO);
	
	// Delete
	public int deleteRole(RoleVO roleVO);
    
}
