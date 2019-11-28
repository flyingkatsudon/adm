package com.bdp.adm.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TaxonomyVO {
	
	@JsonProperty("DT_RowId")
	private String DTRowId;
	private String taxId;
	private int taxIdx;
	private String businessCode;
	private String version;
	private String level1;
	private String level2;
	private String level3;
	private String level4;
	private String refKeyword;
	private String keyword;
	private String delCd;
	private String description;
	
	private String loadDate;
	private String loadTime;
	private String loadName;
	private String updateDate;
	private String updateTime;
	private String updateName;
	
	private String oldTaxId;
	private int oldTaxIdx;
	private String oldBusinessCode;
	private String oldVersion;
	
	@JsonProperty("DT_RowId")
	public String getDTRowId() {
		return DTRowId;
	}
	@JsonProperty("DT_RowId")
	public void setDTRowId(String dTRowId) {
		DTRowId = dTRowId;
	}
	
	public String getTaxId() {
		return taxId;
	}
	public void setTaxId(String taxId) {
		this.taxId = taxId;
	}
	public int getTaxIdx() {
		return taxIdx;
	}
	public void setTaxIdx(int taxIdx) {
		this.taxIdx = taxIdx;
	}
	public String getBusinessCode() {
		return businessCode;
	}
	public void setBusinessCode(String businessCode) {
		this.businessCode = businessCode;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getLevel1() {
		return level1;
	}
	public void setLevel1(String level1) {
		this.level1 = level1;
	}
	public String getLevel2() {
		return level2;
	}
	public void setLevel2(String level2) {
		this.level2 = level2;
	}
	public String getLevel3() {
		return level3;
	}
	public void setLevel3(String level3) {
		this.level3 = level3;
	}
	public String getLevel4() {
		return level4;
	}
	public void setLevel4(String level4) {
		this.level4 = level4;
	}
	public String getRefKeyword() {
		return refKeyword;
	}
	public void setRefKeyword(String refKeyword) {
		this.refKeyword = refKeyword;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getLoadDate() {
		String myDate = loadDate;
		if (loadDate != null) myDate = loadDate.split("\\.")[0];
		return myDate;
		//return loadDate;
	}
	public void setLoadDate(String loadDate) {
		this.loadDate = loadDate;
	}
	public String getUpdateDate() {
		String myDate = updateDate;
		if (updateDate != null) myDate = updateDate.split("\\.")[0];
		return myDate;
		//return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	
	public String getOldTaxId() {
		return oldTaxId;
	}
	public void setOldTaxId(String oldTaxId) {
		this.oldTaxId = oldTaxId;
	}
	public int getOldTaxIdx() {
		return oldTaxIdx;
	}
	public void setOldTaxIdx(int oldTaxIdx) {
		this.oldTaxIdx = oldTaxIdx;
	}
	public String getOldBusinessCode() {
		return oldBusinessCode;
	}
	public void setOldBusinessCode(String oldBusinessCode) {
		this.oldBusinessCode = oldBusinessCode;
	}
	public String getOldVersion() {
		return oldVersion;
	}
	public void setOldVersion(String oldVersion) {
		this.oldVersion = oldVersion;
	}

	public String getLoadTime() {
		String myTime = loadTime;
	    if (loadTime != null) myTime = loadTime.split("\\s")[1].split("\\.")[0];
	    
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
	public String getUpdateTime() {
		String myTime = updateTime;
	    if (updateTime != null) myTime = updateTime.split("\\s")[1].split("\\.")[0];
	    
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
	public String getDelCd() {
		return delCd;
	}
	public void setDelCd(String delCd) {
		this.delCd = delCd;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	

}
