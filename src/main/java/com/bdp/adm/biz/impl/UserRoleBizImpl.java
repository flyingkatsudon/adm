package com.bdp.adm.biz.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.bdp.adm.biz.UserRoleBiz;
import com.bdp.adm.dao.UserRoleDAO;
import com.bdp.adm.vo.UserRoleVO;
import com.bdp.adm.vo.UserVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

//@Service
@Repository
public class UserRoleBizImpl implements UserRoleBiz {

	@Autowired
	private UserRoleDAO userRoleDAO;
	
	@Override
	public UserRoleVO setUserRoleVO(String[] data) {
		String regPtn4Column = "data\\[[a-zA-Z0-9_]*\\]\\[";
		String regPtn4NewValue = "data\\[[a-zA-Z0-9_\\]\\[]*=";
	
		UserRoleVO userRoleVO = new UserRoleVO();
		
		for(int i = 1; i < data.length; i++) {
			String columnName = data[i].split(regPtn4Column)[1].split("]")[0];
			
			if(data[i].split(regPtn4NewValue).length < 2) {
				continue;
			}
			
			if (columnName.equals("userRoleId")) {
				int userRoleId = Integer.parseInt(data[i].split(regPtn4NewValue)[1]);
				userRoleVO.setUserRoleId(userRoleId);
			}
			else if (columnName.equals("userId")) {
				String userId = data[i].split(regPtn4NewValue)[1];
				userRoleVO.setUserId(userId);
			}
			else if (columnName.equals("roleId")) {
				String roleId = data[i].split(regPtn4NewValue)[1];
				userRoleVO.setRoleId(roleId);
			}
			else if (columnName.equals("businessCode")) {
				String businessCode = data[i].split(regPtn4NewValue)[1];
				userRoleVO.setBusinessCode(businessCode);
			}
			else if (columnName.equals("delCd")) {
				String delCd = data[i].split(regPtn4NewValue)[1];
				if (delCd.equals("N")) delCd="";
				userRoleVO.setDelCd(delCd);
			}
			else if (columnName.equals("loadDate")) {
				String loadDate = data[i].split(regPtn4NewValue)[1];
				userRoleVO.setLoadDate(loadDate);
			}
			else if (columnName.equals("loadTime")) {
				String loadTime = data[i].split(regPtn4NewValue)[1];
				userRoleVO.setLoadTime(loadTime);
			}
			else if (columnName.equals("loadName")) {
				String loadName = data[i].split(regPtn4NewValue)[1];
				userRoleVO.setLoadName(loadName);
			}
			else if (columnName.equals("updateDate")) {
				String updateDate = data[i].split(regPtn4NewValue)[1];
				userRoleVO.setUpdateDate(updateDate);
			}
			else if (columnName.equals("updateTime")) {
				String updateTime = data[i].split(regPtn4NewValue)[1];
				userRoleVO.setUpdateTime(updateTime);
			}
			else if (columnName.equals("updateName")) {
				String updateName = data[i].split(regPtn4NewValue)[1];
				userRoleVO.setUpdateName(updateName);
			}	
		}

		return userRoleVO;
	}
	
	// 2. SelectAll
	@Override
	public String selectAllUserRole(String action) throws JsonProcessingException {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		UserVO curUser = (UserVO)attr.getRequest().getSession().getAttribute("user");
		if (curUser == null) curUser = new UserVO();
		String bCode = curUser.getBusinessCode();
		Map<String, String> param = new HashMap<String, String>();
		param.put("action", action);
		param.put("bCode", bCode);
		
		List<UserRoleVO> userRoleList = userRoleDAO.selectAllUserRole(param);
		List<UserRoleVO> emptyList = new ArrayList<UserRoleVO>();
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> userRoleData = new HashMap<String, Object>();
		
		userRoleData.put("data", userRoleList);
		userRoleData.put("options", emptyList);
		userRoleData.put("files", emptyList);
		String userRoleDataJSON="";
		
		userRoleDataJSON = mapper.writeValueAsString(userRoleData);
		
		return userRoleDataJSON;
	}
	
	// 3. Insert
	@Override
	public int insertUserRole(String[] data, String currentUser) {
		UserRoleVO userRoleVO = this.setUserRoleVO(data);
		userRoleVO.setLoadName(currentUser);
	
		return userRoleDAO.insertUserRole(userRoleVO);
	}
	
	// 4. Update
	@Override
	public int updateUserRole(String[] data, String currentUser) {
		UserRoleVO userRoleVO = new UserRoleVO();
		userRoleVO = this.setUserRoleVO(data);
		userRoleVO.setUpdateName(currentUser);
		
		return userRoleDAO.updateUserRole(userRoleVO);
	}
	
	// 5. Remove
	@Override
	public int deleteUserRole(String[] data, String currentUser) {
		UserRoleVO userRoleVO = this.setUserRoleVO(data);
		userRoleVO.setUpdateName(currentUser);
		
		return userRoleDAO.deleteUserRole(userRoleVO);
	}
	
	// 6. SelectOne
	@Override
	public UserRoleVO selectOneUserRole(String DT_RowId, String columnName, String newValue) {
		UserRoleVO userRoleVO = userRoleDAO.selectOneUserRole(DT_RowId);
		
		//수정된 행, 새로운 값을 다시 set해서 
		if (columnName.equals("userRoleId")) {
			userRoleVO.setUserId(newValue);
		}
		else if (columnName.equals("userId")) {
			userRoleVO.setUserId(newValue);
		}
		else if (columnName.equals("roleId")) {
			userRoleVO.setRoleId(newValue);
		}
		else if (columnName.equals("businessCode")) {
			userRoleVO.setBusinessCode(newValue);
		}
		else if (columnName.equals("delCd")) {
			userRoleVO.setDelCd(newValue);
		}
		else if (columnName.equals("loadDate")) {
			userRoleVO.setLoadDate(newValue);
		}
		else if (columnName.equals("loadTime")) {
			userRoleVO.setLoadTime(newValue);
		}
		else if (columnName.equals("loadName")) {
			userRoleVO.setLoadName(newValue);
		}
		else if (columnName.equals("updateDate")) {
			userRoleVO.setUpdateDate(newValue);
		}
		else if (columnName.equals("updateTime")) {
			userRoleVO.setUpdateTime(newValue);
		}
		else if (columnName.equals("updateName")) {
			userRoleVO.setUpdateName(newValue);
		}
	
		return userRoleVO;
	}
}
