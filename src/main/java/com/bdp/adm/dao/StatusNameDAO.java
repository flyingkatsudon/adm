package com.bdp.adm.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.bdp.adm.vo.StatusNameVO;

@Repository
public interface StatusNameDAO {
	// Select All
	public List<StatusNameVO> selectAllStatusName(String action);

	// Insert
	public int insertStatusName(StatusNameVO statusNameVO);
	
	// Update
	public int updateStatusName(StatusNameVO statusNameVO);
	
	// Delete
	public int deleteStatusName(StatusNameVO statusNameVO);
}
