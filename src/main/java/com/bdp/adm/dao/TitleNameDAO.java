package com.bdp.adm.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.bdp.adm.vo.ScreenMasterVO;
import com.bdp.adm.vo.TitleNameVO;

@Repository
public interface TitleNameDAO {
	// Select All
	public List<TitleNameVO> selectAllTitleName(String action);
	
	// Select One
	public TitleNameVO selectOneTitleName(String DT_RowId);
	
	// Insert
	public int insertTitleName(TitleNameVO titleNameVO);
	
	// Update
	public int updateTitleName(TitleNameVO titleNameVO);
	
	// Delete
	public int deleteTitleName(TitleNameVO titleNameVO);
}
