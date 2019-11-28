package com.bdp.adm.biz.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bdp.adm.biz.ScreenLoginBiz;
import com.bdp.adm.dao.ScreenLoginDAO;
import com.bdp.adm.vo.ScreenLoginVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

//@Service
@Repository
public class ScreenLoginBizImpl implements ScreenLoginBiz {

	@Autowired
	private ScreenLoginDAO screenLoginDAO;
	
	@Override
	public ScreenLoginVO setScreenLoginVO(String[] data) {
		String regPtn4Column = "data\\[[a-zA-Z0-9_]*\\]\\[";
		String regPtn4NewValue = "data\\[[a-zA-Z0-9_\\]\\[]*=";
	
		ScreenLoginVO screenLoginVO = new ScreenLoginVO();
		
		for(String i:data) {
			System.out.println(i);
		}
		
		for(int i = 1; i < data.length; i++) {
			String columnName = data[i].split(regPtn4Column)[1].split("]")[0];
			
			//만약 필드에 비어있는 값이 있으면 for문 탈출c
			if(data[i].split(regPtn4NewValue).length < 2) {
				continue;
			}
			
			if (columnName.equals("screenNo")) {
				String screenNo = data[i].split(regPtn4NewValue)[1];
				screenLoginVO.setScreenNo(screenNo);
			}
			else if (columnName.equals("screenLoginUser")) {
				String screenLoginUser = data[i].split(regPtn4NewValue)[1];
				screenLoginVO.setScreenLoginUser(screenLoginUser);
			}
			else if (columnName.equals("screenLoginTime")) {
				String screenLoginTime = data[i].split(regPtn4NewValue)[1];
				screenLoginVO.setScreenLoginTime(screenLoginTime);
			}
			else if (columnName.equals("delCd")) {
				String delCd = data[i].split(regPtn4NewValue)[1];
				if (delCd.equals("N")) delCd="";
				screenLoginVO.setDelCd(delCd);
			}
			else if (columnName.equals("loadDate")) {
				String loadDate = data[i].split(regPtn4NewValue)[1];
				screenLoginVO.setLoadDate(loadDate);
			}
			else if (columnName.equals("loadTime")) {
				String loadTime = data[i].split(regPtn4NewValue)[1];
				screenLoginVO.setLoadTime(loadTime);
			}
			else if (columnName.equals("loadName")) {
				String loadName = data[i].split(regPtn4NewValue)[1];
				screenLoginVO.setLoadName(loadName);
			}
			else if (columnName.equals("updateDate")) {
				String updateDate = data[i].split(regPtn4NewValue)[1];
				screenLoginVO.setUpdateDate(updateDate);
			}
			else if (columnName.equals("updateTime")) {
				String updateTime = data[i].split(regPtn4NewValue)[1];
				screenLoginVO.setUpdateTime(updateTime);
			}
			else if (columnName.equals("updateName")) {
				String updateName = data[i].split(regPtn4NewValue)[1];
				screenLoginVO.setUpdateName(updateName);
			}
		}

		return screenLoginVO;
	}
	
	// 2. SelectAll
	@Override
	public String selectAllScreenLogin() throws JsonProcessingException {
		List<ScreenLoginVO> screenLoginList = screenLoginDAO.selectAllScreenLogin();
		List<ScreenLoginVO> emptyList = new ArrayList<ScreenLoginVO>();
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> screenLoginData = new HashMap<String, Object>();
		
		screenLoginData.put("data", screenLoginList);
		screenLoginData.put("options", emptyList);
		screenLoginData.put("files", emptyList);
		String screenLoginDataJSON="";
		
		screenLoginDataJSON = mapper.writeValueAsString(screenLoginData);
		
		return screenLoginDataJSON;
	}
	
	// 3. Insert
	@Override
	public int insertScreenLogin(String[] data, String currentUser) {
		ScreenLoginVO screenLoginVO = this.setScreenLoginVO(data);
		screenLoginVO.setLoadName(currentUser);
		screenLoginVO.setUpdateName(currentUser);
		
		return screenLoginDAO.insertScreenLogin(screenLoginVO);
	}
	
	// 4. Update
	@Override
	public int updateScreenLogin(String[] data, String currentUser) {
		ScreenLoginVO screenLoginVO = new ScreenLoginVO();
		
		String regPtn4DTRowId = "data\\[";
		String DT_RowId = data[1].split(regPtn4DTRowId)[1].split("]")[0];
		
		String oldScreenNo = screenLoginDAO.selectOneScreenLogin(DT_RowId).getScreenNo();
		
		if(data.length == 2) {
			System.out.println("### Inline Edit");
			String regPtn4Column = "data\\[[a-zA-Z0-9_]*\\]\\[";
			String regPtn4NewValue = "data\\[[a-zA-Z0-9_\\]\\[]*=";
			//String regPtn4DTRowId = "data\\[";
			
			String columnName = data[1].split(regPtn4Column)[1].split("]")[0];
			//String newValue = data[1].split(regPtn4NewValue)[1];
			//String DT_RowId = data[1].split(regPtn4DTRowId)[1].split("]")[0];
			String newValue = (data[1].split(regPtn4NewValue).length < 2) ? null : data[1].split(regPtn4NewValue)[1];
			screenLoginVO = this.selectOneScreenLogin(DT_RowId, columnName, newValue);
		}
		else {
			System.out.println("### Button Edit");
			screenLoginVO = this.setScreenLoginVO(data);
		}
		screenLoginVO.setOldScreenNo(oldScreenNo);
		screenLoginVO.setUpdateName(currentUser);
		
		return screenLoginDAO.updateScreenLogin(screenLoginVO);
	}
	
	// 5. Remove
	@Override
	public int deleteScreenLogin(String[] data, String currentUser) {
		ScreenLoginVO screenLoginVO = this.setScreenLoginVO(data);
		screenLoginVO.setUpdateName(currentUser);
		
		return screenLoginDAO.deleteScreenLogin(screenLoginVO);
	}
	
	// 6. SelectOne
	@Override
	public ScreenLoginVO selectOneScreenLogin(String DT_RowId, String columnName, String newValue) {
		
		System.out.println("selectOne IN!!!"+DT_RowId+":"+columnName+":"+newValue);
		
		ScreenLoginVO screenLoginVO = screenLoginDAO.selectOneScreenLogin(DT_RowId);
		
		//수정된 행, 새로운 값을 다시 set해서 
		if (columnName.equals("screenNo")) {
			screenLoginVO.setScreenNo(newValue);
		}
		else if (columnName.equals("screenLoginUser")) {
			screenLoginVO.setScreenLoginUser(newValue);
		}
		else if (columnName.equals("screenLoginTime")) {
			screenLoginVO.setScreenLoginTime(newValue);
		}
		else if (columnName.equals("delCd")) {
			screenLoginVO.setDelCd(newValue);
		}
		else if (columnName.equals("loadDate")) {
			screenLoginVO.setLoadDate(newValue);
		}
		else if (columnName.equals("loadTime")) {
			screenLoginVO.setLoadTime(newValue);
		}
		else if (columnName.equals("loadName")) {
			screenLoginVO.setLoadName(newValue);
		}
		else if (columnName.equals("updateDate")) {
			screenLoginVO.setUpdateDate(newValue);
		}
		else if (columnName.equals("updateTime")) {
			screenLoginVO.setUpdateTime(newValue);
		}
		else if (columnName.equals("updateName")) {
			screenLoginVO.setUpdateName(newValue);
		}
		
		System.out.println(screenLoginVO);
		return screenLoginVO;
	}
}
