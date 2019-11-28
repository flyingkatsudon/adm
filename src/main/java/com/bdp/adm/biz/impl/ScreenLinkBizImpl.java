package com.bdp.adm.biz.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bdp.adm.biz.ScreenLinkBiz;
import com.bdp.adm.dao.ScreenLinkDAO;
import com.bdp.adm.vo.RoleVO;
import com.bdp.adm.vo.ScreenLinkVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

//@Service
@Repository
public class ScreenLinkBizImpl implements ScreenLinkBiz {

	@Autowired
	private ScreenLinkDAO screenLinkDAO;
	
	@Override
	public ScreenLinkVO setScreenLinkVO(String[] data) {
		String regPtn4Column = "data\\[[a-zA-Z0-9_]*\\]\\[";
		String regPtn4NewValue = "data\\[[a-zA-Z0-9_\\]\\[]*=";
	
		ScreenLinkVO screenLinkVO = new ScreenLinkVO();
		
		for(int i = 1; i < data.length; i++) {
			String columnName = data[i].split(regPtn4Column)[1].split("]")[0];
			
			if(data[i].split(regPtn4NewValue).length < 2) {
				continue;
			}
			
			if (columnName.equals("linkIdx")) {
				int linkIdx = Integer.parseInt(data[i].split(regPtn4NewValue)[1]);
				screenLinkVO.setLinkIdx(linkIdx);
			}
			else if (columnName.equals("screenNo")) {
				String screenNo = data[i].split(regPtn4NewValue)[1];
				screenLinkVO.setScreenNo(screenNo);
			}
			else if (columnName.equals("resType")) {
				String resType = data[i].split(regPtn4NewValue)[1];
				screenLinkVO.setResType(resType);
			}
			else if (columnName.equals("delCd")) {
				String delCd = data[i].split(regPtn4NewValue)[1];
				if (delCd.equals("N")) delCd="";
				screenLinkVO.setDelCd(delCd);
			}
			else if (columnName.equals("resTypeName")) {
				String resTypeName = data[i].split(regPtn4NewValue)[1];
				screenLinkVO.setResTypeName(resTypeName);
			}
			else if (columnName.equals("loadDate")) {
				String loadDate = data[i].split(regPtn4NewValue)[1];
				screenLinkVO.setLoadDate(loadDate);
			}
			else if (columnName.equals("loadTime")) {
				String loadTime = data[i].split(regPtn4NewValue)[1];
				screenLinkVO.setLoadTime(loadTime);
			}
			else if (columnName.equals("loadName")) {
				String loadName = data[i].split(regPtn4NewValue)[1];
				screenLinkVO.setLoadName(loadName);
			}
			else if (columnName.equals("updateDate")) {
				String updateDate = data[i].split(regPtn4NewValue)[1];
				screenLinkVO.setUpdateDate(updateDate);
			}
			else if (columnName.equals("updateTime")) {
				String updateTime = data[i].split(regPtn4NewValue)[1];
				screenLinkVO.setUpdateTime(updateTime);
			}
			else if (columnName.equals("updateName")) {
				String updateName = data[i].split(regPtn4NewValue)[1];
				screenLinkVO.setUpdateName(updateName);
			}
		}

		return screenLinkVO;
	}
	
	// 2. SelectAll
	@Override
	public String selectAllScreenLink(String action) throws JsonProcessingException {
		List<ScreenLinkVO> screenLinkList = screenLinkDAO.selectAllScreenLink(action);
		List<ScreenLinkVO> emptyList = new ArrayList<ScreenLinkVO>();
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> screenLinkData = new HashMap<String, Object>();
		
		screenLinkData.put("data", screenLinkList);
		screenLinkData.put("options", emptyList);
		screenLinkData.put("files", emptyList);
		String screenLinkDataJSON="";
		
		screenLinkDataJSON = mapper.writeValueAsString(screenLinkData);
		
		return screenLinkDataJSON;
	}
	
	// 3. Insert
	@Override
	public int insertScreenLink(String[] data, String currentUser) {
		ScreenLinkVO screenLinkVO = this.setScreenLinkVO(data);
		screenLinkVO.setLoadName(currentUser);
	
		return screenLinkDAO.insertScreenLink(screenLinkVO);
	}
	
	// 4. Update
	@Override
	public int updateScreenLink(String[] data, String currentUser) {
		ScreenLinkVO screenLinkVO = new ScreenLinkVO();
		screenLinkVO = this.setScreenLinkVO(data);
		screenLinkVO.setUpdateName(currentUser);
		
		String regPtn4DTRowId = "data\\[";
		String DT_RowId = data[1].split(regPtn4DTRowId)[1].split("]")[0];
		
		String oldResType = screenLinkDAO.selectOneScreenLink(DT_RowId).getResType();
		screenLinkVO.setOldResType(oldResType);
		
		return screenLinkDAO.updateScreenLink(screenLinkVO);
	}
	
	// 5. Remove
	@Override
	public int deleteScreenLink(String[] data, String currentUser) {
		ScreenLinkVO screenLinkVO = this.setScreenLinkVO(data);
		screenLinkVO.setUpdateName(currentUser);
		
		return screenLinkDAO.deleteScreenLink(screenLinkVO);
	}
	
}
