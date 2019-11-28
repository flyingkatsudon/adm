package com.bdp.adm.biz;

import com.bdp.adm.vo.ScreenArchiVO;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface ScreenArchiBiz {

	public ScreenArchiVO setScreenArchiVO(String[] data);
	
	// Select All
	public String selectAllScreenArchi(String action) throws JsonProcessingException;
	
	// Insert
	public int insertScreenArchi(String[] data, String currentUser);
	
	// Update
	public int updateScreenArchi(String[] data, String currentUser);
	
	// Delete
	public int deleteScreenArchi(String[] data, String currentUser);

}
