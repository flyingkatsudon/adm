package com.bdp.adm.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StopwordVO {
	
	@JsonProperty("DT_RowId")
	private String DTRowId;
	private int keywordId;
	private String businessCode;
	private String categoryCode;
	private String keyword;
	private String registrantInfo;
	private String delCd;
	private String version;
	private String stopType;

	private String loadDate;
	private String loadTime;
	private String loadName;
	private String updateDate;
	private String updateTime;
	private String updateName;
	
	private int oldKeywordId;
	private String oldBusinessCode;
	private String oldCategoryCode;
	private String oldRegistrantInfo;
	private String oldVersion;
	
	
	@JsonProperty("DT_RowId")
	public String getDTRowId() {
		return DTRowId;
	}
	@JsonProperty("DT_RowId")
	public void setDTRowId(String dTRowId) {
		DTRowId = dTRowId;
	}
	public int getKeywordId() {
		return keywordId;
	}
	public void setKeywordId(int keywordId) {
		this.keywordId = keywordId;
	}
	public String getBusinessCode() {
		return businessCode;
	}
	public void setBusinessCode(String businessCode) {
		this.businessCode = businessCode;
	}
	public String getCategoryCode() {
		return categoryCode;
	}
	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getRegistrantInfo() {
		return registrantInfo;
	}
	public void setRegistrantInfo(String registrantInfo) {
		this.registrantInfo = registrantInfo;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getLoadDate() {
		String myDate = loadDate;
		if (loadDate != null) myDate = loadDate.split("\\.")[0];
		return myDate;

	}
	public void setLoadDate(String loadDate) {
		this.loadDate = loadDate;
	}
	public String getUpdateDate() {
		String myDate = updateDate;
		if (updateDate != null) myDate = updateDate.split("\\.")[0];
		return myDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	public int getOldKeywordId() {
		return oldKeywordId;
	}
	public void setOldKeywordId(int oldKeywordId) {
		this.oldKeywordId = oldKeywordId;
	}
	public String getOldBusinessCode() {
		return oldBusinessCode;
	}
	public void setOldBusinessCode(String oldBusinessCode) {
		this.oldBusinessCode = oldBusinessCode;
	}
	public String getOldCategoryCode() {
		return oldCategoryCode;
	}
	public void setOldCategoryCode(String oldCategoryCode) {
		this.oldCategoryCode = oldCategoryCode;
	}
	public String getOldRegistrantInfo() {
		return oldRegistrantInfo;
	}
	public void setOldRegistrantInfo(String oldRegistrantInfo) {
		this.oldRegistrantInfo = oldRegistrantInfo;
	}
	public String getOldVersion() {
		return oldVersion;
	}
	public void setOldVersion(String oldVersion) {
		this.oldVersion = oldVersion;
	}
	public String getLoadTime() {
	
		return loadTime;
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
	public String getUpdateTime() {
	
		return updateTime;
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
	public String getDelCd() {
		return delCd;
	}
	public void setDelCd(String delCd) {
		this.delCd = delCd;
	}
	public String getStopType() {
		return stopType;
	}
	public void setStopType(String stopType) {
		this.stopType = stopType;
	}
	
	
}
