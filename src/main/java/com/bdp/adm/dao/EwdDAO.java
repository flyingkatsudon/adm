package com.bdp.adm.dao;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.bdp.adm.vo.EwdVO;

@Repository
public interface EwdDAO {
	// Select All
	public List<EwdVO> selectAllEwd(Map<String, String> param);
	
	// Select One
	public EwdVO selectOneEwd(String DT_RowId);
	
	// Insert
	public int insertEwd(EwdVO ewdVO);
	
	// Update
	public int updateEwd(EwdVO ewdVO);
	
	// Delete
	public int deleteEwd(EwdVO ewdVO);
	
}
