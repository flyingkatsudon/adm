package com.bdp.adm.biz.impl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.bdp.adm.biz.EwdBiz;
import com.bdp.adm.dao.EwdDAO;
import com.bdp.adm.vo.EwdVO;
import com.bdp.adm.vo.UserVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Repository
public class EwdBizImpl implements EwdBiz {
	@Autowired
	private EwdDAO ewdDAO;
	
	@Override
	public EwdVO setEwdVO(String[] data) {
		// Regular Expression Pattern Definition
		String regPtn4Column = "data\\[[a-zA-Z0-9_]*\\]\\[";
		String regPtn4NewValue = "data\\[[a-zA-Z0-9_\\]\\[]*=";

		EwdVO ewdVO = new EwdVO();
		for(int i = 1; i < data.length; i++) {
			String columnName = data[i].split(regPtn4Column)[1].split("]")[0];
			
			if(data[i].split(regPtn4NewValue).length < 2) {
				continue;
			}
			
			if(columnName.equals("ewdIdx")) {
				int ewdIdx = Integer.parseInt(data[i].split(regPtn4NewValue)[1]);
				ewdVO.setEwdIdx(ewdIdx);
			}
			else if(columnName.equals("synsetId")) {
				String synset_id = data[i].split(regPtn4NewValue)[1];
				ewdVO.setSynsetId(synset_id);
			}
			else if(columnName.equals("wordId")) {
				String word_id = data[i].split(regPtn4NewValue)[1];
				ewdVO.setWordId(word_id);
			}
			else if(columnName.equals("businessCode")) {
				String business_code = data[i].split(regPtn4NewValue)[1];
				ewdVO.setBusinessCode(business_code);
			}
			else if(columnName.equals("word")) {
				String word = data[i].split(regPtn4NewValue)[1];
				ewdVO.setWord(word);
			}
			else if(columnName.equals("posInfo")) {
				String pos_info = data[i].split(regPtn4NewValue)[1];
				ewdVO.setPosInfo(pos_info);
			}
			else if(columnName.equals("attributeInfo")) {
				String attribute_info = data[i].split(regPtn4NewValue)[1];
				ewdVO.setAttributeInfo(attribute_info);
			}
			else if(columnName.equals("protoInfo")) {
				int proto_info = Integer.parseInt(data[i].split(regPtn4NewValue)[1]);
				ewdVO.setProtoInfo(proto_info);
			}
			else if(columnName.equals("domainInfo")) {
				String domain_info = data[i].split(regPtn4NewValue)[1];
				ewdVO.setDomainInfo(domain_info);
			}
			else if(columnName.equals("ontologyInfo")) {
				String ontology_info = data[i].split(regPtn4NewValue)[1];
				ewdVO.setOntologyInfo(ontology_info);
			}
			else if(columnName.equals("polarity")) {
				String polarity = data[i].split(regPtn4NewValue)[1];
				ewdVO.setPolarity(polarity);
			}
			else if(columnName.equals("preVal")) {
				int pre_val = Integer.parseInt(data[i].split(regPtn4NewValue)[1]);
				ewdVO.setPreVal(pre_val);
			}
			else if(columnName.equals("weight")) {
				double weight = Double.parseDouble(data[i].split(regPtn4NewValue)[1]);
				ewdVO.setWeight(weight);
			}
			else if(columnName.equals("zVal")) {
				double z_val = Double.parseDouble(data[i].split(regPtn4NewValue)[1]);
				ewdVO.setzVal(z_val);
			}
			else if(columnName.equals("sVal")) {
				double s_val = Double.parseDouble(data[i].split(regPtn4NewValue)[1]);
				ewdVO.setsVal(s_val);
			}
			else if(columnName.equals("delCd")) {
				String delCd = data[i].split(regPtn4NewValue)[1];
				if (delCd.equals("N")) delCd="";
				ewdVO.setDelCd(delCd);
			}
			else if(columnName.equals("version")) {
				double version = Double.parseDouble(data[i].split(regPtn4NewValue)[1]);
				ewdVO.setVersion(version);
			}

		}
		
		return ewdVO;
	}
	
