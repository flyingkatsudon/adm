package com.bdp.adm.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DocFilterVO {
	@JsonProperty("DT_RowId")
	private String DTRowId;
	private int filterIdx; //pk
	private String businessCode; //pk
	private String metaConcept; //pk
	private String filterWord; //pk
	private String andWord; //pk
	private String notWord; //pk
	private String delCd;
	private String version; //pk
	private String categoryCode;
	private int level;
	
	private String loadDate;
	private String loadTime;
	private String loadName;
	private String updateDate;
	private String updateTime;
	private String updateName;
	
	private int oldFilterIdx; //pk
	private String oldBusinessCode; //pk
	private String oldMetaConcept; //pk
	private String oldFilterWord; //pk
	private String oldAndWord; //pk
	private String oldNotWord; //pk
	private String oldVersion; //pk
	
	@JsonProperty("DT_RowId")
	public String getDTRowId() {
		return DTRowId;
	}
	@JsonProperty("DT_RowId")
	public void setDTRowId(String dTRowId) {
		DTRowId = dTRowId;
	}
	public int getFilterIdx() {
		return filterIdx;
	}
	public void setFilterIdx(int filterIdx) {
		this.filterIdx = filterIdx;
	}
	public String getBusinessCode() {
		return businessCode;
	}
	public void setBusinessCode(String businessCode) {
		this.businessCode = businessCode;
	}
	public String getMetaConcept() {
		return metaConcept;
	}
	public void setMetaConcept(String metaConcept) {
		this.metaConcept = metaConcept;
	}
	public String getFilterWord() {
		return filterWord;
	}
	public void setFilterWord(String filterWord) {
		this.filterWord = filterWord;
	}
	public String getAndWord() {
		return andWord;
	}
	public void setAndWord(String andWord) {
		this.andWord = andWord;
	}
	public String getNotWord() {
		return notWord;
	}
	public void setNotWord(String notWord) {
		this.notWord = notWord;
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
	@Override
	public String toString() {
		return "DocFilterVO [DTRowId=" + DTRowId + ", filterIdx=" + filterIdx + ", businessCode=" + businessCode
				+ ", metaConcept=" + metaConcept + ", filterWord=" + filterWord + ", andWord=" + andWord + ", notWord="
				+ notWord + ", version=" + version + ", loadDate=" + loadDate + ", updateDate=" + updateDate + "]";
	}
	public int getOldFilterIdx() {
		return oldFilterIdx;
	}
	public void setOldFilterIdx(int oldFilterIdx) {
		this.oldFilterIdx = oldFilterIdx;
	}
	public String getOldBusinessCode() {
		return oldBusinessCode;
	}
	public void setOldBusinessCode(String oldBusinessCode) {
		this.oldBusinessCode = oldBusinessCode;
	}
	public String getOldMetaConcept() {
		return oldMetaConcept;
	}
	public void setOldMetaConcept(String oldMetaConcept) {
		this.oldMetaConcept = oldMetaConcept;
	}
	public String getOldFilterWord() {
		return oldFilterWord;
	}
	public void setOldFilterWord(String oldFilterWord) {
		this.oldFilterWord = oldFilterWord;
	}
	public String getOldAndWord() {
		return oldAndWord;
	}
	public void setOldAndWord(String oldAndWord) {
		this.oldAndWord = oldAndWord;
	}
	public String getOldNotWord() {
		return oldNotWord;
	}
	public void setOldNotWord(String oldNotWord) {
		this.oldNotWord = oldNotWord;
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
		String myDate = updateDate;
		if (updateDate != null) myDate = updateDate.split("\\.")[0];
		return myDate;
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
	public String getCategoryCode() {
		return categoryCode;
	}
	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}	
}
