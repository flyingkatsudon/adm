package com.bdp.adm.biz.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bdp.adm.biz.RoleBiz;
import com.bdp.adm.dao.RoleDAO;
import com.bdp.adm.vo.RoleVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

//@Service
@Repository
public class RoleBizImpl implements RoleBiz{

	@Autowired
	private RoleDAO roleDAO;
	
	@Override
	public RoleVO setRoleVO(String[] data) {
		String regPtn4Column = "data\\[[a-zA-Z0-9_]*\\]\\[";
		String regPtn4NewValue = "data\\[[a-zA-Z0-9_\\]\\[]*=";
	
		RoleVO roleVO = new RoleVO();
		
		for(int i = 1; i < data.length; i++) {
			String columnName = data[i].split(regPtn4Column)[1].split("]")[0];
			
			if(data[i].split(regPtn4NewValue).length < 2) {
				continue;
			}
			
			if (columnName.equals("roleId")) {
				String roleId = data[i].split(regPtn4NewValue)[1];
				roleVO.setRoleId(roleId);
			}
			else if (columnName.equals("roleName")) {
				String roleName = data[i].split(regPtn4NewValue)[1];
				roleVO.setRoleName(roleName);
			}
			else if (columnName.equals("userId")) {
				String userId = data[i].split(regPtn4NewValue)[1];
				userId = StringUtils.leftPad(userId, 10, "0");
				roleVO.setUserId(userId);	
			}
			else if (columnName.equals("delCd")) {
				String delCd = data[i].split(regPtn4NewValue)[1];
				if (delCd.equals("N")) delCd="";
				roleVO.setDelCd(delCd);
			}
			else if (columnName.equals("loadDate")) {
				String loadDate = data[i].split(regPtn4NewValue)[1];
				roleVO.setLoadDate(loadDate);
			}
			else if (columnName.equals("loadTime")) {
				String loadTime = data[i].split(regPtn4NewValue)[1];
				roleVO.setLoadTime(loadTime);
			}
			else if (columnName.equals("loadName")) {
				String loadName = data[i].split(regPtn4NewValue)[1];
				roleVO.setLoadName(loadName);
			}
			else if (columnName.equals("updateDate")) {
				String updateDate = data[i].split(regPtn4NewValue)[1];
				roleVO.setUpdateDate(updateDate);
			}
			else if (columnName.equals("updateTime")) {
				String updateTime = data[i].split(regPtn4NewValue)[1];
				roleVO.setUpdateTime(updateTime);
			}
			else if (columnName.equals("updateName")) {
				String updateName = data[i].split(regPtn4NewValue)[1];
				roleVO.setUpdateName(updateName);
			}

		}

		return roleVO;
	}
	
	// 2. SelectAll
	@Override
	public String selectAllRole(String action) throws JsonProcessingException {
		List<RoleVO> roleList = roleDAO.selectAllRole(action);
		List<RoleVO> emptyList = new ArrayList<RoleVO>();
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> roleData = new HashMap<String, Object>();
		
		roleData.put("data", roleList);
		roleData.put("options", emptyList);
		roleData.put("files", emptyList);
		String roleDataJSON="";
		
		roleDataJSON = mapper.writeValueAsString(roleData);
		
		return roleDataJSON;
	}
	
	// 3. Insert
	@Override
	public int insertRole(String[] data, String currentUser) {
		RoleVO roleVO = this.setRoleVO(data);
		roleVO.setLoadName(currentUser);
		
		
		return roleDAO.insertRole(roleVO);
	}
	
	// 4. Update
	@Override
	public int updateRole(String[] data, String currentUser) {
		RoleVO roleVO = new RoleVO();
		roleVO = this.setRoleVO(data);
		roleVO.setUpdateName(currentUser);
		
		return roleDAO.updateRole(roleVO);
	}
	
	// 5. Remove
	@Override
	public int deleteRole(String[] data, String currentUser) {
		RoleVO roleVO = this.setRoleVO(data);
		roleVO.setUpdateName(currentUser);
		
		return roleDAO.deleteRole(roleVO);
	}
	
	// 6. SelectOne
	@Override
	public RoleVO selectOneRole(String DT_RowId, String columnName, String newValue) {
		
		RoleVO roleVO = roleDAO.selectOneRole(DT_RowId);
		
		if (columnName.equals("roleId")) {
			roleVO.setRoleId(newValue);
		}
		else if (columnName.equals("roleName")) {
			roleVO.setRoleName(newValue);
		}
		else if (columnName.equals("userId")) {
			newValue = StringUtils.leftPad(newValue, 10, "0");
			roleVO.setUserId(newValue);
		}
		else if (columnName.equals("delCd")) {
			roleVO.setDelCd(newValue);
		}
		else if (columnName.equals("loadDate")) {
			roleVO.setLoadDate(newValue);
		}
		else if (columnName.equals("loadTime")) {
			roleVO.setLoadTime(newValue);
		}
		else if (columnName.equals("loadName")) {
			roleVO.setLoadName(newValue);
		}
		else if (columnName.equals("updateDate")) {
			roleVO.setUpdateDate(newValue);
		}
		else if (columnName.equals("updateTime")) {
			roleVO.setUpdateTime(newValue);
		}
		else if (columnName.equals("updateName")) {
			roleVO.setUpdateName(newValue);
		}
		
		return roleVO;
	}
}