	// 2. SelectAll
	@Override
	public String selectAllEwd(String action) throws JsonProcessingException {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		UserVO curUser = (UserVO)attr.getRequest().getSession().getAttribute("user");
		if (curUser == null) curUser = new UserVO();
		String bCode = curUser.getBusinessCode();
		Map<String, String> param = new HashMap<String, String>();
		param.put("action", action);
		param.put("bCode", bCode);
		
		List<EwdVO> ewdList = ewdDAO.selectAllEwd(param);
		List<EwdVO> emptyList = new ArrayList<EwdVO>();
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> ewdData = new HashMap<String, Object>();
		
		ewdData.put("data", ewdList);
		ewdData.put("options", emptyList);
		ewdData.put("files", emptyList);
		String ewdDataJSON="";
		
		ewdDataJSON = mapper.writeValueAsString(ewdData);
		
		return ewdDataJSON;
	}
	
	// 3. SelectOne
	@Override
	public EwdVO selectOneEwd(String DT_RowId, String columnName, String newValue) {
		EwdVO ewdVO = ewdDAO.selectOneEwd(DT_RowId);
		
		if(columnName.equals("synsetId")) {
			ewdVO.setSynsetId(newValue);
		}
		else if(columnName.equals("wordId")) {		
			ewdVO.setWordId(newValue);
		}
		else if(columnName.equals("businessCode")) {			
			ewdVO.setBusinessCode(newValue);
		}
		/*else if(columnName.equals("word")) {
			ewdVO.setWord(newValue);
		}
		else if(columnName.equals("posInfo")) {
			ewdVO.setPosInfo(newValue);
		}
		else if(columnName.equals("attributeInfo")) {
			ewdVO.setAttributeInfo(newValue);
		}
		else if(columnName.equals("protoInfo")) {
			ewdVO.setProtoInfo(Integer.parseInt(newValue));
		}
		else if(columnName.equals("domainInfo")) {
			ewdVO.setDomainInfo(newValue);
		}
		else if(columnName.equals("ontologyInfo")) {
			ewdVO.setOntologyInfo(newValue);
		}
		else if(columnName.equals("polarity")) {
			ewdVO.setPolarity(newValue);
		}
		else if(columnName.equals("preVal")) {
			ewdVO.setPreVal(Integer.parseInt(newValue));
		}
		else if(columnName.equals("weight")) {
			ewdVO.setWeight(Double.parseDouble(newValue));
		}
		else if(columnName.equals("zVal")) {
			ewdVO.setzVal(Double.parseDouble(newValue));
		}
		else if(columnName.equals("sVal")) {
			ewdVO.setsVal(Double.parseDouble(newValue));
		}
		else if(columnName.equals("version")) {
			ewdVO.setVersion(Double.parseDouble(newValue));
		}
		else if(columnName.equals("loadDate")) {
			ewdVO.setLoadDate(newValue);
		}
		else if(columnName.equals("updateDate")) {
			ewdVO.setUpdateDate(newValue);
		}*/
		
		return ewdVO;
	}

	// 4. Action: Create
	@Override
	public int insertEwd(String[] data, String currentUser) {
		EwdVO ewdVO = setEwdVO(data);
		ewdVO.setLoadName(currentUser);
		
		return ewdDAO.insertEwd(ewdVO);
	}
	
	// 5. Action: Edit
	@Override
	public int updateEwd(String[] data, String currentUser) {		
		EwdVO ewdVO = new EwdVO();
		ewdVO = setEwdVO(data);
		ewdVO.setUpdateName(currentUser);
		
		return ewdDAO.updateEwd(ewdVO);
	}

	// 6. Action: Remove	
	@Override
	public int deleteEwd(String[] data, String currentUser) {
		EwdVO ewdVO = setEwdVO(data);
		ewdVO.setUpdateName(currentUser);
		
		return ewdDAO.deleteEwd(ewdVO);
	}
}
