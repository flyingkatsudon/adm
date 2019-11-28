package com.bdp.adm.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ScreenLinkVO {
	@JsonProperty("DT_RowId")
	private String DTRowId;
	
	private int linkIdx;
	private String screenNo;
	private String resType;
	private String delCd;
	private String resTypeName;
	
	private String loadDate;
	private String loadTime;
	private String loadName;
	private String updateDate;
	private String updateTime;
	private String updateName;
	
	private String oldResType;

	@JsonProperty("DT_RowId")
	public String getDTRowId() {
		return DTRowId;
	}
	@JsonProperty("DT_RowId")
	public void setDTRowId(String dTRowId) {
		DTRowId = dTRowId;
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
	public int getLinkIdx() {
		return linkIdx;
	}
	public void setLinkIdx(int linkIdx) {
		this.linkIdx = linkIdx;
	}
	public String getScreenNo() {
		return screenNo;
	}
	public void setScreenNo(String screenNo) {
		this.screenNo = screenNo;
	}
	public String getResType() {
		return resType;
	}
	public void setResType(String resType) {
		this.resType = resType;
	}
	public String getDelCd() {
		return delCd;
	}
	public void setDelCd(String delCd) {
		this.delCd = delCd;
	}
	public String getResTypeName() {
		return resTypeName;
	}
	public void setResTypeName(String resTypeName) {
		this.resTypeName = resTypeName;
	}
	public String getOldResType() {
		return oldResType;
	}
	public void setOldResType(String oldResType) {
		this.oldResType = oldResType;
	}
	
}
