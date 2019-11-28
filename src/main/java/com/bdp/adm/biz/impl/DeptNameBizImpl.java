package com.bdp.adm.biz.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bdp.adm.biz.DeptNameBiz;
import com.bdp.adm.dao.DeptNameDAO;
import com.bdp.adm.vo.DeptNameVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Repository
public class DeptNameBizImpl implements DeptNameBiz {

	@Autowired
	private DeptNameDAO deptNameDAO;
	
	@Override
	public DeptNameVO setDeptNameVO(String[] data) {
		String regPtn4Column = "data\\[[a-zA-Z0-9_]*\\]\\[";
		String regPtn4NewValue = "data\\[[a-zA-Z0-9_\\]\\[]*=";
	
		DeptNameVO deptNameVO = new DeptNameVO();
		
		for(int i = 1; i < data.length; i++) {
			String columnName = data[i].split(regPtn4Column)[1].split("]")[0];
			
			if(data[i].split(regPtn4NewValue).length < 2) {
				continue;
			}
			
			if (columnName.equals("deptIdx")) {
				int deptIdx = Integer.parseInt(data[i].split(regPtn4NewValue)[1]);
				deptNameVO.setDeptIdx(deptIdx);
			}
			else if (columnName.equals("userDeptCd")) {
				String userDeptCd = data[i].split(regPtn4NewValue)[1];
				deptNameVO.setUserDeptCd(userDeptCd);
			}
			else if (columnName.equals("userDeptName")) {
				String userDeptName = data[i].split(regPtn4NewValue)[1];
				deptNameVO.setUserDeptName(userDeptName);
			}
			else if (columnName.equals("delCd")) {
				String delCd = data[i].split(regPtn4NewValue)[1];
				if (delCd.equals("N")) delCd="";
				deptNameVO.setDelCd(delCd);
			}
			else if (columnName.equals("loadDate")) {
				String loadDate = data[i].split(regPtn4NewValue)[1];
				deptNameVO.setLoadDate(loadDate);
			}
			else if (columnName.equals("loadTime")) {
				String loadTime = data[i].split(regPtn4NewValue)[1];
				deptNameVO.setLoadTime(loadTime);
			}
			else if (columnName.equals("loadName")) {
				String loadName = data[i].split(regPtn4NewValue)[1];
				deptNameVO.setLoadName(loadName);
			}
			else if (columnName.equals("updateDate")) {
				String updateDate = data[i].split(regPtn4NewValue)[1];
				deptNameVO.setUpdateDate(updateDate);
			}
			else if (columnName.equals("updateTime")) {
				String updateTime = data[i].split(regPtn4NewValue)[1];
				deptNameVO.setUpdateTime(updateTime);
			}
			else if (columnName.equals("updateName")) {
				String updateName = data[i].split(regPtn4NewValue)[1];
				deptNameVO.setUpdateName(updateName);
			}
		}

		return deptNameVO;
	}
	
	// 2. SelectAll
	@Override
	public String selectAllDeptName(String action) throws JsonProcessingException {
		List<DeptNameVO> deptNameList = deptNameDAO.selectAllDeptName(action);
		List<DeptNameVO> emptyList = new ArrayList<DeptNameVO>();
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> deptNameData = new HashMap<String, Object>();
		
		deptNameData.put("data", deptNameList);
		deptNameData.put("options", emptyList);
		deptNameData.put("files", emptyList);
		String deptNameDataJSON="";
		
		deptNameDataJSON = mapper.writeValueAsString(deptNameData);
		
		return deptNameDataJSON;
	}
	
	// 3. Insert
	@Override
	public int insertDeptName(String[] data, String currentUser) {
		DeptNameVO deptNameVO = this.setDeptNameVO(data);
		deptNameVO.setLoadName(currentUser);
		
		return deptNameDAO.insertDeptName(deptNameVO);
	}
	
	// 4. Update
	@Override
	public int updateDeptName(String[] data, String currentUser) {
		DeptNameVO deptNameVO = new DeptNameVO();
		deptNameVO = this.setDeptNameVO(data);
		deptNameVO.setUpdateName(currentUser);

		return deptNameDAO.updateDeptName(deptNameVO);
	}
	
	// 5. Remove
	@Override
	public int deleteDeptName(String[] data, String currentUser) {
		
		DeptNameVO deptNameVO = this.setDeptNameVO(data);
		deptNameVO.setUpdateName(currentUser);
		
		return deptNameDAO.deleteDeptName(deptNameVO);
	}
	

}
