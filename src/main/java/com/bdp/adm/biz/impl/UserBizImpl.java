package com.bdp.adm.biz.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.bdp.adm.biz.UserBiz;
import com.bdp.adm.dao.UserDAO;
import com.bdp.adm.util.CheckPwd;
import com.bdp.adm.vo.UserMetaVO;
import com.bdp.adm.vo.UserVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;

@Repository
public class UserBizImpl implements UserBiz {

	@Autowired
	private UserDAO userDAO;

	@Override
	public UserVO setUserVO(String[] data) {
		String regPtn4Column = "data\\[[a-zA-Z0-9_]*\\]\\[";
		String regPtn4NewValue = "data\\[[a-zA-Z0-9_\\]\\[]*=";
	
		UserVO userVO = new UserVO();

		for(int i = 1; i < data.length; i++) {
			String columnName = data[i].split(regPtn4Column)[1].split("]")[0];
			
			if(data[i].split(regPtn4NewValue).length < 2) {
				continue;
			}
			
			if (columnName.equals("userId")) {
				String userId = data[i].split(regPtn4NewValue)[1];
				userVO.setUserId(userId);
			}
			if (columnName.equals("businessCode")) {
				String businessCode = data[i].split(regPtn4NewValue)[1];
				userVO.setBusinessCode(businessCode);
			}
			else if (columnName.equals("loginName")) {
				String loginName = data[i].split(regPtn4NewValue)[1];
				userVO.setLoginName(loginName);	
			}
			else if (columnName.equals("username")) {
				String userName = data[i].split(regPtn4NewValue)[1];
				userVO.setUsername(userName);
			}
			else if (columnName.equals("userPassword")) {
				String userPassword = data[i].split(regPtn4NewValue)[1].trim();
				userPassword = Hashing.md5().newHasher().putString(userPassword, Charsets.UTF_8).hash().toString();
				userVO.setUserPassword(userPassword.toUpperCase());
			}
			else if (columnName.equals("userTitleCd")) {
				String userTitleCd = data[i].split(regPtn4NewValue)[1];
				userVO.setUserTitleCd(userTitleCd);
			}
			else if (columnName.equals("userDeptCd")) {
				String UserDeptCd = data[i].split(regPtn4NewValue)[1];
				userVO.setUserDeptCd(UserDeptCd);
			}
			else if (columnName.equals("userStatus")) {
				String userStatus = data[i].split(regPtn4NewValue)[1];
				userVO.setUserStatus(userStatus);
			}
			else if (columnName.equals("delCd")) {
				String delCd = data[i].split(regPtn4NewValue)[1];
				if (delCd.equals("N")) delCd="";
				userVO.setDelCd(delCd);
			}
			else if (columnName.equals("userLastDate")) {
				String UserLastDate = data[i].split(regPtn4NewValue)[1];
				userVO.setUserLastDate(UserLastDate);		
			}
			else if (columnName.equals("userStartDate")) {
				String UserStartDate = data[i].split(regPtn4NewValue)[1];
				userVO.setUserStartDate(UserStartDate);
			}
			else if (columnName.equals("userEndDate")) {
				String UserEndDate = data[i].split(regPtn4NewValue)[1];
				userVO.setUserEndDate(UserEndDate);
			}
			else if (columnName.equals("rbacPolicy")) {
				int rbacPolicy = Integer.parseInt(data[i].split(regPtn4NewValue)[1]);				
				userVO.setRbacPolicy(rbacPolicy);
			}
			else if (columnName.equals("userStateInfo")) {
				String userStateInfo = data[i].split(regPtn4NewValue)[1];
				userVO.setUserStateInfo(userStateInfo);
			}
			else if (columnName.equals("loadDate")) {
				String loadDate = data[i].split(regPtn4NewValue)[1];
				userVO.setLoadDate(loadDate);
			}
			else if (columnName.equals("loadTime")) {
				String loadTime = data[i].split(regPtn4NewValue)[1];
				userVO.setLoadTime(loadTime);
			}
			else if (columnName.equals("loadName")) {
				String loadName = data[i].split(regPtn4NewValue)[1];
				userVO.setLoadName(loadName);
			}
			else if (columnName.equals("updateDate")) {
				String updateDate = data[i].split(regPtn4NewValue)[1];
				userVO.setUpdateDate(updateDate);
			}
			else if (columnName.equals("updateTime")) {
				String updateTime = data[i].split(regPtn4NewValue)[1];
				userVO.setUpdateTime(updateTime);
			}
			else if (columnName.equals("updateName")) {
				String updateName = data[i].split(regPtn4NewValue)[1];
				userVO.setUpdateName(updateName);
			}
			
		}

		return userVO;
	}
	
