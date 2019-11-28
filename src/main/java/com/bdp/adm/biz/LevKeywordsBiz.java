package com.bdp.adm.biz;

import com.bdp.adm.vo.LevKeywordsVO;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface LevKeywordsBiz {
	// Set VO
	public LevKeywordsVO setLevKeywordsVO(String[] data);
	
	// Select All
	public String selectAllLevKeywords(String action) throws JsonProcessingException;
	
	// Insert
	public int insertLevKeywords(String[] data, String currentUser);
	
	// Update
	public int updateLevKeywords(String[] data, String currentUser);
	
	// Delete
	public int deleteLevKeywords(String[] data, String currentUser);

}
