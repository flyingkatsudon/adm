package com.bdp.adm.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.bdp.adm.vo.TrendKeywordsVO;

@Repository
public interface TrendKeywordsDAO {
	// Select All
	public List<TrendKeywordsVO> selectAllTrendKeywords(Map<String, String> param);
	
	// Select One
	public TrendKeywordsVO selectOneTrendKeywords(String DT_RowId);
	
	// Insert
	public int insertTrendKeywords(TrendKeywordsVO trendKeywordsVO);
	
	// Update
	public int updateTrendKeywords(TrendKeywordsVO trendKeywordsVO);

	// Delete
	public int deleteTrendKeywords(TrendKeywordsVO trendKeywordsVO);
}
