package com.bdp.adm.biz.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bdp.adm.biz.TknDictBiz;
import com.bdp.adm.dao.TknDictDAO;
import com.bdp.adm.vo.EwdVO;
import com.bdp.adm.vo.TknDictVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Repository
public class TknDictBizImpl implements TknDictBiz {
	
	@Autowired
	private TknDictDAO tknDictDAO;
	
	@Override
	public TknDictVO setTknDictVO(String[] data) {
		
		// Regular Expression Pattern Definition
		String regPtn4Column = "data\\[[a-zA-Z0-9_]*\\]\\[";
		String regPtn4NewValue = "data\\[[a-zA-Z0-9_\\]\\[]*=";
			
		// Data --> tknDictVO
		TknDictVO tknDictVO = new TknDictVO();
		for(int i = 1; i < data.length; i++) {
			String columnName = data[i].split(regPtn4Column)[1].split("]")[0];
			
			//만약 필드에 비어있는 값이 있으면 for문 탈출
			if(data[i].split(regPtn4NewValue).length < 2) {
				continue;
			}
			
			if(columnName.equals("wordId")) {
				String word_id = data[i].split(regPtn4NewValue)[1];
				tknDictVO.setWordId(word_id);
			}
			else if(columnName.equals("word")) {
				String word = data[i].split(regPtn4NewValue)[1];
				tknDictVO.setWord(word);
			}
			else if(columnName.equals("businessCode")) {
				String business_code = data[i].split(regPtn4NewValue)[1];
				tknDictVO.setBusinessCode(business_code);
			}
			else if(columnName.equals("cost")) {
				String cost = data[i].split(regPtn4NewValue)[1];
				tknDictVO.setCost(cost);
			}
			else if(columnName.equals("posInfo")) {
				String pos_info = data[i].split(regPtn4NewValue)[1];
				tknDictVO.setPosInfo(pos_info);
			}
			else if(columnName.equals("nerInfo")) {
				String ner_info = data[i].split(regPtn4NewValue)[1];
				tknDictVO.setNerInfo(ner_info);
			}
			else if(columnName.equals("finSyllable")) {
				String fin_syllable = data[i].split(regPtn4NewValue)[1];
				tknDictVO.setFinSyllable(fin_syllable);
			}
			else if(columnName.equals("aliasList")) {
				String alias_list = data[i].split(regPtn4NewValue)[1];
				tknDictVO.setAliasList(alias_list);
			}
			else if(columnName.equals("cmpndInfo")) {
				String cmpnd_info = data[i].split(regPtn4NewValue)[1];
				tknDictVO.setCmpndInfo(cmpnd_info);
			}
			else if(columnName.equals("addInfo1")) {
				String add_info1 = data[i].split(regPtn4NewValue)[1];
				tknDictVO.setAddInfo1(add_info1);
			}
			else if(columnName.equals("addInfo2")) {
				String add_info2 = data[i].split(regPtn4NewValue)[1];
				tknDictVO.setAddInfo2(add_info2);
			}
			else if(columnName.equals("cmpndSummary")) {
				String cmpnd_summary = data[i].split(regPtn4NewValue)[1];
				tknDictVO.setCmpndSummary(cmpnd_summary);
			}
			else if(columnName.equals("cmpndDetail")) {
				String cmpnd_detail = data[i].split(regPtn4NewValue)[1];
				tknDictVO.setCmpndDetail(cmpnd_detail);
			}
			else if(columnName.equals("version")) {
				String version = data[i].split(regPtn4NewValue)[1];
				tknDictVO.setVersion(version);
			}
			/*else if(columnName.equals("load_date")) {
				String load_date = data[i].split(regPtn4NewValue)[1];
				tknDictVO.setLoad_date(load_date);
			}*/
			else if(columnName.equals("updateDate")) {
				String update_date = data[i].split(regPtn4NewValue)[1];
				tknDictVO.setUpdateDate(update_date);
			}
		}
		return tknDictVO;
	}
	
	// 2. SelectAll
	@Override
	public String selectAllTknDict() throws JsonProcessingException {
		List<TknDictVO> tknDictList = tknDictDAO.selectAllTknDict();
		List<EwdVO> emptyList = new ArrayList<EwdVO>();
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> tknDictData = new HashMap<String, Object>();
		
		tknDictData.put("data", tknDictList);
		tknDictData.put("options", emptyList);
		tknDictData.put("files", emptyList);
		String tknDictDataJSON="";
		
		tknDictDataJSON = mapper.writeValueAsString(tknDictData);
		System.out.println(tknDictDataJSON);
		return tknDictDataJSON;
	}

