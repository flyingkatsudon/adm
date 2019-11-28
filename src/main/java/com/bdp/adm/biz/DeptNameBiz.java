package com.bdp.adm.biz;

import com.bdp.adm.vo.DeptNameVO;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface DeptNameBiz {
	
	public DeptNameVO setDeptNameVO(String[] data);
	
	// Select All
	public String selectAllDeptName(String action) throws JsonProcessingException;
	
	// Insert
	public int insertDeptName(String[] data, String currentUser);
	
	// Update
	public int updateDeptName(String[] data, String currentUser);
	
	// Delete
	public int deleteDeptName(String[] data, String currentUser);

}
