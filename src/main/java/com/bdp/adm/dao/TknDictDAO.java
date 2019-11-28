package com.bdp.adm.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.bdp.adm.vo.TknDictVO;

@Repository
public interface TknDictDAO {
	// Select All
	public List<TknDictVO> selectAllTknDict();
	
	// Select One
	public TknDictVO selectOneTknDict(String DT_RowId);
	
	// Insert
	public int insertTknDict(TknDictVO tknDictVO);
	
	// Update
	public int updateTknDict(TknDictVO tknDictVO);
	
	// Delete
	public int deleteTknDict(TknDictVO tknDictVO);

}
