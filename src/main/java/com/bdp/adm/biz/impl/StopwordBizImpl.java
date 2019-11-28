package com.bdp.adm.biz.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.bdp.adm.biz.StopwordBiz;
import com.bdp.adm.dao.StopwordDAO;
import com.bdp.adm.vo.StopwordVO;
import com.bdp.adm.vo.UserVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Repository
public class StopwordBizImpl implements StopwordBiz {

	@Autowired
	private StopwordDAO stopwordDAO;
	
	@Override
	public StopwordVO setStopwordVO(String[] data) {
		String regPtn4Column = "data\\[[a-zA-Z0-9_]*\\]\\[";
		String regPtn4NewValue = "data\\[[a-zA-Z0-9_\\]\\[]*=";
	
		StopwordVO stopwordVO = new StopwordVO();
		
		for(int i = 1; i < data.length; i++) {
			String columnName = data[i].split(regPtn4Column)[1].split("]")[0];
			
			//만약 필드에 비어있는 값이 있으면 for문 탈출
			if(data[i].split(regPtn4NewValue).length < 2) {
				continue;
			}
			
			if (columnName.equals("keywordId")) {
				int keywordId = Integer.parseInt(data[i].split(regPtn4NewValue)[1]);
				stopwordVO.setKeywordId(keywordId);
			}
			else if (columnName.equals("businessCode")) {
				String businessCode = data[i].split(regPtn4NewValue)[1];
				stopwordVO.setBusinessCode(businessCode);
			}
			else if (columnName.equals("categoryCode")) {
				String categoryCode = data[i].split(regPtn4NewValue)[1];
				stopwordVO.setCategoryCode(categoryCode);
			}
			else if (columnName.equals("keyword")) {
				String keyword = data[i].split(regPtn4NewValue)[1];
				stopwordVO.setKeyword(keyword);
			}
			else if (columnName.equals("registrantInfo")) {
				String registrantInfo = data[i].split(regPtn4NewValue)[1];
				stopwordVO.setRegistrantInfo(registrantInfo);	
			}
			else if (columnName.equals("delCd")) {
				String delCd = data[i].split(regPtn4NewValue)[1];
				if (delCd.equals("N")) delCd="";
				stopwordVO.setDelCd(delCd);
			}
			else if (columnName.equals("stopType")) {
				String stopType = data[i].split(regPtn4NewValue)[1];
				stopwordVO.setStopType(stopType);
			}
			else if (columnName.equals("version")) {
				String version = data[i].split(regPtn4NewValue)[1];
				stopwordVO.setVersion(version);	
			}
			else if (columnName.equals("loadDate")) {
				String loadDate = data[i].split(regPtn4NewValue)[1];
				stopwordVO.setLoadDate(loadDate);
				
			}
			else if (columnName.equals("updateDate")) {
				String updateDate = data[i].split(regPtn4NewValue)[1];
				stopwordVO.setUpdateDate(updateDate);
			}
			
		}

		return stopwordVO;
	}
	
	// 2. SelectAll
	@Override
	public String selectAllStopword(String action) throws JsonProcessingException {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		UserVO curUser = (UserVO)attr.getRequest().getSession().getAttribute("user");
		if (curUser == null) curUser = new UserVO();
		String bCode = curUser.getBusinessCode();
		Map<String, String> param = new HashMap<String, String>();
		param.put("action", action);
		param.put("bCode", bCode);
		
		List<StopwordVO> stopwordList = stopwordDAO.selectAllStopword(param);
		List<StopwordVO> emptyList = new ArrayList<StopwordVO>();
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> stopwordData = new HashMap<String, Object>();
		
		stopwordData.put("data", stopwordList);
		stopwordData.put("options", emptyList);
		stopwordData.put("files", emptyList);
		String stopwordDataJSON="";
		
		stopwordDataJSON = mapper.writeValueAsString(stopwordData);
		
		return stopwordDataJSON;
	}
	
	// 3. Insert
	@Override
	public int insertStopword(String[] data, String currentUser) {
		StopwordVO stopwordVO = this.setStopwordVO(data);
		stopwordVO.setLoadName(currentUser);
		
		return stopwordDAO.insertStopword(stopwordVO);
	}
	
	// 4. Update
	@Override
	public int updateStopword(String[] data, String currentUser) {
		StopwordVO stopwordVO = new StopwordVO();
		stopwordVO = this.setStopwordVO(data);
		stopwordVO.setUpdateName(currentUser);
	
		return stopwordDAO.updateStopword(stopwordVO);
	}
	
	// 5. Remove
	@Override
	public int deleteStopword(String[] data, String currentUser) {
		StopwordVO stopwordVO = this.setStopwordVO(data);
		stopwordVO.setUpdateName(currentUser);
		return stopwordDAO.deleteStopword(stopwordVO);
	}
	
	// 6. SelectOne
	@Override
	public StopwordVO selectOneStopword(String DT_RowId, String columnName, String newValue) {
		StopwordVO stopwordVO = stopwordDAO.selectOneStopword(DT_RowId);
		
		//수정된 행, 새로운 값을 다시 set해서 
		if (columnName.equals("keywordId")) {
			stopwordVO.setKeywordId(Integer.parseInt(newValue));
		}
		else if (columnName.equals("businessCode")) {
			stopwordVO.setBusinessCode(newValue);
		}
		else if (columnName.equals("categoryCode")) {
			stopwordVO.setCategoryCode(newValue);
		}
		else if (columnName.equals("keyword")) {
			stopwordVO.setKeyword(newValue);
		}
		else if (columnName.equals("registrantInfo")) {
			stopwordVO.setRegistrantInfo(newValue);
			
		}
		else if (columnName.equals("version")) {
			stopwordVO.setVersion(newValue);
		}
		else if (columnName.equals("loadDate")) {
			stopwordVO.setLoadDate(newValue);
		}
		else if (columnName.equals("updateDate")) {
			stopwordVO.setUpdateDate(newValue);
		}
		
		return stopwordVO;
	}
}
