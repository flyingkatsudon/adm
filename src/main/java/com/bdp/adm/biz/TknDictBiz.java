package com.bdp.adm.biz;

import com.bdp.adm.vo.TknDictVO;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface TknDictBiz {
	// Data --> TknDictVO
	public TknDictVO setTknDictVO(String[] data);
	
	// Select All
	public String selectAllTknDict() throws JsonProcessingException;
	
	// Select One
	public TknDictVO  selectOneTknDict(String DT_RowId, String columnName, String newValue);
	
	// Insert
	public int insertTknDict(String[] data);
	
	// Update
	public int updataeTknDict(String[] data);
	
	// Delete
	public int deleteTknDict(String[] data);
}
