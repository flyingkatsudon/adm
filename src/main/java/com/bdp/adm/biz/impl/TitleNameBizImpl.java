package com.bdp.adm.biz.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bdp.adm.biz.TitleNameBiz;
import com.bdp.adm.dao.TitleNameDAO;
import com.bdp.adm.vo.ScreenMasterVO;
import com.bdp.adm.vo.TitleNameVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Repository
public class TitleNameBizImpl implements TitleNameBiz {

	@Autowired
	private TitleNameDAO titleNameDAO;
	
	@Override
	public TitleNameVO setTitleNameVO(String[] data) {
		String regPtn4Column = "data\\[[a-zA-Z0-9_]*\\]\\[";
		String regPtn4NewValue = "data\\[[a-zA-Z0-9_\\]\\[]*=";
	
		TitleNameVO titleNameVO = new TitleNameVO();
		
		for(int i = 1; i < data.length; i++) {
			String columnName = data[i].split(regPtn4Column)[1].split("]")[0];
			
			if(data[i].split(regPtn4NewValue).length < 2) {
				continue;
			}
			
			if (columnName.equals("titleIdx")) {
				int titleIdx = Integer.parseInt(data[i].split(regPtn4NewValue)[1]);
				titleNameVO.setTitleIdx(titleIdx);
			}
			else if (columnName.equals("userTitleCd")) {
				String userTitleCd = data[i].split(regPtn4NewValue)[1];
				titleNameVO.setUserTitleCd(userTitleCd);
			}
			else if (columnName.equals("userTitleName")) {
				String userTitleName = data[i].split(regPtn4NewValue)[1];
				titleNameVO.setUserTitleName(userTitleName);
			}
			else if (columnName.equals("delCd")) {
				String delCd = data[i].split(regPtn4NewValue)[1];
				if (delCd.equals("N")) delCd="";
				titleNameVO.setDelCd(delCd);
			}
			else if (columnName.equals("loadDate")) {
				String loadDate = data[i].split(regPtn4NewValue)[1];
				titleNameVO.setLoadDate(loadDate);
			}
			else if (columnName.equals("loadTime")) {
				String loadTime = data[i].split(regPtn4NewValue)[1];
				titleNameVO.setLoadTime(loadTime);
			}
			else if (columnName.equals("loadName")) {
				String loadName = data[i].split(regPtn4NewValue)[1];
				titleNameVO.setLoadName(loadName);
			}
			else if (columnName.equals("updateDate")) {
				String updateDate = data[i].split(regPtn4NewValue)[1];
				titleNameVO.setUpdateDate(updateDate);
			}
			else if (columnName.equals("updateTime")) {
				String updateTime = data[i].split(regPtn4NewValue)[1];
				titleNameVO.setUpdateTime(updateTime);
			}
			else if (columnName.equals("updateName")) {
				String updateName = data[i].split(regPtn4NewValue)[1];
				titleNameVO.setUpdateName(updateName);
			}
		}

		return titleNameVO;
	}
	
	// 2. SelectAll
	@Override
	public String selectAllTitleName(String action) throws JsonProcessingException {
		List<TitleNameVO> titleNameList = titleNameDAO.selectAllTitleName(action);
		List<TitleNameVO> emptyList = new ArrayList<TitleNameVO>();
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> titleNameData = new HashMap<String, Object>();
		
		titleNameData.put("data", titleNameList);
		titleNameData.put("options", emptyList);
		titleNameData.put("files", emptyList);
		String titleNameDataJSON="";
		
		titleNameDataJSON = mapper.writeValueAsString(titleNameData);
		
		return titleNameDataJSON;
	}
	
	// 3. Insert
	@Override
	public int insertTitleName(String[] data, String currentUser) {
		TitleNameVO titleNameVO = this.setTitleNameVO(data);
		titleNameVO.setLoadName(currentUser);
		
		return titleNameDAO.insertTitleName(titleNameVO);
	}
	
	// 4. Update
	@Override
	public int updateTitleName(String[] data, String currentUser) {
		TitleNameVO titleNameVO = new TitleNameVO();
		titleNameVO = this.setTitleNameVO(data);
		titleNameVO.setUpdateName(currentUser);

		return titleNameDAO.updateTitleName(titleNameVO);
	}
	
	// 5. Remove
	@Override
	public int deleteTitleName(String[] data, String currentUser) {
		TitleNameVO titleNameVO = this.setTitleNameVO(data);
		titleNameVO.setUpdateName(currentUser);
		
		return titleNameDAO.deleteTitleName(titleNameVO);
	}
	
	// 6. SelectOne
	@Override
	public TitleNameVO selectOneTitleName(String DT_RowId, String columnName, String newValue) {
		
		TitleNameVO titleNameVO = titleNameDAO.selectOneTitleName(DT_RowId);
		
		//수정된 행, 새로운 값을 다시 set해서 
		if (columnName.equals("userId")) {
			newValue = StringUtils.leftPad(newValue, 10, "0");
			titleNameVO.setUserId(newValue);
		}
		else if (columnName.equals("userTitleCd")) {
			titleNameVO.setUserTitleCd(newValue);
		}
		else if (columnName.equals("userTitleName")) {
			titleNameVO.setUserTitleName(newValue);
		}
		else if (columnName.equals("delCd")) {
			titleNameVO.setDelCd(newValue);
		}
		else if (columnName.equals("loadDate")) {
			titleNameVO.setLoadDate(newValue);
		}
		else if (columnName.equals("loadTime")) {
			titleNameVO.setLoadTime(newValue);
		}
		else if (columnName.equals("loadName")) {
			titleNameVO.setLoadName(newValue);
		}
		else if (columnName.equals("updateDate")) {
			titleNameVO.setUpdateDate(newValue);
		}
		else if (columnName.equals("updateTime")) {
			titleNameVO.setUpdateTime(newValue);
		}
		else if (columnName.equals("updateName")) {
			titleNameVO.setUpdateName(newValue);
		}
		
		System.out.println(titleNameVO);
		return titleNameVO;
	}
}
