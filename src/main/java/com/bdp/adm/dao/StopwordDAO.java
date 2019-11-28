package com.bdp.adm.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.bdp.adm.vo.StopwordVO;

@Repository
public interface StopwordDAO {
	// Select All
	public List<StopwordVO> selectAllStopword(Map<String, String> param);
	
	// Select One
	public StopwordVO selectOneStopword(String DT_RowId);
	
	// Insert
	public int insertStopword(StopwordVO stopwordVO);
	
	// Update
	public int updateStopword(StopwordVO stopwordVO);

	// Delete
	public int deleteStopword(StopwordVO stopwordVO);
}
