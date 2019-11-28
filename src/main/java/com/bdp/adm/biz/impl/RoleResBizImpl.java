package com.bdp.adm.biz.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.bdp.adm.biz.RoleResBiz;
import com.bdp.adm.dao.RoleResDAO;
import com.bdp.adm.vo.RoleResMetaVO;
import com.bdp.adm.vo.RoleResVO;
import com.bdp.adm.vo.UserVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Repository
public class RoleResBizImpl implements RoleResBiz {

	@Autowired
	private RoleResDAO roleResDAO;
	
	@Override
	public RoleResVO setRoleResVO(String[] data) {
		String regPtn4Column = "data\\[[a-zA-Z0-9_]*\\]\\[";
		String regPtn4NewValue = "data\\[[a-zA-Z0-9_\\]\\[]*=";
	
		RoleResVO roleResVO = new RoleResVO();
		
		for(int i = 1; i < data.length; i++) {
			String columnName = data[i].split(regPtn4Column)[1].split("]")[0];
			
			if(data[i].split(regPtn4NewValue).length < 2) {
				continue;
			}
			
			if (columnName.equals("roleResId")) {
				int roleResId = Integer.parseInt(data[i].split(regPtn4NewValue)[1]);
				roleResVO.setRoleResId(roleResId);
			}
			else if (columnName.equals("roleId")) {
				String roleId = data[i].split(regPtn4NewValue)[1];
				roleResVO.setRoleId(roleId);
			}
			else if (columnName.equals("resType")) {
				String resType = data[i].split(regPtn4NewValue)[1];
				roleResVO.setResType(resType);	
			}
			else if (columnName.equals("resId")) {
				int resId = Integer.parseInt(data[i].split(regPtn4NewValue)[1]);
				roleResVO.setResId(resId);
			}
			else if (columnName.equals("permission")) {
				String permission = data[i].split(regPtn4NewValue)[1];
				roleResVO.setPermission(permission);
			}
			else if (columnName.equals("auRbacText")) {
				String auRbacText = data[i].split(regPtn4NewValue)[1];
				roleResVO.setAuRbacText(auRbacText);
			}
			else if (columnName.equals("delCd")) {
				String delCd = data[i].split(regPtn4NewValue)[1];
				if (delCd.equals("N")) delCd="";
				roleResVO.setDelCd(delCd);
			}
			else if (columnName.equals("loadDate")) {
				String loadDate = data[i].split(regPtn4NewValue)[1];
				roleResVO.setLoadDate(loadDate);
			}
			else if (columnName.equals("loadTime")) {
				String loadTime = data[i].split(regPtn4NewValue)[1];
				roleResVO.setLoadTime(loadTime);
			}
			else if (columnName.equals("loadName")) {
				String loadName = data[i].split(regPtn4NewValue)[1];
				roleResVO.setLoadName(loadName);
			}
			else if (columnName.equals("updateDate")) {
				String updateDate = data[i].split(regPtn4NewValue)[1];
				roleResVO.setUpdateDate(updateDate);
			}
			else if (columnName.equals("updateTime")) {
				String updateTime = data[i].split(regPtn4NewValue)[1];
				roleResVO.setUpdateTime(updateTime);
			}
			else if (columnName.equals("updateName")) {
				String updateName = data[i].split(regPtn4NewValue)[1];
				roleResVO.setUpdateName(updateName);
			}
		
		}

		return roleResVO;
	}
	
	// 2. SelectAll
	@Override
	public String selectAllRoleRes(String action) throws JsonProcessingException {
		List<RoleResVO> roleResList = roleResDAO.selectAllRoleRes(action);
		List<RoleResVO> emptyList = new ArrayList<RoleResVO>();
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> roleResData = new HashMap<String, Object>();
		
		roleResData.put("data", roleResList);
		roleResData.put("options", emptyList);
		roleResData.put("files", emptyList);
		String roleResDataJSON="";
		
		roleResDataJSON = mapper.writeValueAsString(roleResData);
		
		return roleResDataJSON;
	}
	
	// 3. Insert
	@Override
	public int insertRoleRes(String[] data, String currentUser) {
		RoleResVO roleResVO = this.setRoleResVO(data);
		roleResVO.setLoadName(currentUser);
		
		return roleResDAO.insertRoleRes(roleResVO);
	}
	
	// 4. Update
	@Override
	public int updateRoleRes(String[] data, String currentUser) {
		RoleResVO roleResVO = new RoleResVO();
		roleResVO = this.setRoleResVO(data);
		roleResVO.setUpdateName(currentUser);
		
		if (roleResVO.getAuRbacText() == null) {
			roleResVO.setAuRbacText(" ");
		}
		return roleResDAO.updateRoleRes(roleResVO);
	}
	
	// 5. Remove
	@Override
	public int deleteRoleRes(String[] data, String currentUser) {
		RoleResVO roleResVO = this.setRoleResVO(data);
		roleResVO.setUpdateName(currentUser);
		
		return roleResDAO.deleteRoleRes(roleResVO);
	}
	
	// 6. SelectOne
	@Override
	public RoleResVO selectOneRoleRes(String DT_RowId, String columnName, String newValue) {
		RoleResVO roleResVO = roleResDAO.selectOneRoleRes(DT_RowId);
		
		if (columnName.equals("roleResId")) {
			roleResVO.setRoleResId(Integer.parseInt(newValue));
		}
		else if (columnName.equals("roleId")) {
			roleResVO.setRoleId(newValue);
		}
		else if (columnName.equals("resType")) {
			roleResVO.setResType(newValue);
		}
		else if (columnName.equals("resId")) {
			roleResVO.setResId(Integer.parseInt(newValue));
		}
		else if (columnName.equals("permission")) {
			roleResVO.setPermission(newValue);
		}
		else if (columnName.equals("auRbacText")) {
			roleResVO.setAuRbacText(newValue);
		}
		else if (columnName.equals("delCd")) {
			roleResVO.setDelCd(newValue);
		}
		else if (columnName.equals("loadDate")) {
			roleResVO.setLoadDate(newValue);
		}
		else if (columnName.equals("loadTime")) {
			roleResVO.setLoadTime(newValue);
		}
		else if (columnName.equals("loadName")) {
			roleResVO.setLoadName(newValue);
		}
		else if (columnName.equals("updateDate")) {
			roleResVO.setUpdateDate(newValue);
		}
		else if (columnName.equals("updateTime")) {
			roleResVO.setUpdateTime(newValue);
		}
		else if (columnName.equals("updateName")) {
			roleResVO.setUpdateName(newValue);
		}
		
		System.out.println(roleResVO);
		return roleResVO;
	}
	
	@Override
	public Map<String, Object> getRoleResMeta() {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		UserVO curUser = (UserVO)attr.getRequest().getSession().getAttribute("user");
		if (curUser == null) curUser = new UserVO();
		String bCode = curUser.getBusinessCode();
	
		List<RoleResMetaVO> role = roleResDAO.selectRoleName();
		List<RoleResMetaVO> resType = roleResDAO.selectResTypeName();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("role", role);
		map.put("resType", resType);
		map.put("bCode", bCode);
		
		return map;
	}
}
