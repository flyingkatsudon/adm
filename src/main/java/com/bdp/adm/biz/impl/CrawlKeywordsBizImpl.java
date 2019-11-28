package com.bdp.adm.biz.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.bdp.adm.biz.CrawlKeywordsBiz;
import com.bdp.adm.dao.CrawlKeywordsDAO;
import com.bdp.adm.vo.CrawlKeywordsVO;
import com.bdp.adm.vo.UserVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Repository
public class CrawlKeywordsBizImpl implements CrawlKeywordsBiz {

	@Autowired
	private CrawlKeywordsDAO crawlKeywordsDAO;
	
	@Override
	public CrawlKeywordsVO setCrawlKeywordsVO(String[] data) {
		String regPtn4Column = "data\\[[a-zA-Z0-9_]*\\]\\[";
		String regPtn4NewValue = "data\\[[a-zA-Z0-9_\\]\\[]*=";
	
		CrawlKeywordsVO crawlKeywordsVO = new CrawlKeywordsVO();
		
		for(int i = 1; i < data.length; i++) {
			String columnName = data[i].split(regPtn4Column)[1].split("]")[0];
			
			if(data[i].split(regPtn4NewValue).length < 2) {
				continue;
			}
			if (columnName.equals("keywordId")) {
				String keywordId = data[i].split(regPtn4NewValue)[1];
				crawlKeywordsVO.setKeywordId(Integer.parseInt(keywordId));
			} 
			else if (columnName.equals("businessCode")) {
				String businessCode = data[i].split(regPtn4NewValue)[1];
				crawlKeywordsVO.setBusinessCode(businessCode);
			}
			else if (columnName.equals("categoryCode")) {
				String categoryCode = data[i].split(regPtn4NewValue)[1];
				crawlKeywordsVO.setCategoryCode(categoryCode);
			}
			else if (columnName.equals("keyword")) {
				String keyword = data[i].split(regPtn4NewValue)[1];
				crawlKeywordsVO.setKeyword(keyword);
			}
			else if (columnName.equals("posInfo")) {
				String posInfo = data[i].split(regPtn4NewValue)[1];
				crawlKeywordsVO.setPosInfo(posInfo);
			}
			else if (columnName.equals("cnt")) {
				String cnt = data[i].split(regPtn4NewValue)[1];
				crawlKeywordsVO.setCnt(Integer.parseInt(cnt));
			}
			else if (columnName.equals("registrantInfo")) {
				String registrantInfo = data[i].split(regPtn4NewValue)[1];
				crawlKeywordsVO.setRegistrantInfo(registrantInfo);
			}
			else if (columnName.equals("delCd")) {
				String delCd = data[i].split(regPtn4NewValue)[1];
				if (delCd.equals("N")) delCd="";
				crawlKeywordsVO.setDelCd(delCd);
			}
			else if (columnName.equals("version")) {
				String version = data[i].split(regPtn4NewValue)[1];
				crawlKeywordsVO.setVersion(version);
			}
			else if (columnName.equals("loadDate")) {
				String loadDate = data[i].split(regPtn4NewValue)[1];
				crawlKeywordsVO.setLoadDate(loadDate);
			}
			else if (columnName.equals("updateDate")) {
				String updateDate = data[i].split(regPtn4NewValue)[1];
				crawlKeywordsVO.setUpdateDate(updateDate);
			}
		}

		return crawlKeywordsVO;
	}
	
	// 2. SelectAll
	@Override
	public String selectAllCrawlKeywords(String action) throws JsonProcessingException {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		UserVO curUser = (UserVO)attr.getRequest().getSession().getAttribute("user");
		if (curUser == null) curUser = new UserVO();
		String bCode = curUser.getBusinessCode();
		Map<String, String> param = new HashMap<String, String>();
		param.put("action", action);
		param.put("bCode", bCode);
		
		List<CrawlKeywordsVO> crawlKeywordsList = crawlKeywordsDAO.selectAllCrawlKeywords(param);
		List<CrawlKeywordsVO> emptyList = new ArrayList<CrawlKeywordsVO>();
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> crawlKeywordsData = new HashMap<String, Object>();
		
		crawlKeywordsData.put("data", crawlKeywordsList);
		crawlKeywordsData.put("options", emptyList);
		crawlKeywordsData.put("files", emptyList);
		String crawlKeywordsDataJSON="";
		
		crawlKeywordsDataJSON = mapper.writeValueAsString(crawlKeywordsData);
		
		return crawlKeywordsDataJSON;
	}
	
	// 3. Insert
	@Override
	public int insertCrawlKeywords(String[] data, String currentUser) {
		CrawlKeywordsVO crawlKeywordsVO = this.setCrawlKeywordsVO(data);
		crawlKeywordsVO.setLoadName(currentUser);
		
		return crawlKeywordsDAO.insertCrawlKeywords(crawlKeywordsVO);
	}
	
	// 4. Update
	@Override
	public int updateCrawlKeywords(String[] data, String currentUser) {
		CrawlKeywordsVO crawlKeywordsVO = new CrawlKeywordsVO();
		crawlKeywordsVO = this.setCrawlKeywordsVO(data);
		crawlKeywordsVO.setUpdateName(currentUser);
		
		return crawlKeywordsDAO.updateCrawlKeywords(crawlKeywordsVO);
	}
	
	// 5. Remove
	@Override
	public int deleteCrawlKeywords(String[] data, String currentUser) {
		CrawlKeywordsVO crawlKeywordsVO = this.setCrawlKeywordsVO(data);
		crawlKeywordsVO.setUpdateName(currentUser);
		
		return crawlKeywordsDAO.deleteCrawlKeywords(crawlKeywordsVO);
	}
	
	// 6. SelectOne
	@Override
	public CrawlKeywordsVO selectOneCrawlKeywords(String DT_RowId, String columnName, String newValue) {
		
		System.out.println("selectOne IN!!!"+DT_RowId+":"+columnName+":"+newValue);
		
		CrawlKeywordsVO crawlKeywordsVO = crawlKeywordsDAO.selectOneCrawlKeywords(DT_RowId);
		System.out.println(crawlKeywordsVO);
	
		//수정된 행, 새로운 값을 다시 set해서 
		if (columnName.equals("keywordId")) {
			crawlKeywordsVO.setKeywordId(Integer.parseInt(newValue));
		}
		else if (columnName.equals("businessCode")) {
			crawlKeywordsVO.setBusinessCode(newValue);
		}
		else if (columnName.equals("categoryCode")) {
			crawlKeywordsVO.setCategoryCode(newValue);
		}
		else if (columnName.equals("keyword")) {
			crawlKeywordsVO.setKeyword(newValue);
		}
		else if (columnName.equals("posInfo")) {
			crawlKeywordsVO.setPosInfo(newValue);
		}
		else if (columnName.equals("cnt")) {
			crawlKeywordsVO.setCnt(Integer.parseInt(newValue));
		}
		else if (columnName.equals("registrantInfo")) {
			crawlKeywordsVO.setRegistrantInfo(newValue);
		}
		else if (columnName.equals("version")) {
			crawlKeywordsVO.setVersion(newValue);
		}
		else if (columnName.equals("loadDate")) {
			crawlKeywordsVO.setLoadDate(newValue);
		}
		else if (columnName.equals("updateDate")) {
			crawlKeywordsVO.setUpdateDate(newValue);
		}
		
		return crawlKeywordsVO;
	}
}
