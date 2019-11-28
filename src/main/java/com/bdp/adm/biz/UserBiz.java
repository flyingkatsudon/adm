package com.bdp.adm.biz;

import java.util.List;
import java.util.Map;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.bdp.adm.vo.UserMetaVO;
import com.bdp.adm.vo.UserVO;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface UserBiz {//extends UserDetailsService {
	
	// UserVO Setting
	public UserVO setUserVO(String[] data);
	
	// Select All
	public String selectAllUser(String action) throws JsonProcessingException;
	
	// Select One
	public UserVO selectOneUser(String DT_RowId, String columnName, String newValue);
	
	// Insert
	public int insertUser(String[] data, String currentUser);
	
	// Update
	public int updateUser(String[] data, String currentUser);
	
	// Delete
	public int deleteUser(String[] data, String currentUser);
	
	// Init UserStatus
	public int initUserStat(String[] data, String currentUser);
	
	// getUserMeta
	public Map<String, Object> getUserMeta();
	
	// security
	public void updatePwd(UserVO param); // 초기비밀번호 수정
	
	public void updateLoginCnt(UserVO param); // 로그인 카운트
	
	//@Override
	public UserVO loadUserByUsername(UserVO param) throws UsernameNotFoundException;
}
