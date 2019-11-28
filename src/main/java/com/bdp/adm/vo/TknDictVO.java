package com.bdp.adm.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TknDictVO {
	
	@JsonProperty("DT_RowId")
	private String DTRowId;
	private String wordId;
	private String word;
	private String businessCode;
	private String leftIdx;
	private String rightIdx;
	private String cost;
	private String posInfo;
	private String nerInfo;
	private String finSyllable;
	private String aliasList;
	private String cmpndInfo;
	private String addInfo1;
	private String addInfo2;
	private String cmpndSummary;
	private String cmpndDetail;
	private String version;
	
	private String loadDate;
	private String loadTime;
	private String loadName;
	private String updateDate;
	private String updateTime;
	private String updateName;
	
	private String oldWordId;
	private String oldWord;
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
	public String getWordId() {
		return wordId;
	}
	public void setWordId(String wordId) {
		this.wordId = wordId;
	}
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public String getBusinessCode() {
		return businessCode;
	}
	public void setBusinessCode(String businessCode) {
		this.businessCode = businessCode;
	}
	public String getLeftIdx() {
		return leftIdx;
	}
	public void setLeftIdx(String leftIdx) {
		this.leftIdx = leftIdx;
	}
	public String getRightIdx() {
		return rightIdx;
	}
	public void setRightIdx(String rightIdx) {
		this.rightIdx = rightIdx;
	}
	public String getCost() {
		return cost;
	}
	public void setCost(String cost) {
		this.cost = cost;
	}
	public String getPosInfo() {
		return posInfo;
	}
	public void setPosInfo(String posInfo) {
		this.posInfo = posInfo;
	}
	public String getNerInfo() {
		return nerInfo;
	}
	public void setNerInfo(String nerInfo) {
		this.nerInfo = nerInfo;
	}
	public String getFinSyllable() {
		return finSyllable;
	}
	public void setFinSyllable(String finSyllable) {
		this.finSyllable = finSyllable;
	}
	public String getAliasList() {
		return aliasList;
	}
	public void setAliasList(String aliasList) {
		this.aliasList = aliasList;
	}
	public String getCmpndInfo() {
		return cmpndInfo;
	}
	public void setCmpndInfo(String cmpndInfo) {
		this.cmpndInfo = cmpndInfo;
	}
	public String getAddInfo1() {
		return addInfo1;
	}
	public void setAddInfo1(String addInfo1) {
		this.addInfo1 = addInfo1;
	}
	public String getAddInfo2() {
		return addInfo2;
	}
	public void setAddInfo2(String addInfo2) {
		this.addInfo2 = addInfo2;
	}
	public String getCmpndSummary() {
		return cmpndSummary;
	}
	public void setCmpndSummary(String cmpndSummary) {
		this.cmpndSummary = cmpndSummary;
	}
	public String getCmpndDetail() {
		return cmpndDetail;
	}
	public void setCmpndDetail(String cmpndDetail) {
		this.cmpndDetail = cmpndDetail;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getLoadDate() {
		return loadDate;
	}
	public void setLoadDate(String loadDate) {
		this.loadDate = loadDate;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	public String getOldWordId() {
		return oldWordId;
	}
	public void setOldWordId(String oldWordId) {
		this.oldWordId = oldWordId;
	}
	public String getOldWord() {
		return oldWord;
	}
	public void setOldWord(String oldWord) {
		this.oldWord = oldWord;
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
		String myTime = (loadTime != null) ? loadTime.split("\\s")[1] : "";
		return myTime.substring(0, 8);
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
	public String getUpdateTime() {
		String myTime = (updateTime != null) ? updateTime.split("\\s")[1] : "";
		return myTime.substring(0, 8);
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

}
