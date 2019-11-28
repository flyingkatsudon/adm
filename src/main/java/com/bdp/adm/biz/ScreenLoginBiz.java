package com.bdp.adm.biz;

import com.bdp.adm.vo.ScreenLoginVO;
import com.bdp.adm.vo.TitleNameVO;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface ScreenLoginBiz {
	
	public ScreenLoginVO setScreenLoginVO(String[] data);
	
	// Select All
	public String selectAllScreenLogin() throws JsonProcessingException;
	
	// Select One
	public ScreenLoginVO selectOneScreenLogin(String DT_RowId, String columnName, String newValue);
	
	// Insert
	public int insertScreenLogin(String[] data, String currentUser);
	
	// Update
	public int updateScreenLogin(String[] data, String currentUser);
	
	// Delete
	public int deleteScreenLogin(String[] data, String currentUser);

}
