package com.bdp.adm.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.bdp.adm.vo.DeptNameVO;

@Repository
public interface DeptNameDAO {
	// Select All
	public List<DeptNameVO> selectAllDeptName(String action);
	
	// Insert
	public int insertDeptName(DeptNameVO deptNameVO);
	
	// Update
	public int updateDeptName(DeptNameVO deptNameVO);
	
	// Delete
	public int deleteDeptName(DeptNameVO deptNameVO);
}
