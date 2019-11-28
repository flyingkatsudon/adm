package com.bdp.adm.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.bdp.adm.vo.RoleVO;
import com.bdp.adm.vo.ScreenLinkVO;

@Repository
public interface ScreenLinkDAO {
	
	// Select All
	public List<ScreenLinkVO> selectAllScreenLink(String action);
	
	// Insert
	public int insertScreenLink(ScreenLinkVO screenLinkVO);
	
	// Update
	public int updateScreenLink(ScreenLinkVO screenLinkVO);
	
	// Delete
	public int deleteScreenLink(ScreenLinkVO screenLinkVO);
	
	// Select One
	public ScreenLinkVO selectOneScreenLink(String DT_RowId);
}
