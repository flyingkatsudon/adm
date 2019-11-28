package com.bdp.adm.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.bdp.adm.vo.RoleResMetaVO;
import com.bdp.adm.vo.RoleResVO;

@Repository
public interface RoleResDAO {
	// Select All
	public List<RoleResVO> selectAllRoleRes(String action);
	
	// Select One
	public RoleResVO selectOneRoleRes(String DT_RowId);
	
	// Insert
	public int insertRoleRes(RoleResVO roleVO);
	
	// Update
	public int updateRoleRes(RoleResVO roleVO);
	
	// Delete
	public int deleteRoleRes(RoleResVO roleVO);
	
	// Meta
	public ArrayList<RoleResMetaVO> selectRoleName();
	public ArrayList<RoleResMetaVO> selectResTypeName();
    
}
