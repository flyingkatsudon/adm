package com.bdp.adm.biz;

import com.bdp.adm.vo.StatusNameVO;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface StatusNameBiz {
	
	// Taxonomy Setting
	public StatusNameVO setStatusNameVO(String[] data);
	
	// Select All
	public String selectAllStatusName(String action) throws JsonProcessingException;
	
	// Insert
	public int insertStatusName(String[] data, String currentUser);
	
	// Update
	public int updateStatusName(String[] data, String currentUser);
	
	// Delete
	public int deleteStatusName(String[] data, String currentUser);

}
