package com.bdp.adm.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.bdp.adm.vo.DocFilterVO;

@Repository
public interface DocFilterDAO {
	// Select All
	public List<DocFilterVO> selectAllDocFilter(Map<String, String> param);
	
	// Select One
	public DocFilterVO selectOneDocFilter(String DT_RowId);
	
	// Insert
	public int insertDocFilter(DocFilterVO docFilterVO);
	
	// Update
	public int updateDocFilter(DocFilterVO docFilterVO);
	
	// Delete
	public int deleteDocFilter(DocFilterVO docFilterVO);
	
}
