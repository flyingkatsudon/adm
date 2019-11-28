package com.bdp.adm.biz.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.bdp.adm.biz.DocFilterBiz;
import com.bdp.adm.dao.DocFilterDAO;
import com.bdp.adm.vo.DocFilterVO;
import com.bdp.adm.vo.UserVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Repository
public class DocFilterBizImpl implements DocFilterBiz {
	@Autowired
	private DocFilterDAO docFilterDAO;
	
	@Override
	public DocFilterVO setDocFilterVO(String[] data) {
		// Regular Expression Pattern Definition
		String regPtn4Column = "data\\[[a-zA-Z0-9_]*\\]\\[";
		String regPtn4NewValue = "data\\[[a-zA-Z0-9_\\]\\[]*=";

		DocFilterVO docFilterVO = new DocFilterVO();
		for(int i = 1; i < data.length; i++) {
			String columnName = data[i].split(regPtn4Column)[1].split("]")[0];
			
			//만약 필드에 비어있는 값이 있으면 for문 탈출c
			if(data[i].split(regPtn4NewValue).length < 2) {
				continue;
			}
			
			if(columnName.equals("filterIdx")) {
				int filter_idx = Integer.parseInt(data[i].split(regPtn4NewValue)[1]);
				docFilterVO.setFilterIdx(filter_idx);
			}
			else if(columnName.equals("businessCode")) {
				String business_code = data[i].split(regPtn4NewValue)[1];
				docFilterVO.setBusinessCode(business_code);
			}
			else if(columnName.equals("categoryCode")) {
				String categoryCode = data[i].split(regPtn4NewValue)[1];
				docFilterVO.setCategoryCode(categoryCode);
			}
			else if(columnName.equals("level")) {
				int level = Integer.parseInt(data[i].split(regPtn4NewValue)[1]);
				docFilterVO.setLevel(level);
			}
			else if(columnName.equals("metaConcept")) {
				String meta_concept = data[i].split(regPtn4NewValue)[1];
				docFilterVO.setMetaConcept(meta_concept);
			}
			else if(columnName.equals("filterWord")) {
				String filter_word = data[i].split(regPtn4NewValue)[1];
				docFilterVO.setFilterWord(filter_word);
			}
			else if(columnName.equals("andWord")) {
				String and_word = data[i].split(regPtn4NewValue)[1];
				docFilterVO.setAndWord(and_word);
			}
			else if(columnName.equals("notWord")) {
				String not_word = data[i].split(regPtn4NewValue)[1];
				docFilterVO.setNotWord(not_word);
			}
			else if(columnName.equals("delCd")) {
				String delCd = data[i].split(regPtn4NewValue)[1];
				if (delCd.equals("N")) delCd="";
				docFilterVO.setDelCd(delCd);
			}
			else if(columnName.equals("version")) {
				String version = data[i].split(regPtn4NewValue)[1];
				docFilterVO.setVersion(version);
			}

		}
		
		return docFilterVO;
	}
	
	// 2. SelectAll
	@Override
	public String selectAllDocFilter(String action) throws JsonProcessingException {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		UserVO curUser = (UserVO)attr.getRequest().getSession().getAttribute("user");
		if (curUser == null) curUser = new UserVO();
		String bCode = curUser.getBusinessCode();
		Map<String, String> param = new HashMap<String, String>();
		param.put("action", action);
		param.put("bCode", bCode);
		
		List<DocFilterVO> docFilterList = docFilterDAO.selectAllDocFilter(param);
		List<DocFilterVO> emptyList = new ArrayList<DocFilterVO>();
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> docFilterData = new HashMap<String, Object>();
		
				
		docFilterData.put("data", docFilterList);
		docFilterData.put("options", emptyList);
		docFilterData.put("files", emptyList);
		String docFilterDataJSON="";
		
		docFilterDataJSON = mapper.writeValueAsString(docFilterData);
		
		return docFilterDataJSON;
	}
	
	// 3. SelectOne
	@Override
	public DocFilterVO selectOneDocFilter(String DT_RowId, String columnName, String newValue) {
		DocFilterVO docFilterVO = docFilterDAO.selectOneDocFilter(DT_RowId);
		
		if(columnName.equals("filterIdx")) {
			docFilterVO.setFilterIdx(Integer.parseInt(newValue));
		}
		else if(columnName.equals("businessCode")) {
			docFilterVO.setBusinessCode(newValue);
		}
		else if(columnName.equals("metaConcept")) {
			docFilterVO.setMetaConcept(newValue);
		}
		else if(columnName.equals("filterWord")) {
			docFilterVO.setFilterWord(newValue);
		}
		else if(columnName.equals("andWord")) {
			docFilterVO.setAndWord(newValue);
		}
		else if(columnName.equals("notWord")) {
			docFilterVO.setNotWord(newValue);
		}
		else if(columnName.equals("version")) {
			docFilterVO.setVersion(newValue);
		}
		/*else if(columnName.equals("load_date")) {
			docFilterVO.setLoad_date(newValue);
		}*/
		else if(columnName.equals("updateDate")) {
			docFilterVO.setUpdateDate(newValue);
		}
		
		return docFilterVO;
	}

	// 4. Action: Create
	@Override
	public int insertDocFilter(String[] data, String currentUser) {
		DocFilterVO docFilterVO = this.setDocFilterVO(data);
		docFilterVO.setLoadName(currentUser);
		
		return docFilterDAO.insertDocFilter(docFilterVO);
	}
	
	// 5. Action: Edit
	@Override
	public int updataeDocFilter(String[] data, String currentUser) {		
		DocFilterVO docFilterVO = new DocFilterVO();
		docFilterVO = this.setDocFilterVO(data);
		docFilterVO.setUpdateName(currentUser);

		return docFilterDAO.updateDocFilter(docFilterVO);
	}

	// 6. Action: Remove	
	@Override
	public int deleteDocFilter(String[] data, String currentUser) {
		DocFilterVO docFilterVO = this.setDocFilterVO(data);
		docFilterVO.setUpdateName(currentUser);
		
		return docFilterDAO.deleteDocFilter(docFilterVO);
	}
}
