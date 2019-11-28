package com.bdp.adm.biz;

import com.bdp.adm.vo.TitleNameVO;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface TitleNameBiz {
	
	// Taxonomy Setting
	public TitleNameVO setTitleNameVO(String[] data);
	
	// Select All
	public String selectAllTitleName(String action) throws JsonProcessingException;
	
	// Select One
	public TitleNameVO selectOneTitleName(String DT_RowId, String columnName, String newValue);
	
	// Insert
	public int insertTitleName(String[] data, String currentUser);
	
	// Update
	public int updateTitleName(String[] data, String currentUser);
	
	// Delete
	public int deleteTitleName(String[] data, String currentUser);

}
