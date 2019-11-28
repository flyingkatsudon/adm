package com.bdp.adm.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.bdp.adm.vo.ScreenLoginVO;

@Repository
public interface ScreenLoginDAO {
	// Select All
	public List<ScreenLoginVO> selectAllScreenLogin();
	
	// Select One
	public ScreenLoginVO selectOneScreenLogin(String DT_RowId);
	
	// Insert
	public int insertScreenLogin(ScreenLoginVO screenLoginVO);
	
	// Update
	public int updateScreenLogin(ScreenLoginVO screenLoginVO);
	
	// Delete
	public int deleteScreenLogin(ScreenLoginVO screenLoginVO);
}
