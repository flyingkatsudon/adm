package com.bdp.adm.biz;

import com.bdp.adm.vo.TrendKeywordsVO;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface TrendKeywordsBiz {
	
	// Taxonomy Setting
	public TrendKeywordsVO setTrendKeywordsVO(String[] data);
	
	// Select All
	public String selectAllTrendKeywords(String action) throws JsonProcessingException;
	
	// Select One
	public TrendKeywordsVO selectOneTrendKeywords(String DT_RowId, String columnName, String newValue);
	
	// Insert
	public int insertTrendKeywords(String[] data, String currentUser);
	
	// Update
	public int updateTrendKeywords(String[] data, String currentUser);
	
	// Delete
	public int deleteTrendKeywords(String[] data, String currentUser);

}
