package com.bdp.adm.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.bdp.adm.vo.LevKeywordsVO;

@Repository
public interface LevKeywordsDAO {
	// Select All
	public List<LevKeywordsVO> selectAllLevKeywords(String action);
	
	// Insert
	public int insertLevKeywords(LevKeywordsVO levKeywordsVO);
	
	// Update
	public int updateLevKeywords(LevKeywordsVO levKeywordsVO);

	// Delete
	public int deleteLevKeywords(LevKeywordsVO levKeywordsVO);
}
