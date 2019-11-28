package com.bdp.adm.biz.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bdp.adm.biz.ScreenArchiBiz;
import com.bdp.adm.dao.ScreenArchiDAO;
import com.bdp.adm.vo.ScreenArchiVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

//@Service
@Repository
public class ScreenArchiBizImpl implements ScreenArchiBiz {

	@Autowired
	private ScreenArchiDAO screenArchiDAO;
	
	@Override
	public ScreenArchiVO setScreenArchiVO(String[] data) {
		String regPtn4Column = "data\\[[a-zA-Z0-9_]*\\]\\[";
		String regPtn4NewValue = "data\\[[a-zA-Z0-9_\\]\\[]*=";
	
		ScreenArchiVO screenArchiVO = new ScreenArchiVO();
		
		for(int i = 1; i < data.length; i++) {
			String columnName = data[i].split(regPtn4Column)[1].split("]")[0];
			
			if(data[i].split(regPtn4NewValue).length < 2) {
				continue;
			}
			
			if (columnName.equals("archiIdx")) {
				int archiIdx = Integer.parseInt(data[i].split(regPtn4NewValue)[1]);
				screenArchiVO.setArchiIdx(archiIdx);
			}
			else if (columnName.equals("screenNo")) {
				String screenNo = data[i].split(regPtn4NewValue)[1];
				screenArchiVO.setScreenNo(screenNo);
			}
			else if (columnName.equals("screenArchi")) {
				String screenArchi = data[i].split(regPtn4NewValue)[1];
				screenArchiVO.setScreenArchi(screenArchi);
			}
			else if (columnName.equals("delCd")) {
				String delCd = data[i].split(regPtn4NewValue)[1];
				if (delCd.equals("N")) delCd="";
				screenArchiVO.setDelCd(delCd);
			}
			else if (columnName.equals("loadDate")) {
				String loadDate = data[i].split(regPtn4NewValue)[1];
				screenArchiVO.setLoadDate(loadDate);
			}
			else if (columnName.equals("loadTime")) {
				String loadTime = data[i].split(regPtn4NewValue)[1];
				screenArchiVO.setLoadTime(loadTime);
			}
			else if (columnName.equals("loadName")) {
				String loadName = data[i].split(regPtn4NewValue)[1];
				screenArchiVO.setLoadName(loadName);
			}
			else if (columnName.equals("updateDate")) {
				String updateDate = data[i].split(regPtn4NewValue)[1];
				screenArchiVO.setUpdateDate(updateDate);
			}
			else if (columnName.equals("updateTime")) {
				String updateTime = data[i].split(regPtn4NewValue)[1];
				screenArchiVO.setUpdateTime(updateTime);
			}
			else if (columnName.equals("updateName")) {
				String updateName = data[i].split(regPtn4NewValue)[1];
				screenArchiVO.setUpdateName(updateName);
			}
		}

		return screenArchiVO;
	}
	
	// 2. SelectAll
	@Override
	public String selectAllScreenArchi(String action) throws JsonProcessingException {
		List<ScreenArchiVO> screenArchiList = screenArchiDAO.selectAllScreenArchi(action);
		List<ScreenArchiVO> emptyList = new ArrayList<ScreenArchiVO>();
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> screenArchiData = new HashMap<String, Object>();
		
		screenArchiData.put("data", screenArchiList);
		screenArchiData.put("options", emptyList);
		screenArchiData.put("files", emptyList);
		String screenArchiDataJSON="";
		
		screenArchiDataJSON = mapper.writeValueAsString(screenArchiData);
		
		return screenArchiDataJSON;
	}
	
	// 3. Insert
	@Override
	public int insertScreenArchi(String[] data, String currentUser) {
		ScreenArchiVO screenArchiVO = this.setScreenArchiVO(data);
		screenArchiVO.setLoadName(currentUser);
	
		return screenArchiDAO.insertScreenArchi(screenArchiVO);
	}
	
	// 4. Update
	@Override
	public int updateScreenArchi(String[] data, String currentUser) {
		ScreenArchiVO screenArchiVO = new ScreenArchiVO();
		screenArchiVO = this.setScreenArchiVO(data);
		screenArchiVO.setUpdateName(currentUser);
		
		return screenArchiDAO.updateScreenArchi(screenArchiVO);
	}
	
	// 5. Remove
	@Override
	public int deleteScreenArchi(String[] data, String currentUser) {
		ScreenArchiVO screenArchiVO = this.setScreenArchiVO(data);
		screenArchiVO.setUpdateName(currentUser);
		
		return screenArchiDAO.deleteScreenArchi(screenArchiVO);
	}

}
