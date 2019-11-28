package com.bdp.adm.dao;

import java.util.ArrayList;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.bdp.adm.vo.UserMetaVO;
import com.bdp.adm.vo.UserVO;

@Repository
public interface UserDAO {
	
	// Select All
	public ArrayList<UserVO> selectAllUser(Map<String, String> param);
	
	// Select One
	public UserVO selectOneUser(String DT_RowId);
	
	// Insert
	public int insertUser(UserVO userVO);
	
	// Update
	public int updateUser(UserVO userVO);
	
	// Delete
	public int deleteUser(UserVO userVO);
	
	// InitUserStat
	public int initUserStat(UserVO userVO);

	// security
	public UserVO getUser(@Param("param") UserVO param);
	
	public ArrayList<Object> getScreenList(String roleId);
	
	public void updateLoginCnt(@Param("param") UserVO param);

	public void updatePwd(@Param("param") UserVO param);
	
	public ArrayList<UserMetaVO> selectUserDept();

	public ArrayList<UserMetaVO> selectUserTitle();

	public ArrayList<UserMetaVO> selectUserStatus();
}
