package com.bdp.adm.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.bdp.adm.vo.DictWordVO;

@Repository
public interface DictWordDAO {
	// Select All
	public List<DictWordVO> selectAllDictWord(Map<String, String> param);
	
	// Select One
	public DictWordVO selectOneDictWord(String DT_RowId);
	
	// Insert
	public int insertDictWord(DictWordVO dictWordVO);
	
	// Update
	public int updateDictWord(DictWordVO dictWordVO);

	// Delete
	public int deleteDictWord(DictWordVO dictWordVO);
}
