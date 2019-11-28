package com.bdp.adm.biz.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bdp.adm.biz.StatusNameBiz;
import com.bdp.adm.dao.StatusNameDAO;
import com.bdp.adm.vo.StatusNameVO;
import com.bdp.adm.vo.TitleNameVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Repository
public class StatusNameBizImpl implements StatusNameBiz {

	@Autowired
	private StatusNameDAO statusNameDAO;
	
	@Override
	public StatusNameVO setStatusNameVO(String[] data) {
		String regPtn4Column = "data\\[[a-zA-Z0-9_]*\\]\\[";
		String regPtn4NewValue = "data\\[[a-zA-Z0-9_\\]\\[]*=";
	
		StatusNameVO statusNameVO = new StatusNameVO();
		
		for(int i = 1; i < data.length; i++) {
			String columnName = data[i].split(regPtn4Column)[1].split("]")[0];
			
			if(data[i].split(regPtn4NewValue).length < 2) {
				continue;
			}
			
			if (columnName.equals("statusIdx")) {
				int titleIdx = Integer.parseInt(data[i].split(regPtn4NewValue)[1]);
				statusNameVO.setStatusIdx(titleIdx);
			}
			else if (columnName.equals("userStatusCd")) {
				String userTitleCd = data[i].split(regPtn4NewValue)[1];
				statusNameVO.setUserStatusCd(userTitleCd);
			}
			else if (columnName.equals("userStatusName")) {
				String userTitleName = data[i].split(regPtn4NewValue)[1];
				statusNameVO.setUserStatusName(userTitleName);
			}
			else if (columnName.equals("delCd")) {
				String delCd = data[i].split(regPtn4NewValue)[1];
				if (delCd.equals("N")) delCd="";
				statusNameVO.setDelCd(delCd);
			}
			else if (columnName.equals("loadDate")) {
				String loadDate = data[i].split(regPtn4NewValue)[1];
				statusNameVO.setLoadDate(loadDate);
			}
			else if (columnName.equals("loadTime")) {
				String loadTime = data[i].split(regPtn4NewValue)[1];
				statusNameVO.setLoadTime(loadTime);
			}
			else if (columnName.equals("loadName")) {
				String loadName = data[i].split(regPtn4NewValue)[1];
				statusNameVO.setLoadName(loadName);
			}
			else if (columnName.equals("updateDate")) {
				String updateDate = data[i].split(regPtn4NewValue)[1];
				statusNameVO.setUpdateDate(updateDate);
			}
			else if (columnName.equals("updateTime")) {
				String updateTime = data[i].split(regPtn4NewValue)[1];
				statusNameVO.setUpdateTime(updateTime);
			}
			else if (columnName.equals("updateName")) {
				String updateName = data[i].split(regPtn4NewValue)[1];
				statusNameVO.setUpdateName(updateName);
			}
		}

		return statusNameVO;
	}
	
	// 2. SelectAll
	@Override
	public String selectAllStatusName(String action) throws JsonProcessingException {
		List<StatusNameVO> statusNameList = statusNameDAO.selectAllStatusName(action);
		List<StatusNameVO> emptyList = new ArrayList<StatusNameVO>();
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> statusNameData = new HashMap<String, Object>();
		
		statusNameData.put("data", statusNameList);
		statusNameData.put("options", emptyList);
		statusNameData.put("files", emptyList);
		String statusNameDataJSON="";
		
		statusNameDataJSON = mapper.writeValueAsString(statusNameData);
		
		return statusNameDataJSON;
	}
	
	// 3. Insert
	@Override
	public int insertStatusName(String[] data, String currentUser) {
		StatusNameVO statusNameVO = this.setStatusNameVO(data);
		statusNameVO.setLoadName(currentUser);
		
		return statusNameDAO.insertStatusName(statusNameVO);
	}
	
	// 4. Update
	@Override
	public int updateStatusName(String[] data, String currentUser) {
		StatusNameVO statusNameVO = this.setStatusNameVO(data);
		statusNameVO.setUpdateName(currentUser);

		return statusNameDAO.updateStatusName(statusNameVO);
	}
	
	// 5. Remove
	@Override
	public int deleteStatusName(String[] data, String currentUser) {
		StatusNameVO statusNameVO = this.setStatusNameVO(data);
		statusNameVO.setUpdateName(currentUser);
		
		return statusNameDAO.deleteStatusName(statusNameVO);
	}
	
}
