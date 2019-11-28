package com.bdp.adm.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.bdp.adm.vo.CrawlKeywordsVO;

@Repository
public interface CrawlKeywordsDAO {
	// Select All
	public List<CrawlKeywordsVO> selectAllCrawlKeywords(Map<String, String> param);
	
	// Select One
	public CrawlKeywordsVO selectOneCrawlKeywords(String DT_RowId);
	
	// Insert
	public int insertCrawlKeywords(CrawlKeywordsVO crawlKeywordsVO);
	
	// Update
	public int updateCrawlKeywords(CrawlKeywordsVO crawlKeywordsVO);

	// Delete
	public int deleteCrawlKeywords(CrawlKeywordsVO crawlKeywordsVO);
}
