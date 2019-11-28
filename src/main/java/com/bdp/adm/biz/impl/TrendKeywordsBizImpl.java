package com.bdp.adm.biz.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.bdp.adm.biz.TrendKeywordsBiz;
import com.bdp.adm.dao.TrendKeywordsDAO;
import com.bdp.adm.vo.TrendKeywordsVO;
import com.bdp.adm.vo.UserVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

//@Service
@Repository
public class TrendKeywordsBizImpl implements TrendKeywordsBiz {

	@Autowired
	private TrendKeywordsDAO trendKeywordsDAO;
	
	@Override
	public TrendKeywordsVO setTrendKeywordsVO(String[] data) {
		String regPtn4Column = "data\\[[a-zA-Z0-9_]*\\]\\[";
		String regPtn4NewValue = "data\\[[a-zA-Z0-9_\\]\\[]*=";
	
		TrendKeywordsVO trendKeywordsVO = new TrendKeywordsVO();
		
		for(int i = 1; i < data.length; i++) {
			String columnName = data[i].split(regPtn4Column)[1].split("]")[0];
			
			if(data[i].split(regPtn4NewValue).length < 2) {
				continue;
			}
			
			if (columnName.equals("trendKeywordsIdx")) {
				int trendKeywordsIdx = Integer.parseInt(data[i].split(regPtn4NewValue)[1]);
				trendKeywordsVO.setTrendKeywordsIdx(trendKeywordsIdx);
			}
			else if (columnName.equals("businessCode")) {
				String businessCode = data[i].split(regPtn4NewValue)[1];
				trendKeywordsVO.setBusinessCode(businessCode);
			}
			else if (columnName.equals("keyword")) {
				String keyword = data[i].split(regPtn4NewValue)[1];
				trendKeywordsVO.setKeyword(keyword);
			}
			else if (columnName.equals("registrantInfo")) {
				String registrantInfo = data[i].split(regPtn4NewValue)[1];
				trendKeywordsVO.setRegistrantInfo(registrantInfo);
			}
			else if (columnName.equals("delCd")) {
				String delCd = data[i].split(regPtn4NewValue)[1];
				if (delCd.equals("N")) delCd="";
				trendKeywordsVO.setDelCd(delCd);
			}
			else if (columnName.equals("version")) {
				String version = data[i].split(regPtn4NewValue)[1];
				trendKeywordsVO.setVersion(version);
			}
			else if (columnName.equals("loadDate")) {
				String loadDate = data[i].split(regPtn4NewValue)[1];
				trendKeywordsVO.setLoadDate(loadDate);
			}
			else if (columnName.equals("updateDate")) {
				String updateDate = data[i].split(regPtn4NewValue)[1];
				trendKeywordsVO.setUpdateDate(updateDate);
			}
		}

		return trendKeywordsVO;
	}
	
	// 2. SelectAll
	@Override
	public String selectAllTrendKeywords(String action) throws JsonProcessingException {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		UserVO curUser = (UserVO)attr.getRequest().getSession().getAttribute("user");
		if (curUser == null) curUser = new UserVO();
		String bCode = curUser.getBusinessCode();
		Map<String, String> param = new HashMap<String, String>();
		param.put("action", action);
		param.put("bCode", bCode);
		
		List<TrendKeywordsVO> trendKeywordsList = trendKeywordsDAO.selectAllTrendKeywords(param);
		List<TrendKeywordsVO> emptyList = new ArrayList<TrendKeywordsVO>();
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> trendKeywordsData = new HashMap<String, Object>();
		
		trendKeywordsData.put("data", trendKeywordsList);
		trendKeywordsData.put("options", emptyList);
		trendKeywordsData.put("files", emptyList);
		String trendKeywordsDataJSON="";
		
		trendKeywordsDataJSON = mapper.writeValueAsString(trendKeywordsData);
		
		return trendKeywordsDataJSON;
	}
	
	// 3. Insert
	@Override
	public int insertTrendKeywords(String[] data, String currentUser) {
		TrendKeywordsVO trendKeywordsVO = this.setTrendKeywordsVO(data);
		trendKeywordsVO.setLoadName(currentUser);
		
		return trendKeywordsDAO.insertTrendKeywords(trendKeywordsVO);
	}
	
	// 4. Update
	@Override
	public int updateTrendKeywords(String[] data, String currentUser) {
		TrendKeywordsVO trendKeywordsVO = new TrendKeywordsVO();
		trendKeywordsVO = this.setTrendKeywordsVO(data);
		trendKeywordsVO.setUpdateName(currentUser);
		
		return trendKeywordsDAO.updateTrendKeywords(trendKeywordsVO);
	}
	
	// 5. Remove
	@Override
	public int deleteTrendKeywords(String[] data, String currentUser) {
		TrendKeywordsVO trendKeywordsVO = this.setTrendKeywordsVO(data);
		trendKeywordsVO.setUpdateName(currentUser);
		return trendKeywordsDAO.deleteTrendKeywords(trendKeywordsVO);
	}
	
	// 6. SelectOne
	@Override
	public TrendKeywordsVO selectOneTrendKeywords(String DT_RowId, String columnName, String newValue) {
		
		TrendKeywordsVO trendKeywordsVO = trendKeywordsDAO.selectOneTrendKeywords(DT_RowId);
		
		//수정된 행, 새로운 값을 다시 set해서 
		if (columnName.equals("businessCode")) {
			trendKeywordsVO.setBusinessCode(newValue);
		}
		else if (columnName.equals("keyword")) {
			trendKeywordsVO.setKeyword(newValue);
		}
		else if (columnName.equals("registrantInfo")) {
			trendKeywordsVO.setRegistrantInfo(newValue);
		}
		else if (columnName.equals("version")) {
			trendKeywordsVO.setVersion(newValue);
		}
		else if (columnName.equals("loadDate")) {
			trendKeywordsVO.setLoadDate(newValue);
		}
		else if (columnName.equals("updateDate")) {
			trendKeywordsVO.setUpdateDate(newValue);
		}
		
		return trendKeywordsVO;
	}
}
