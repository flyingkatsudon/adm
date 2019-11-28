package com.bdp.adm.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.bdp.adm.vo.ScreenMasterVO;

@Repository
public interface ScreenMasterDAO {
	// Select All
	public List<ScreenMasterVO> selectAllScreenMaster(Map<String, String> param);
	
	// Select One
	public ScreenMasterVO selectOneScreenMaster(String DT_RowId);
	
	// Insert
	public int insertScreenMaster(ScreenMasterVO screenMasterVO);
	
	// Update
	public int updateScreenMaster(ScreenMasterVO screenMasterVO);
	
	// Delete
	public int deleteScreenMaster(ScreenMasterVO screenMasterVO);
}
