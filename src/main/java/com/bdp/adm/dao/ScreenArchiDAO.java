package com.bdp.adm.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.bdp.adm.vo.ScreenArchiVO;

@Repository
public interface ScreenArchiDAO {
	
	// Select All
	public List<ScreenArchiVO> selectAllScreenArchi(String action);
	
	// Insert
	public int insertScreenArchi(ScreenArchiVO screenArchiVO);
	
	// Update
	public int updateScreenArchi(ScreenArchiVO screenArchiVO);
	
	// Delete
	public int deleteScreenArchi(ScreenArchiVO screenArchiVO);
}
