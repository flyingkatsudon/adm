package com.bdp.adm.biz.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.bdp.adm.biz.DictWordBiz;
import com.bdp.adm.dao.DictWordDAO;
import com.bdp.adm.vo.DictWordVO;
import com.bdp.adm.vo.UserVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Repository
public class DictWordBizImpl implements DictWordBiz {

	@Autowired
	private DictWordDAO dictWordDAO;
	
	@Override
	public DictWordVO setDictWordVO(String[] data) {
		String regPtn4Column = "data\\[[a-zA-Z0-9_]*\\]\\[";
		String regPtn4NewValue = "data\\[[a-zA-Z0-9_\\]\\[]*=";
	
		DictWordVO dictWordVO = new DictWordVO();
		
		for(int i = 1; i < data.length; i++) {
			String columnName = data[i].split(regPtn4Column)[1].split("]")[0];
			
			if(data[i].split(regPtn4NewValue).length < 2) {
				continue;
			}
			
			if (columnName.equals("dictWordIdx")) {
				int dictWordIdx = Integer.parseInt(data[i].split(regPtn4NewValue)[1]);
				dictWordVO.setDictWordIdx(dictWordIdx);
			}
			else if (columnName.equals("word")) {
				String word = data[i].split(regPtn4NewValue)[1];
				dictWordVO.setWord(word);
			}
			else if (columnName.equals("businessCode")) {
				String businessCode = data[i].split(regPtn4NewValue)[1];
				dictWordVO.setBusinessCode(businessCode);
			}
			else if (columnName.equals("nerInfo")) {
				String nerInfo = data[i].split(regPtn4NewValue)[1];
				dictWordVO.setNerInfo(nerInfo);
			}
			else if (columnName.equals("aliasList")) {
				String aliasList = data[i].split(regPtn4NewValue)[1];
				dictWordVO.setAliasList(aliasList);
			}
			else if (columnName.equals("delCd")) {
				String delCd = data[i].split(regPtn4NewValue)[1];
				if (delCd.equals("N")) delCd="";
				dictWordVO.setDelCd(delCd);
			}
			else if (columnName.equals("version")) {
				String version = data[i].split(regPtn4NewValue)[1];
				dictWordVO.setVersion(version);
			}
			else if (columnName.equals("loadDate")) {
				String loadDate = data[i].split(regPtn4NewValue)[1];
				dictWordVO.setLoadDate(loadDate);
			}
			else if (columnName.equals("updateDate")) {
				String updateDate = data[i].split(regPtn4NewValue)[1];
				dictWordVO.setUpdateDate(updateDate);
			}
			
		}

		return dictWordVO;
	}
	
	// 2. SelectAll
	@Override
	public String selectAllDictWord(String action) throws JsonProcessingException {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		UserVO curUser = (UserVO)attr.getRequest().getSession().getAttribute("user");
		if (curUser == null) curUser = new UserVO();
		String bCode = curUser.getBusinessCode();
		Map<String, String> param = new HashMap<String, String>();
		param.put("action", action);
		param.put("bCode", bCode);
		
		List<DictWordVO> dictWordList = dictWordDAO.selectAllDictWord(param);
		List<DictWordVO> emptyList = new ArrayList<DictWordVO>();
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> dictWordData = new HashMap<String, Object>();
		
		dictWordData.put("data", dictWordList);
		dictWordData.put("options", emptyList);
		dictWordData.put("files", emptyList);
		String dictWordDataJSON="";
		
		dictWordDataJSON = mapper.writeValueAsString(dictWordData);
		
		return dictWordDataJSON;
	}
	
	// 3. Insert
	@Override
	public int insertDictWord(String[] data, String currentUser) {
		DictWordVO dictWordVO = this.setDictWordVO(data);
		dictWordVO.setLoadName(currentUser);
		
		return dictWordDAO.insertDictWord(dictWordVO);
	}
	
	// 4. Update
	@Override
	public int updateDictWord(String[] data, String currentUser) {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		UserVO curUser = (UserVO)attr.getRequest().getSession().getAttribute("user");
		if (curUser == null) curUser = new UserVO();
		String bCode = curUser.getBusinessCode();
		Map<String, String> param = new HashMap<String, String>();
		param.put("bCode", bCode);
		
		DictWordVO dictWordVO = this.setDictWordVO(data);
		dictWordVO.setUpdateName(currentUser);
		String regPtn4DTRowId = "data\\[";
		String DT_RowId = data[1].split(regPtn4DTRowId)[1].split("]")[0];
		
		String oldWord = dictWordDAO.selectOneDictWord(DT_RowId).getWord();
		String oldBusinessCode = dictWordDAO.selectOneDictWord(DT_RowId).getBusinessCode();
		String oldVersion = dictWordDAO.selectOneDictWord(DT_RowId).getVersion();
		
		dictWordVO.setOldWord(oldWord);
		dictWordVO.setOldBusinessCode(oldBusinessCode);
		dictWordVO.setOldVersion(oldVersion);

		return dictWordDAO.updateDictWord(dictWordVO);
	}
	
	// 5. Remove
	@Override
	public int deleteDictWord(String[] data, String currentUser) {
		DictWordVO dictWordVO = this.setDictWordVO(data);
		//DictWordVO.setUpdateName(currentUser);
		String regPtn4DTRowId = "data\\[";
		String DT_RowId = data[1].split(regPtn4DTRowId)[1].split("]")[0];
		
		String oldWord = dictWordDAO.selectOneDictWord(DT_RowId).getWord();
		String oldBusinessCode = dictWordDAO.selectOneDictWord(DT_RowId).getBusinessCode();
		String oldVersion = dictWordDAO.selectOneDictWord(DT_RowId).getVersion();
		
		dictWordVO.setOldWord(oldWord);
		dictWordVO.setOldBusinessCode(oldBusinessCode);
		dictWordVO.setOldVersion(oldVersion);
		
		
		return dictWordDAO.deleteDictWord(dictWordVO);
	}
	
	// 6. SelectOne
	@Override
	public DictWordVO selectOneDictWord(String DT_RowId, String columnName, String newValue) {
		DictWordVO DictWordVO = dictWordDAO.selectOneDictWord(DT_RowId);
		
		if (columnName.equals("word")) {
			DictWordVO.setWord(newValue);
		}
		else if (columnName.equals("businessCode")) {
			DictWordVO.setBusinessCode(newValue);
		}
		else if (columnName.equals("version")) {
			DictWordVO.setVersion(newValue);
		}

		
		return DictWordVO;
	}
}
