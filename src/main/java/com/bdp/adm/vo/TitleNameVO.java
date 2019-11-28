package com.bdp.adm.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TitleNameVO {
	
	@JsonProperty("DT_RowId")
	private String DTRowId;
	private String userId;
	private String userTitleCd;
	private String userTitleName;
	private String delCd;
	
	private String loadDate;
	private String loadTime;
	private String loadName;
	private String updateDate;
	private String updateTime;
	private String updateName;
	
	private String oldUserId;
	private String oldUserTitleCd;
	private int titleIdx;
	
	@JsonProperty("DT_RowId")
	public String getDTRowId() {
		return DTRowId;
	}
	@JsonProperty("DT_RowId")
	public void setDTRowId(String dTRowId) {
		DTRowId = dTRowId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserTitleCd() {
		return userTitleCd;
	}
	public void setUserTitleCd(String userTitleCd) {
		this.userTitleCd = userTitleCd;
	}
	public String getUserTitleName() {
		return userTitleName;
	}
	public void setUserTitleName(String userTitleName) {
		this.userTitleName = userTitleName;
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
	public String getOldUserId() {
		return oldUserId;
	}
	public void setOldUserId(String oldUserId) {
		this.oldUserId = oldUserId;
	}
	
	public String getOldUserTitleCd() {
		return oldUserTitleCd;
	}
	public void setOldUserTitleCd(String oldUserTitleCd) {
		this.oldUserTitleCd = oldUserTitleCd;
	}
	public int getTitleIdx() {
		return titleIdx;
	}
	public void setTitleIdx(int titleIdx) {
		this.titleIdx = titleIdx;
	}

	
	
	
}
