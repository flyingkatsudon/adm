package com.bdp.adm.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EwdVO {
	
	@JsonProperty("DT_RowId")
	private String DTRowId;
	
	private int ewdIdx;
	private String synsetId; //PK
	private String wordId; //PK
	private String businessCode; //PK
	private String word;
	private String posInfo;
	private String attributeInfo;
	private int protoInfo;
	private String domainInfo;
	private String ontologyInfo;
	private String polarity;
	private int preVal;
	private double weight;
	private double zVal;
	private double sVal;
	private String delCd;
	private double version;
	
	private String loadDate;
	private String loadTime;
	private String loadName;
	private String updateDate;
	private String updateTime;
	private String updateName;
	
	private String oldSynsetId; //PK
	private String oldWordId; //PK
	private String oldBusinessCode; //PK
	
	@JsonProperty("DT_RowId")
	public String getDTRowId() {
		return DTRowId;
	}
	@JsonProperty("DT_RowId")
	public void setDTRowId(String dTRowId) {
		DTRowId = dTRowId;
	}
	public String getSynsetId() {
		return synsetId;
	}
	public void setSynsetId(String synsetId) {
		this.synsetId = synsetId;
	}
	public String getWordId() {
		return wordId;
	}
	public void setWordId(String wordId) {
		this.wordId = wordId;
	}
	public String getBusinessCode() {
		return businessCode;
	}
	public void setBusinessCode(String businessCode) {
		this.businessCode = businessCode;
	}
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public String getPosInfo() {
		return posInfo;
	}
	public void setPosInfo(String posInfo) {
		this.posInfo = posInfo;
	}
	public String getAttributeInfo() {
		return attributeInfo;
	}
	public void setAttributeInfo(String attributeInfo) {
		this.attributeInfo = attributeInfo;
	}
	public int getProtoInfo() {
		return protoInfo;
	}
	public void setProtoInfo(int protoInfo) {
		this.protoInfo = protoInfo;
	}
	public String getDomainInfo() {
		return domainInfo;
	}
	public void setDomainInfo(String domainInfo) {
		this.domainInfo = domainInfo;
	}
	public String getOntologyInfo() {
		return ontologyInfo;
	}
	public void setOntologyInfo(String ontologyInfo) {
		this.ontologyInfo = ontologyInfo;
	}
	public String getPolarity() {
		return polarity;
	}
	public void setPolarity(String polarity) {
		this.polarity = polarity;
	}
	public int getPreVal() {
		return preVal;
	}
	public void setPreVal(int preVal) {
		this.preVal = preVal;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public double getzVal() {
		return zVal;
	}
	public void setzVal(double zVal) {
		this.zVal = zVal;
	}
	public double getsVal() {
		return sVal;
	}
	public void setsVal(double sVal) {
		this.sVal = sVal;
	}
	public double getVersion() {
		return version;
	}
	public void setVersion(double version) {
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
	public String getOldSynsetId() {
		return oldSynsetId;
	}
	public void setOldSynsetId(String oldSynsetId) {
		this.oldSynsetId = oldSynsetId;
	}
	public String getOldWordId() {
		return oldWordId;
	}
	public void setOldWordId(String oldWordId) {
		this.oldWordId = oldWordId;
	}
	public String getOldBusinessCode() {
		return oldBusinessCode;
	}
	public void setOldBusinessCode(String oldBusinessCode) {
		this.oldBusinessCode = oldBusinessCode;
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
	public int getEwdIdx() {
		return ewdIdx;
	}
	public void setEwdIdx(int ewdIdx) {
		this.ewdIdx = ewdIdx;
	}
}
