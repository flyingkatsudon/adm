package com.bdp.adm.biz.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.bdp.adm.biz.ScreenMasterBiz;
import com.bdp.adm.dao.ScreenMasterDAO;
import com.bdp.adm.vo.ScreenMasterVO;
import com.bdp.adm.vo.UserVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

//@Service
@Repository
public class ScreenMasterBizImpl implements ScreenMasterBiz {

	@Autowired
	private ScreenMasterDAO screenMasterDAO;
	
	@Override
	public ScreenMasterVO setScreenMasterVO(String[] data) {
		String regPtn4Column = "data\\[[a-zA-Z0-9_]*\\]\\[";
		String regPtn4NewValue = "data\\[[a-zA-Z0-9_\\]\\[]*=";
	
		ScreenMasterVO screenMasterVO = new ScreenMasterVO();
		
		for(int i = 1; i < data.length; i++) {
			String columnName = data[i].split(regPtn4Column)[1].split("]")[0];
			
			if(data[i].split(regPtn4NewValue).length < 2) {
				continue;
			}
			
			if (columnName.equals("screenIdx")) {
				int screenIdx = Integer.parseInt(data[i].split(regPtn4NewValue)[1]);
				screenMasterVO.setScreenIdx(screenIdx);
			}
			else if (columnName.equals("screenNo")) {
				String screenNo = data[i].split(regPtn4NewValue)[1];
				screenMasterVO.setScreenNo(screenNo);
			}
			else if (columnName.equals("screenProgramId")) {
				String screenProgramId = data[i].split(regPtn4NewValue)[1];
				screenMasterVO.setScreenProgramId(screenProgramId);
			}
			else if (columnName.equals("screenLayoutTxt")) {
				String screenLayoutTxt = data[i].split(regPtn4NewValue)[1];
				screenMasterVO.setScreenLayoutTxt(screenLayoutTxt);
			}
			else if (columnName.equals("roleId")) {
				String roleId = data[i].split(regPtn4NewValue)[1];
				screenMasterVO.setRoleId(roleId);
			}
			else if (columnName.equals("userCompanyCd")) {
				String userCompanyCd = data[i].split(regPtn4NewValue)[1];
				screenMasterVO.setUserCompanyCd(userCompanyCd);
			}
			else if (columnName.equals("delCd")) {
				String delCd = data[i].split(regPtn4NewValue)[1];
				if (delCd.equals("N")) delCd="";
				screenMasterVO.setDelCd(delCd);
			}
			else if (columnName.equals("loadDate")) {
				String loadDate = data[i].split(regPtn4NewValue)[1];
				screenMasterVO.setLoadDate(loadDate);
			}
			else if (columnName.equals("loadTime")) {
				String loadTime = data[i].split(regPtn4NewValue)[1];
				screenMasterVO.setLoadTime(loadTime);
			}
			else if (columnName.equals("loadName")) {
				String loadName = data[i].split(regPtn4NewValue)[1];
				screenMasterVO.setLoadName(loadName);
			}
			else if (columnName.equals("updateDate")) {
				String updateDate = data[i].split(regPtn4NewValue)[1];
				screenMasterVO.setUpdateDate(updateDate);
			}
			else if (columnName.equals("updateTime")) {
				String updateTime = data[i].split(regPtn4NewValue)[1];
				screenMasterVO.setUpdateTime(updateTime);
			}
			else if (columnName.equals("updateName")) {
				String updateName = data[i].split(regPtn4NewValue)[1];
				screenMasterVO.setUpdateName(updateName);
			}
		}

		return screenMasterVO;
	}
	
	// 2. SelectAll
	@Override
	public String selectAllScreenMaster(String action) throws JsonProcessingException {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		UserVO curUser = (UserVO)attr.getRequest().getSession().getAttribute("user");
		if (curUser == null) curUser = new UserVO();
		String bCode = curUser.getBusinessCode();
		Map<String, String> param = new HashMap<String, String>();
		param.put("action", action);
		param.put("bCode", bCode);
		
		List<ScreenMasterVO> screenMasterList = screenMasterDAO.selectAllScreenMaster(param);
		List<ScreenMasterVO> emptyList = new ArrayList<ScreenMasterVO>();
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> screenMasterData = new HashMap<String, Object>();
		
		screenMasterData.put("data", screenMasterList);
		screenMasterData.put("options", emptyList);
		screenMasterData.put("files", emptyList);
		String screenMasterDataJSON="";
		
		screenMasterDataJSON = mapper.writeValueAsString(screenMasterData);
		
		return screenMasterDataJSON;
	}
	
	// 3. Insert
	@Override
	public int insertScreenMaster(String[] data, String currentUser) {
		ScreenMasterVO screenMasterVO = this.setScreenMasterVO(data);
		screenMasterVO.setLoadName(currentUser);
	
		return screenMasterDAO.insertScreenMaster(screenMasterVO);
	}
	
	// 4. Update
	@Override
	public int updateScreenMaster(String[] data, String currentUser) {
		ScreenMasterVO screenMasterVO = new ScreenMasterVO();
		screenMasterVO = this.setScreenMasterVO(data);
		screenMasterVO.setUpdateName(currentUser);
		
		return screenMasterDAO.updateScreenMaster(screenMasterVO);
	}
	
	// 5. Remove
	@Override
	public int deleteScreenMaster(String[] data, String currentUser) {
		ScreenMasterVO screenMasterVO = this.setScreenMasterVO(data);
		screenMasterVO.setUpdateName(currentUser);
		
		return screenMasterDAO.deleteScreenMaster(screenMasterVO);
	}
	
	// 6. SelectOne
	@Override
	public ScreenMasterVO selectOneScreenMaster(String DT_RowId, String columnName, String newValue) {	
		ScreenMasterVO screenMasterVO = screenMasterDAO.selectOneScreenMaster(DT_RowId);
		
		//수정된 행, 새로운 값을 다시 set해서 
		if (columnName.equals("screenNo")) {
			screenMasterVO.setScreenNo(newValue);
		}
		else if (columnName.equals("screenProgramId")) {
			screenMasterVO.setScreenProgramId(newValue);
		}
		else if (columnName.equals("screenLayoutTxt")) {
			screenMasterVO.setScreenLayoutTxt(newValue);
		}
		else if (columnName.equals("roleId")) {
			screenMasterVO.setRoleId(newValue);
		}
		else if (columnName.equals("userCompanyCd")) {
			screenMasterVO.setUserCompanyCd(newValue);
		}
		else if (columnName.equals("delCd")) {
			screenMasterVO.setDelCd(newValue);
		}
		else if (columnName.equals("loadDate")) {
			screenMasterVO.setLoadDate(newValue);
		}
		else if (columnName.equals("loadTime")) {
			screenMasterVO.setLoadTime(newValue);
		}
		else if (columnName.equals("loadName")) {
			screenMasterVO.setLoadName(newValue);
		}
		else if (columnName.equals("updateDate")) {
			screenMasterVO.setUpdateDate(newValue);
		}
		else if (columnName.equals("updateTime")) {
			screenMasterVO.setUpdateTime(newValue);
		}
		else if (columnName.equals("updateName")) {
			screenMasterVO.setUpdateName(newValue);
		}
		
		System.out.println(screenMasterVO);
		return screenMasterVO;
	}
}
