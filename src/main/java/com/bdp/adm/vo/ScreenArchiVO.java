package com.bdp.adm.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ScreenArchiVO {
	@JsonProperty("DT_RowId")
	private String DTRowId;
	
	private int archiIdx;
	private String screenNo;
	private String screenArchi;
	private String delCd;
	private String loadDate;
	private String loadTime;
	private String loadName;
	private String updateDate;
	private String updateTime;
	private String updateName;
	
	public String getDTRowId() {
		return DTRowId;
	}
	public void setDTRowId(String dTRowId) {
		DTRowId = dTRowId;
	}
	public int getArchiIdx() {
		return archiIdx;
	}
	public void setArchiIdx(int archiIdx) {
		this.archiIdx = archiIdx;
	}
	public String getScreenNo() {
		return screenNo;
	}
	public void setScreenNo(String screenNo) {
		this.screenNo = screenNo;
	}
	public String getScreenArchi() {
		return screenArchi;
	}
	public void setScreenArchi(String screenArchi) {
		this.screenArchi = screenArchi;
	}
	public String getDelCd() {
		return delCd;
	}
	public void setDelCd(String delCd) {
		this.delCd = delCd;
	}
	public String getLoadDate() {
		String myDate = loadDate;
		if (loadDate != null) myDate = loadDate.split("\\s")[0];
		return myDate;
	}
	public void setLoadDate(String loadDate) {
		this.loadDate = loadDate;
	}
	public String getLoadTime() {
		String myTime = loadTime;
		if (loadTime != null) myTime = loadTime.split("\\s")[1];
		return myTime;

	}
	public void setLoadTime(String loadTime) {
		this.loadTime = loadTime;
	}
	public String getLoadName() {
		return loadName;
	}
	public void setLoadName(String loadName) {
		this.loadName = loadName;
	}
	public String getUpdateDate() {
		String myDate = updateDate;
		if (updateDate != null) myDate = updateDate.split("\\s")[0];
		return myDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	public String getUpdateTime() {
		String myTime = updateTime;
		if (updateTime != null) myTime = updateTime.split("\\s")[1];
		return myTime;

	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public String getUpdateName() {
		return updateName;
	}
	public void setUpdateName(String updateName) {
		this.updateName = updateName;
	}
	
	
	

}
