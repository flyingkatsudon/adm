package com.bdp.adm.biz;

import com.bdp.adm.vo.ScreenLinkVO;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface ScreenLinkBiz {

	public ScreenLinkVO setScreenLinkVO(String[] data);
	
	// Select All
	public String selectAllScreenLink(String action) throws JsonProcessingException;
	
	// Insert
	public int insertScreenLink(String[] data, String currentUser);
	
	// Update
	public int updateScreenLink(String[] data, String currentUser);
	
	// Delete
	public int deleteScreenLink(String[] data, String currentUser);
	
}
