package com.bdp.adm.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ScreenLoginVO {
	@JsonProperty("DT_RowId")
	private String DTRowId;
	private String screenNo; //pk
	private String screenLoginUser;
	private String screenLoginTime;
	private String delCd="";
	private String loadDate;
	private String loadTime;
	private String loadName;
	private String updateDate;
	private String updateTime;
	private String updateName;
	
	private String oldScreenNo;
	
	@JsonProperty("DT_RowId")
	public String getDTRowId() {
		return DTRowId;
	}
	@JsonProperty("DT_RowId")
	public void setDTRowId(String dTRowId) {
		DTRowId = dTRowId;
	}
	public String getScreenNo() {
		return screenNo;
	}
	public void setScreenNo(String screenNo) {
		this.screenNo = screenNo;
	}
	public String getScreenLoginUser() {
		return screenLoginUser;
	}
	public void setScreenLoginUser(String screenLoginUser) {
		this.screenLoginUser = screenLoginUser;
	}
	public String getScreenLoginTime() {
		return screenLoginTime;
	}
	public void setScreenLoginTime(String screenLoginTime) {
		this.screenLoginTime = screenLoginTime;
	}
	public String getDelCd() {
		return delCd;
	}
	public void setDelCd(String delCd) {
		this.delCd = delCd;
	}
	public String getLoadDate() {
		String myDate = (loadDate != null) ? loadDate.split("\\s")[0] : "";
		return myDate;
		//return loadDate;
	}
	public void setLoadDate(String loadDate) {
		this.loadDate = loadDate;
	}
	public String getLoadTime() {
		String myTime = (loadTime != null) ? loadTime.split("\\s")[1] : "";
		return myTime;
		//return loadTime;
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
		String myDate = (updateDate != null) ? updateDate.split("\\s")[0] : "";
		return myDate;
		//return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	public String getUpdateTime() {
		String myTime = (updateTime != null) ? updateTime.split("\\s")[1] : "";
		return myTime;
		//return updateTime;
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
	public String getOldScreenNo() {
		return oldScreenNo;
	}
	public void setOldScreenNo(String oldScreenNo) {
		this.oldScreenNo = oldScreenNo;
	}
	
	

	
}
