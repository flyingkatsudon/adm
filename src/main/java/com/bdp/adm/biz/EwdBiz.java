package com.bdp.adm.biz;

import com.bdp.adm.vo.EwdVO;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface EwdBiz {
	// Data --> ewdVO
	public EwdVO setEwdVO(String[] data);
	
	// Select All
	public String selectAllEwd(String action) throws JsonProcessingException;
	
	// Select One
	public EwdVO selectOneEwd(String DT_RowId, String columnName, String newValue);
	
	// Insert
	public int insertEwd(String[] data, String currentUser);
	
	// Update
	public int updateEwd(String[] data, String currentUser);
	
	// Delete
	public int deleteEwd(String[] data, String currentUser);
	

}
