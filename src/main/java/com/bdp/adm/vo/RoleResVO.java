package com.bdp.adm.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RoleResVO {
	@JsonProperty("DT_RowId")
	private String DTRowId;
	private int roleResId;
	private String roleId; //pk
	private String resType; //pk
	private int resId;
	private String permission;
	private String auRbacText;	
	private String delCd;
	private String loadDate;
	private String loadTime;
	private String loadName;
	private String updateDate;
	private String updateTime;
	private String updateName;
	
	private String roleName;
	private String resTypeName;
	
	private String oldRoleId; //pk
	private String oldResType; //pk
	

	@JsonProperty("DT_RowId")
	public String getDTRowId() {
		return DTRowId;
	}
	@JsonProperty("DT_RowId")
	public void setDTRowId(String dTRowId) {
		DTRowId = dTRowId;
	}
	public int getRoleResId() {
		return roleResId;
	}
	public void setRoleResId(int roleResId) {
		this.roleResId = roleResId;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getResType() {
		return resType;
	}
	public void setResType(String resType) {
		this.resType = resType;
	}
	public int getResId() {
		return resId;
	}
	public void setResId(int resId) {
		this.resId = resId;
	}
	public String getPermission() {
		return permission;
	}
	public void setPermission(String permission) {
		this.permission = permission;
	}
	public String getAuRbacText() {
		return auRbacText;
	}
	public void setAuRbacText(String auRbacText) {
		this.auRbacText = auRbacText;
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
	public String getOldRoleId() {
		return oldRoleId;
	}
	public void setOldRoleId(String oldRoleId) {
		this.oldRoleId = oldRoleId;
	}
	public String getOldResType() {
		return oldResType;
	}
	public void setOldResType(String oldResType) {
		this.oldResType = oldResType;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getResTypeName() {
		return resTypeName;
	}
	public void setResTypeName(String resTypeName) {
		this.resTypeName = resTypeName;
	}
}
