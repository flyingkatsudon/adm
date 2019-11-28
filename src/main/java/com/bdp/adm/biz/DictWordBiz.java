package com.bdp.adm.biz;

import com.bdp.adm.vo.DictWordVO;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface DictWordBiz {
	
	public DictWordVO setDictWordVO(String[] data);
	
	// Select All
	public String selectAllDictWord(String action) throws JsonProcessingException;
	
	// Select One
	public DictWordVO selectOneDictWord(String DT_RowId, String columnName, String newValue);
	
	// Insert
	public int insertDictWord(String[] data, String currentUser);
	
	// Update
	public int updateDictWord(String[] data, String currentUser);
	
	// Delete
	public int deleteDictWord(String[] data, String currentUser);

}
