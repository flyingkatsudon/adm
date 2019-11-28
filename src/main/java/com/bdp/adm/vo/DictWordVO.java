package com.bdp.adm.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DictWordVO {
	@JsonProperty("DT_RowId")
	private String DTRowId;
	
	private int dictWordIdx;
	private String word;
	private String businessCode;
	private String nerInfo;
	private String aliasList;
	private String delCd;
	private String version;
	private String loadDate;
	private String loadName;
	private String updateDate;
	private String updateName;
	
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
	public String getNerInfo() {
		return nerInfo;
	}
	public void setNerInfo(String nerInfo) {
		this.nerInfo = nerInfo;
	}
	public String getAliasList() {
		return aliasList;
	}
	public void setAliasList(String aliasList) {
		this.aliasList = aliasList;
	}
	public String getDelCd() {
		return delCd;
	}
	public void setDelCd(String delCd) {
		this.delCd = delCd;
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
	public String getLoadName() {
		return loadName;
	}
	public void setLoadName(String loadName) {
		this.loadName = loadName;
	}
	public String getUpdateName() {
		return updateName;
	}
	public void setUpdateName(String updateName) {
		this.updateName = updateName;
	}
	public int getDictWordIdx() {
		return dictWordIdx;
	}
	public void setDictWordIdx(int dictWordIdx) {
		this.dictWordIdx = dictWordIdx;
	}
	
}
