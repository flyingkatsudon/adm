package com.bdp.adm.biz;

import com.bdp.adm.vo.DocFilterVO;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface DocFilterBiz {
	// Data --> docFilterVO
	public DocFilterVO setDocFilterVO(String[] data);
	
	// Select All
	public String selectAllDocFilter(String action) throws JsonProcessingException;
	
	// Select One
	public DocFilterVO selectOneDocFilter(String DT_RowId, String columnName, String newValue);
	
	// Insert
	public int insertDocFilter(String[] data, String currentUser);
	
	// Update
	public int updataeDocFilter(String[] data, String currentUser);
	
	// Delete
	public int deleteDocFilter(String[] data, String currentUser);
	

}