	// 2. SelectAll
	@Override
	public String selectAllUser(String action) throws JsonProcessingException {
		List<UserVO> emptyList = new ArrayList<UserVO>();
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> userData = new HashMap<String, Object>();
		Map<String, Object> tmpData  = null;
		
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		UserVO curUser = (UserVO)attr.getRequest().getSession().getAttribute("user");
		if (curUser == null) curUser = new UserVO();
		String bCode = curUser.getBusinessCode();
		Map<String, String> param = new HashMap<String, String>();
		param.put("action", action);
		param.put("bCode", bCode);
		
		List<UserVO> userList = userDAO.selectAllUser(param);
		List<Object> newList  = new ArrayList<Object>();
		
		for (UserVO t : userList) {
			tmpData = new HashMap<String, Object>();
			tmpData.put("DT_RowId", t.getDTRowId());
			tmpData.put("userId",  t.getUserId());
			tmpData.put("businessCode",  t.getBusinessCode());
			tmpData.put("username",  t.getUsername());
			tmpData.put("userTitleCd",  t.getUserTitleCd());
			tmpData.put("userDeptCd",  t.getUserDeptCd());
			tmpData.put("userStatus",  t.getUserStatus());
			tmpData.put("userStartDate",  t.getUserStartDate());
			tmpData.put("userLastDate",  t.getUserLastDate());
			tmpData.put("userEndDate",  t.getUserEndDate());	
			tmpData.put("rbacPolicy",  t.getRbacPolicy());
			tmpData.put("userStateInfo",  t.getUserStateInfo());
			tmpData.put("loadDate",  t.getLoadDate());
			tmpData.put("loadTime",  t.getLoadTime());
			tmpData.put("loadName",  t.getLoadName());
			tmpData.put("updateDate",  t.getUpdateDate());
			tmpData.put("updateTime",  t.getUpdateTime());
			tmpData.put("updateName",  t.getUpdateName());
			tmpData.put("delCd",  t.getDelCd());
			tmpData.put("userDeptName", t.getUserDeptName());
			tmpData.put("userTitleName", t.getUserTitleName());
			tmpData.put("userStatusName", t.getUserStatusName());
			
			newList.add(tmpData);
		}
		
		userData.put("data", newList);
		userData.put("options", emptyList);
		userData.put("files", emptyList);
		String userDataJSON = "";
		
		userDataJSON = mapper.writeValueAsString(userData);

		return userDataJSON;
	}
	
	// 3. Insert or Create
	@Override
	public int insertUser(String[] data, String currentUser) {
		UserVO userVO = this.setUserVO(data);
		userVO.setLoadName(currentUser);
		userVO.setUpdateName(currentUser);
		
		String defPwd  = "init12345";
	    String convPwd = CheckPwd.passwordEncoder.encodePassword(defPwd, "").toUpperCase();
	    userVO.setUserPassword(convPwd);
		
		return userDAO.insertUser(userVO);
	}
	
	// 4. Update
	@Override
	public int updateUser(String[] data, String currentUser) {
		UserVO userVO = new UserVO();
		userVO = this.setUserVO(data);
		userVO.setUpdateName(currentUser);

		return userDAO.updateUser(userVO);
	}
	
