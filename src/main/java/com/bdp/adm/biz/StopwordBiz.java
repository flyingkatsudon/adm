package com.bdp.adm.biz;

import com.bdp.adm.vo.StopwordVO;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface StopwordBiz {
	
	// Taxonomy Setting
	public StopwordVO setStopwordVO(String[] data);
	
	// Select All
	public String selectAllStopword(String action) throws JsonProcessingException;
	
	// Select One
	public StopwordVO selectOneStopword(String DT_RowId, String columnName, String newValue);
	
	// Insert
	public int insertStopword(String[] data, String currentUser);
	
	// Update
	public int updateStopword(String[] data, String currentUser);
	
	// Delete
	public int deleteStopword(String[] data, String currentUser);

}
