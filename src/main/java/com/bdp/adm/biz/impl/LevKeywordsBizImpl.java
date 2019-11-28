package com.bdp.adm.biz.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bdp.adm.biz.LevKeywordsBiz;
import com.bdp.adm.dao.LevKeywordsDAO;
import com.bdp.adm.vo.LevKeywordsVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Repository
public class LevKeywordsBizImpl implements LevKeywordsBiz {

	@Autowired
	private LevKeywordsDAO levKeywordsDAO;
	
	// Set VO
	@Override
	public LevKeywordsVO setLevKeywordsVO(String[] data) {
		String regPtn4Column = "data\\[[a-zA-Z0-9_]*\\]\\[";
		String regPtn4NewValue = "data\\[[a-zA-Z0-9_\\]\\[]*=";
	
		LevKeywordsVO levKeywordsVO = new LevKeywordsVO();
		
		for(int i = 1; i < data.length; i++) {
			String columnName = data[i].split(regPtn4Column)[1].split("]")[0];
			
			if(data[i].split(regPtn4NewValue).length < 2) {
				continue;
			}
			
			if (columnName.equals("seqKeyword")) {
				int seqKeyword = Integer.parseInt(data[i].split(regPtn4NewValue)[1]);
				levKeywordsVO.setSeqKeyword(seqKeyword);
			}
			else if (columnName.equals("level1")) {
				String level1 = data[i].split(regPtn4NewValue)[1];
				levKeywordsVO.setLevel1(level1);
			}
			else if (columnName.equals("level2")) {
				String level2 = data[i].split(regPtn4NewValue)[1];
				levKeywordsVO.setLevel2(level2);
			}
			else if (columnName.equals("level3")) {
				String level3 = data[i].split(regPtn4NewValue)[1];
				levKeywordsVO.setLevel3(level3);
			}
			else if (columnName.equals("keyword1")) {
				String keyword1 = data[i].split(regPtn4NewValue)[1];
				levKeywordsVO.setKeyword1(keyword1);
			}
			else if (columnName.equals("keyword2")) {
				String keyword2 = data[i].split(regPtn4NewValue)[1];
				levKeywordsVO.setKeyword2(keyword2);
			}
			else if (columnName.equals("keyword3")) {
				String keyword3 = data[i].split(regPtn4NewValue)[1];
				levKeywordsVO.setKeyword3(keyword3);
			}
			else if (columnName.equals("keyword4")) {
				String keyword4 = data[i].split(regPtn4NewValue)[1];
				levKeywordsVO.setKeyword4(keyword4);
			}
			else if (columnName.equals("keyword5")) {
				String keyword5 = data[i].split(regPtn4NewValue)[1];
				levKeywordsVO.setKeyword5(keyword5);
			}
			else if (columnName.equals("keyword6")) {
				String keyword6 = data[i].split(regPtn4NewValue)[1];
				levKeywordsVO.setKeyword6(keyword6);
			}
			else if (columnName.equals("keyword7")) {
				String keyword7 = data[i].split(regPtn4NewValue)[1];
				levKeywordsVO.setKeyword7(keyword7);
			}
			else if (columnName.equals("keyword8")) {
				String keyword8 = data[i].split(regPtn4NewValue)[1];
				levKeywordsVO.setKeyword8(keyword8);
			}
			else if (columnName.equals("keyword9")) {
				String keyword9 = data[i].split(regPtn4NewValue)[1];
				levKeywordsVO.setKeyword9(keyword9);
			}
			else if (columnName.equals("keyword10")) {
				String keyword10 = data[i].split(regPtn4NewValue)[1];
				levKeywordsVO.setKeyword10(keyword10);
			}
			else if (columnName.equals("keyword11")) {
				String keyword11 = data[i].split(regPtn4NewValue)[1];
				levKeywordsVO.setKeyword11(keyword11);
			}
			else if (columnName.equals("keyword12")) {
				String keyword12 = data[i].split(regPtn4NewValue)[1];
				levKeywordsVO.setKeyword12(keyword12);
			}
			else if (columnName.equals("keyword13")) {
				String keyword13 = data[i].split(regPtn4NewValue)[1];
				levKeywordsVO.setKeyword13(keyword13);
			}
			else if (columnName.equals("keyword14")) {
				String keyword14 = data[i].split(regPtn4NewValue)[1];
				levKeywordsVO.setKeyword14(keyword14);
			}
			else if (columnName.equals("keyword15")) {
				String keyword15 = data[i].split(regPtn4NewValue)[1];
				levKeywordsVO.setKeyword15(keyword15);
			}
			else if (columnName.equals("delCd")) {
				String delCd = data[i].split(regPtn4NewValue)[1];
				if (delCd.equals("N")) delCd="";
				levKeywordsVO.setDelCd(delCd);
			}
			else if (columnName.equals("loadDate")) {
				String loadDate = data[i].split(regPtn4NewValue)[1];
				levKeywordsVO.setLoadDate(loadDate);
			}
			else if (columnName.equals("updateDate")) {
				String updateDate = data[i].split(regPtn4NewValue)[1];
				levKeywordsVO.setUpdateDate(updateDate);
			}
		}

		return levKeywordsVO;
	}
	
	// 2. SelectAll
	@Override
	public String selectAllLevKeywords(String action) throws JsonProcessingException {
		List<LevKeywordsVO> LevKeywordsList = levKeywordsDAO.selectAllLevKeywords(action);
		List<LevKeywordsVO> emptyList = new ArrayList<LevKeywordsVO>();
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> LevKeywordsData = new HashMap<String, Object>();
		
		LevKeywordsData.put("data", LevKeywordsList);
		LevKeywordsData.put("options", emptyList);
		LevKeywordsData.put("files", emptyList);
		String LevKeywordsDataJSON="";
		
		LevKeywordsDataJSON = mapper.writeValueAsString(LevKeywordsData);
		
		return LevKeywordsDataJSON;
	}
	
	// 3. Insert
	@Override
	public int insertLevKeywords(String[] data, String currentUser) {
		LevKeywordsVO levKeywordsVO = this.setLevKeywordsVO(data);
		levKeywordsVO.setLoadName(currentUser);
		
		return levKeywordsDAO.insertLevKeywords(levKeywordsVO);
	}
	
	// 4. Update
	@Override
	public int updateLevKeywords(String[] data, String currentUser) {
		LevKeywordsVO levKeywordsVO = this.setLevKeywordsVO(data);
		levKeywordsVO.setUpdateName(currentUser);
	
		return levKeywordsDAO.updateLevKeywords(levKeywordsVO);
	}
	
	// 5. Remove
	@Override
	public int deleteLevKeywords(String[] data, String currentUser) {
		LevKeywordsVO levKeywordsVO = this.setLevKeywordsVO(data);
		levKeywordsVO.setUpdateName(currentUser);
		
		return levKeywordsDAO.deleteLevKeywords(levKeywordsVO);
	}
	
}
