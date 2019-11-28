package com.bdp.adm.biz;

import com.bdp.adm.vo.CrawlKeywordsVO;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface CrawlKeywordsBiz {
	
	// Taxonomy Setting
	public CrawlKeywordsVO setCrawlKeywordsVO(String[] data);
	
	// Select All
	public String selectAllCrawlKeywords(String action) throws JsonProcessingException;
	
	// Select One
	public CrawlKeywordsVO selectOneCrawlKeywords(String DT_RowId, String columnName, String newValue);
	
	// Insert
	public int insertCrawlKeywords(String[] data, String currentUser);
	
	// Update
	public int updateCrawlKeywords(String[] data, String currentUser);
	
	// Delete
	public int deleteCrawlKeywords(String[] data, String currentUser);

}