	// 5. Remove
	@Override
	public int deleteUser(String[] data, String currentUser) {
		UserVO userVO = this.setUserVO(data);
		userVO.setUpdateName(currentUser);
		
		return userDAO.deleteUser(userVO);
	}
	
	// 5. InitUser
	@Override
	public int initUserStat(String[] data, String currentUser) {
		UserVO userVO = this.setUserVO(data);
		userVO.setUpdateName(currentUser);
		
		String defPwd  = "init12345";
	    String convPwd = CheckPwd.passwordEncoder.encodePassword(defPwd, "").toUpperCase();
	    userVO.setUserPassword(convPwd);
		
		return userDAO.initUserStat(userVO);
	}
	
	
	// 6. SelectOne
	@Override
	public UserVO selectOneUser(String DT_RowId, String columnName, String newValue) {
		
		UserVO userVO = userDAO.selectOneUser(DT_RowId);
		
		if (columnName.equals("userId")) {
			newValue = StringUtils.leftPad(newValue, 10, "0");
			userVO.setUserId(newValue);
		}
		else if (columnName.equals("businessCode")) {
			userVO.setBusinessCode(newValue);
		}
		else if (columnName.equals("loginName")) {
			userVO.setLoginName(newValue);
		}
		else if (columnName.equals("username")) {
			userVO.setUsername(newValue);
		}
		else if (columnName.equals("userPassword")) {
			newValue = Hashing.md5().newHasher().putString(newValue.trim(), Charsets.UTF_8).hash().toString();
			userVO.setUserPassword(newValue.toUpperCase());
		}
		else if (columnName.equals("userTitleCd")) {
			userVO.setUserTitleCd(newValue);
		}
		else if (columnName.equals("userDeptCd")) {
			userVO.setUserDeptCd(newValue);
		}
		else if (columnName.equals("userStatus")) {
			userVO.setUserStatus(newValue);
		}
		else if (columnName.equals("delCd")) {
			userVO.setDelCd(newValue);
		}
		else if (columnName.equals("userLastDate")) {
			userVO.setUserLastDate(newValue);
		}
		else if (columnName.equals("userStartDate")) {
			userVO.setUserStartDate(newValue);
		}
		else if (columnName.equals("userEndDate")) {
			userVO.setUserEndDate(newValue);
		}
		else if (columnName.equals("rbacPolicy")) {
			userVO.setRbacPolicy(Integer.parseInt(newValue));
		}
		else if (columnName.equals("userStateInfo")) {
			userVO.setUserStateInfo(newValue);
		}
		else if (columnName.equals("loadDate")) {
			userVO.setLoadDate(newValue);
		}
		else if (columnName.equals("loadTime")) {
			userVO.setLoadTime(newValue);
		}
		else if (columnName.equals("loadName")) {
			userVO.setLoadName(newValue);
		}
		else if (columnName.equals("updateDate")) {
			userVO.setUpdateDate(newValue);
		}
		else if (columnName.equals("updateTime")) {
			userVO.setUpdateTime(newValue);
		}
		else if (columnName.equals("updateName")) {
			userVO.setUpdateName(newValue);
		}

		return userVO;
	}

	// security
	@Override
	public void updatePwd(UserVO param) {
		userDAO.updatePwd(param);
	}

	@Override
	public void updateLoginCnt(UserVO param) {
		userDAO.updateLoginCnt(param);
	}
	
	//@Override
	public UserVO loadUserByUsername(UserVO param) throws UsernameNotFoundException {
		UserVO user = new UserVO();

		try {
			user = userDAO.getUser(param);
			if (user != null) user.setScreenList(userDAO.getScreenList(user.getRoleId()));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		return user;
	}

	@Override
	public Map<String, Object> getUserMeta() {
		List<UserMetaVO> dept = userDAO.selectUserDept();
		List<UserMetaVO> title = userDAO.selectUserTitle();
		List<UserMetaVO> status = userDAO.selectUserStatus();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dept", dept);
		map.put("title", title);
		map.put("status", status);
		
		return map;
	}
}