	// 3. SelectOne
	@Override
	public TknDictVO selectOneTknDict(String DT_RowId, String columnName, String newValue) {
		TknDictVO tknDictVO = tknDictDAO.selectOneTknDict(DT_RowId);
		
		if(columnName.equals("wordId")) {
			tknDictVO.setWordId(newValue);
		}
		else if(columnName.equals("word")) {
			tknDictVO.setWord(newValue);
		}
		else if(columnName.equals("businessCode")) {
			tknDictVO.setBusinessCode(newValue);
		}
		else if(columnName.equals("cost")) {
			tknDictVO.setCost(newValue);
		}
		else if(columnName.equals("posInfo")) {
			tknDictVO.setPosInfo(newValue);
		}
		else if(columnName.equals("nerInfo")) {
			tknDictVO.setNerInfo(newValue);
		}
		else if(columnName.equals("finSyllable")) {
			tknDictVO.setFinSyllable(newValue);
		}
		else if(columnName.equals("aliasList")) {
			tknDictVO.setAliasList(newValue);
		}
		else if(columnName.equals("cmpndInfo")) {
			tknDictVO.setCmpndInfo(newValue);
		}
		else if(columnName.equals("addInfo1")) {
			tknDictVO.setAddInfo1(newValue);
		}
		else if(columnName.equals("addInfo2")) {
			tknDictVO.setAddInfo2(newValue);
		}
		else if(columnName.equals("cmpndSummary")) {
			tknDictVO.setCmpndSummary(newValue);
		}
		else if(columnName.equals("cmpndDetail")) {
			tknDictVO.setCmpndDetail(newValue);
		}
		else if(columnName.equals("version")) {
			tknDictVO.setVersion(newValue);
		}
		/*else if(columnName.equals("load_date")) {
			tknDictVO.setLoad_date(newValue);
		}*/
		else if(columnName.equals("updateDate")) {
			tknDictVO.setUpdateDate(newValue);
		}
		
		return tknDictVO;
	}
	
	// Action: Create
	@Override
	public int insertTknDict(String[] data) {
		TknDictVO tknDictVO = this.setTknDictVO(data);
		
		return tknDictDAO.insertTknDict(tknDictVO);
	}
	
	// Action: Edit
	@Override
	public int updataeTknDict(String[] data) {
		TknDictVO tknDictVO = new TknDictVO();
		
		String regPtn4DTRowId = "data\\[";
		String DT_RowId = data[1].split(regPtn4DTRowId)[1].split("]")[0];
		
		String oldWordId = tknDictDAO.selectOneTknDict(DT_RowId).getWordId();
		String oldWord = tknDictDAO.selectOneTknDict(DT_RowId).getWord();
		String oldBusinessCode = tknDictDAO.selectOneTknDict(DT_RowId).getBusinessCode();
		String oldVersion = tknDictDAO.selectOneTknDict(DT_RowId).getVersion();
		
		if(data.length == 2) {
			System.out.println("### Inline Edit");
			String regPtn4Column = "data\\[[a-zA-Z0-9_]*\\]\\[";
			String regPtn4NewValue = "data\\[[a-zA-Z0-9_\\]\\[]*=";
			//String regPtn4DTRowId = "data\\[";
			
			String columnName = data[1].split(regPtn4Column)[1].split("]")[0];
			//String newValue = data[1].split(regPtn4NewValue)[1];
			//String DT_RowId = data[1].split(regPtn4DTRowId)[1].split("]")[0];
			String newValue = (data[1].split(regPtn4NewValue).length < 2) ? null : data[1].split(regPtn4NewValue)[1];
			tknDictVO = this.selectOneTknDict(DT_RowId, columnName, newValue); // ewdVO for Inline Edit
		}
		else {
			System.out.println("### Button Edit");
			tknDictVO = this.setTknDictVO(data); // ewdVO for Button Edit
		}
		
		tknDictVO.setOldBusinessCode(oldBusinessCode);
		tknDictVO.setOldWord(oldWord);
		tknDictVO.setOldWordId(oldWordId);
		tknDictVO.setOldVersion(oldVersion);
		
		return tknDictDAO.updateTknDict(tknDictVO);
	}
	
	// Action: Remove
	@Override
	public int deleteTknDict(String[] data) {
		TknDictVO tknDictVO = this.setTknDictVO(data);
		
		return tknDictDAO.deleteTknDict(tknDictVO);
	}
}
