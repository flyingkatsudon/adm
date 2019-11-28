package com.bdp.adm.biz.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.bdp.adm.biz.TaxonomyBiz;
import com.bdp.adm.dao.TaxonomyDAO;
import com.bdp.adm.vo.TaxonomyVO;
import com.bdp.adm.vo.UserVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Repository
public class TaxonomyBizImpl implements TaxonomyBiz {

	@Autowired
	private TaxonomyDAO taxonomyDAO;
	
	@Override
	public TaxonomyVO setTaxonomyVO(String[] data) {
		String regPtn4Column = "data\\[[a-zA-Z0-9_]*\\]\\[";
		String regPtn4NewValue = "data\\[[a-zA-Z0-9_\\]\\[]*=";
	
		TaxonomyVO taxonomyVO = new TaxonomyVO();
		
		for(int i = 1; i < data.length; i++) {
			String columnName = data[i].split(regPtn4Column)[1].split("]")[0];
			
			//만약 필드에 비어있는 값이 있으면 for문 탈출
			if(data[i].split(regPtn4NewValue).length < 2) {
				continue;
			}
			
			if (columnName.equals("taxId")) {
				String tax_id = data[i].split(regPtn4NewValue)[1];
				taxonomyVO.setTaxId(tax_id);
			}
			else if (columnName.equals("taxIdx")) {
				int tax_idx = Integer.parseInt(data[i].split(regPtn4NewValue)[1]);
				taxonomyVO.setTaxIdx(tax_idx);
			}
			else if (columnName.equals("businessCode")) {
				String business_code = data[i].split(regPtn4NewValue)[1];
				taxonomyVO.setBusinessCode(business_code);	
			}
			else if (columnName.equals("version")) {
				String version = data[i].split(regPtn4NewValue)[1];
				taxonomyVO.setVersion(version);
			}
			else if (columnName.equals("level1")) {
				String level1 = data[i].split(regPtn4NewValue)[1];
				taxonomyVO.setLevel1(level1);
				
			}
			else if (columnName.equals("level2")) {
				String level2 = data[i].split(regPtn4NewValue)[1];
				taxonomyVO.setLevel2(level2);
				
			}
			else if (columnName.equals("level3")) {
				String level3 = data[i].split(regPtn4NewValue)[1];
				taxonomyVO.setLevel3(level3);
				
			}
			else if (columnName.equals("level4")) {
				String level4 = data[i].split(regPtn4NewValue)[1];
				taxonomyVO.setLevel4(level4);
				
			}
			else if (columnName.equals("refKeyword")) {
				String ref_keyword = data[i].split(regPtn4NewValue)[1];
				taxonomyVO.setRefKeyword(ref_keyword);
				
			}
			else if (columnName.equals("keyword")) {
				String keyword = data[i].split(regPtn4NewValue)[1];
				taxonomyVO.setKeyword(keyword);
			}
			else if (columnName.equals("delCd")) {
				String delCd = data[i].split(regPtn4NewValue)[1];
				if (delCd.equals("N")) delCd="";
				taxonomyVO.setDelCd(delCd);
			}
			else if (columnName.equals("description")) {
				String description = data[i].split(regPtn4NewValue)[1];
				taxonomyVO.setDescription(description);
			}
			else if (columnName.equals("updateDate")) {
				String update_date = data[i].split(regPtn4NewValue)[1];
				taxonomyVO.setUpdateDate(update_date);
				
			}
			else if (columnName.equals("DT_RowId")) {
				String dT_RowId = data[i].split(regPtn4NewValue)[1];
				taxonomyVO.setDTRowId(dT_RowId);
			}
		}

		return taxonomyVO;
	}
	
	// 2. SelectAll
	@Override
	public String selectAllTaxonomy(String action) throws JsonProcessingException {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		UserVO curUser = (UserVO)attr.getRequest().getSession().getAttribute("user");
		if (curUser == null) curUser = new UserVO();
		String bCode = curUser.getBusinessCode();
		Map<String, String> param = new HashMap<String, String>();
		param.put("action", action);
		param.put("bCode", bCode);
		
		List<TaxonomyVO> taxnomyList = taxonomyDAO.selectAllTaxonomy(param);
		List<TaxonomyVO> emptyList = new ArrayList<TaxonomyVO>();
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> taxonomyData = new HashMap<String, Object>();
		
		taxonomyData.put("data", taxnomyList);
		taxonomyData.put("options", emptyList);
		taxonomyData.put("files", emptyList);
		String taxonomyDataJSON="";
		
		taxonomyDataJSON = mapper.writeValueAsString(taxonomyData);
		
		return taxonomyDataJSON;
	}
	
	// 3. Insert
	@Override
	public int insertTaxonomy(String[] data, String currentUser) {
		TaxonomyVO taxonomyVO = this.setTaxonomyVO(data);
		taxonomyVO.setLoadName(currentUser);
		
		return taxonomyDAO.insertTaxonomy(taxonomyVO);
	}
	
	// 4. Update
	@Override
	public int updateTaxonomy(String[] data, String currentUser) {
		TaxonomyVO taxonomyVO = new TaxonomyVO();
		taxonomyVO = this.setTaxonomyVO(data);
		taxonomyVO.setUpdateName(currentUser);
	
		return taxonomyDAO.updateTaxonomy(taxonomyVO);
	}
	
	// 5. Remove
	@Override
	public int deleteTaxonomy(String[] data, String currentUser) {
		TaxonomyVO taxonomyVO = this.setTaxonomyVO(data);
		taxonomyVO.setUpdateName(currentUser);
		
		return taxonomyDAO.deleteTaxonomy(taxonomyVO);
	}
	
	// 6. SelectOne
	@Override
	public TaxonomyVO selectOneTaxonomy(String DT_RowId, String columnName, String newValue) {		
		TaxonomyVO taxonomyVO = taxonomyDAO.selectOneTaxonomy(DT_RowId);
		System.out.println(taxonomyVO);
	
		//수정된 행, 새로운 값을 다시 set해서 
		if (columnName.equals("taxId")) {
			taxonomyVO.setTaxId(newValue);
		}
		else if (columnName.equals("taxIdx")) {
			taxonomyVO.setTaxIdx(Integer.parseInt(newValue));
		}
		else if (columnName.equals("businessCode")) {
			taxonomyVO.setBusinessCode(newValue);
		}
		else if (columnName.equals("version")) {
			taxonomyVO.setVersion(newValue);
		}
		else if (columnName.equals("level1")) {
			taxonomyVO.setLevel1(newValue);
		} 
		else if (columnName.equals("level2")) {
			taxonomyVO.setLevel2(newValue);
		}
		else if (columnName.equals("level3")) {
			taxonomyVO.setLevel3(newValue);
		}
		else if (columnName.equals("level4")) {
			taxonomyVO.setLevel4(newValue);
		}
		else if (columnName.equals("refKeyword")) {
			taxonomyVO.setRefKeyword(newValue);
		}
		else if (columnName.equals("keyword")) {
			taxonomyVO.setKeyword(newValue);
		}
		
		return taxonomyVO;
	}

	@Override
	public String selectTaxonomyTree() throws JsonProcessingException {
		List<TaxonomyVO> taxnomyTreeList = taxonomyDAO.selectTaxonomyTree();

		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> taxonomyTreeData = new HashMap<String, Object>();
		taxonomyTreeData.put("rawdata", taxnomyTreeList);
		String taxonomyTreeDataJSON="";
		taxonomyTreeDataJSON = mapper.writeValueAsString(taxonomyTreeData);
		
		return taxonomyTreeDataJSON;
	}
}
