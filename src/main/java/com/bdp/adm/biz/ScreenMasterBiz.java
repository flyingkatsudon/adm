package com.bdp.adm.biz;

import com.bdp.adm.vo.ScreenMasterVO;
import com.bdp.adm.vo.TrendKeywordsVO;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface ScreenMasterBiz {
	
	// Taxonomy Setting
	public ScreenMasterVO setScreenMasterVO(String[] data);
	
	// Select All
	public String selectAllScreenMaster(String action) throws JsonProcessingException;
	
	// Select One
	public ScreenMasterVO selectOneScreenMaster(String DT_RowId, String columnName, String newValue);
	
	// Insert
	public int insertScreenMaster(String[] data, String currentUser);
	
	// Update
	public int updateScreenMaster(String[] data, String currentUser);
	
	// Delete
	public int deleteScreenMaster(String[] data, String currentUser);

}
