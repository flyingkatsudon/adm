package com.bdp.adm.vo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserVO implements UserDetails {
	@JsonProperty("DT_RowId")
	private String DTRowId;
	private String userId;
	private String businessCode;
	private String loginName;
	private String userName;
	private String userPassword;
	private String userTitleCd;
	private String userDeptCd;
	private String userStatus;
	private String delCd;
	private String userLastDate;
	private String userStartDate;
	private String userEndDate;
	private String loadDate;
	private String loadTime;
	private String loadName;
	private String updateDate;
	private String updateTime;
	private String updateName;
	
	private String oldUserId;
	private String oldBusinessCode;
	
	private String userDeptName;
	private String userTitleName;
	private String userStatusName;
	
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
	public String getBusinessCode() {	
		return businessCode;
	}
	public void setBusinessCode(String businessCode) {
		this.businessCode = businessCode;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	/*public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}*/
	public void setUsername(String userName) {
		this.userName = userName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserTitleCd() {
		return userTitleCd;
	}
	public void setUserTitleCd(String userTitleCd) {
		this.userTitleCd = userTitleCd;
	}
	public String getUserDeptCd() {
		return userDeptCd;
	}
	public void setUserDeptCd(String userDeptCd) {
		this.userDeptCd = userDeptCd;
	}
	public String getUserStatus() {
		return userStatus;
	}
	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}
	public String getDelCd() {
		return delCd;
	}
	public void setDelCd(String delCd) {
		this.delCd = delCd;
	}
	public String getUserLastDate() {
		return userLastDate;
	}
	public void setUserLastDate(String userLastDate) {
		this.userLastDate = userLastDate;
	}
	public String getUserStartDate() {
		String myDate = userStartDate;
		if (userStartDate != null) myDate = userStartDate.split("\\s")[0];
		return myDate;
		//return userStartDate;
	}
	public void setUserStartDate(String userStartDate) {
		this.userStartDate = userStartDate;
	}
	public String getUserEndDate() {
		String myDate = userStartDate;
		if (userEndDate != null) myDate = userEndDate.split("\\s")[0];
		return myDate;
		//return userEndDate;
	}
	public void setUserEndDate(String userEndDate) {
		this.userEndDate = userEndDate;
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
	public String getOldBusinessCode() {
		return oldBusinessCode;
	}
	public void setOldBusinessCode(String oldBusinessCode) {
		this.oldBusinessCode = oldBusinessCode;
	}
	
	@Override
	public String toString() {
		return "UserVO [DTRowId=" + DTRowId + ", userId=" + userId + ", businessCode=" + businessCode + ", loginName="
				+ loginName + ", userName=" + userName + ", userPassword=" + userPassword + ", userTitleCd="
				+ userTitleCd + ", userDeptCd=" + userDeptCd + ", userStatus=" + userStatus + ", delCd=" + delCd
				+ ", userLastDate=" + userLastDate + ", userStartDate=" + userStartDate + ", userEndDate=" + userEndDate
				+ ", loadDate=" + loadDate + ", loadTime=" + loadTime + ", loadName=" + loadName + ", updateDate="
				+ updateDate + ", updateTime=" + updateTime + ", updateName=" + updateName + ", oldUserId=" + oldUserId
				+ ", oldBusinessCode=" + oldBusinessCode + "]";
	}
	
	
	// securityVO
	private int rbacPolicy;
	private String userStateInfo;
	
	private ArrayList<Object> screenList;
	private String sessionId;
	private String oldUserPassword;
	private String loginIp;
	
	private String uniqueHash;
	
	public String getUniqueHash() {
		return uniqueHash;
	}
	public void setUniqueHash(String uniqueHash) {
		this.uniqueHash = uniqueHash;
	}
	public String getLoginIp() {
		return loginIp;
	}
	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}
	public String getOldUserPassword() {
		return oldUserPassword;
	}
	public void setOldUserPassword(String oldUserPassword) {
		this.oldUserPassword = oldUserPassword;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public ArrayList<Object> getScreenList() {
		return screenList;
	}

	public void setScreenList(ArrayList<Object> screenList) {
		this.screenList = screenList;
	}

	public int getRbacPolicy() {
		return rbacPolicy;
	}

	public void setRbacPolicy(int rbacPolicy) {
		this.rbacPolicy = rbacPolicy;
	}

	public String getUserStateInfo() {
		return userStateInfo;
	}

	public void setUserStateInfo(String userStateInfo) {
		this.userStateInfo = userStateInfo;
	}

	public UserVO() {

	}

	public UserVO(String userId) {
		this.userId = userId;
	}

	public UserVO(String userId, String userPassword) {
		this.userId = userId;
		this.userPassword = userPassword;
	}

	public UserVO(String userId, String userPw, Collection<? extends GrantedAuthority> authorities) {
		this(userId, userPw, true, true, true, true, authorities);
	}

	public UserVO(String userId, String userPassword, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {

		if (((userId == null) || "".equals(userId)) || (userPassword == null)) {
			throw new IllegalArgumentException("Cannot pass null or empty values to constructor");
		}

		this.userId = userId;
		this.userPassword = userPassword;
		this.isEnabled = enabled;
		this.isAccountNonExpired = accountNonExpired;
		this.isCredentialsNonExpired = credentialsNonExpired;
		this.isAccountNonLocked = accountNonLocked;
	}
	
	public String getName() {
		return userName;
	}

	public void setName(String userName) {
		this.userName = userName;
	}

	// v0 ~ v4
	private String v0;
	private String v1;
	private String v2;
	private String v3;

	public String getV0() {
		return v0;
	}

	public void setV0(String v0) {
		this.v0 = v0;
	}

	public String getV1() {
		return v1;
	}

	public void setV1(String v1) {
		this.v1 = v1;
	}

	public String getV2() {
		return v2;
	}

	public void setV2(String v2) {
		this.v2 = v2;
	}

	public String getV3() {
		return v3;
	}

	public void setV3(String v3) {
		this.v3 = v3;
	}

	// extends ROLE implements UserDetails
	private String roleId;
	private String roleName;

	private List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
	
	private boolean isAccountNonExpired = true;
	private boolean isAccountNonLocked = true;
	private boolean isCredentialsNonExpired = true;
	private boolean isEnabled = true;
	
	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
		this.authorities.add(new SimpleGrantedAuthority(roleId));
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return this.isAccountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return this.isAccountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return this.isCredentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return this.isEnabled;
	}
	public String getUserDeptName() {
		return userDeptName;
	}
	public void setUserDeptName(String userDeptName) {
		this.userDeptName = userDeptName;
	}
	public String getUserTitleName() {
		return userTitleName;
	}
	public void setUserTitleName(String userTitleName) {
		this.userTitleName = userTitleName;
	}
	public String getUserStatusName() {
		return userStatusName;
	}
	public void setUserStatusName(String userStatusName) {
		this.userStatusName = userStatusName;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
