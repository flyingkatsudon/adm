package com.bdp.adm.security.authentication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.bdp.adm.biz.UserBiz;
import com.bdp.adm.vo.UserVO;

public class BDPAuthenticationProvider implements AuthenticationProvider {

	private static Logger logger = LoggerFactory.getLogger(BDPAuthenticationProvider.class);

	@Autowired
	private UserBiz userBiz;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		logger.info("{}", authentication);
		
		if (authentication == null) throw new AuthenticationServiceException("올바르지 않은 접근입니다.");
		// BDPAuthenticationFilter로부터 넘어온 User 객체
		UserVO user = (UserVO) authentication.getPrincipal();
		
		if (user == null) throw new AuthenticationServiceException("올바르지 않은 접근입니다.");
		//UserVO load = userBiz.loadUserByUsername(user.getUserId());
		UserVO load = userBiz.loadUserByUsername(user);

		// 1. load 실패하면 없는 유저 에러
		if (load == null) {
			throw new UsernameNotFoundException("시스템에 등록되어 있지 않은 사번이거나, 사번 또는 비밀번호를 잘못 입력하셨습니다");
		} else {
			if (load.getDelCd().toUpperCase().equals("X"))
				throw new AuthenticationServiceException("시스템에 등록되어 있지 않은 사번이거나, 사번 또는 비밀번호를 잘못 입력하셨습니다");
			// 차단된 사용자라면
			if (load.getUserStateInfo().equals("1") || load.getRbacPolicy() >= 5) {
				
				load.setRbacPolicy(5);
				load.setUserStateInfo("1");
				
				userBiz.updateLoginCnt(load);
				
				throw new AuthenticationServiceException("비밀번호가 5회이상 일치하지 않았습니다. 로그인이 차단됩니다.         시스템 관리자에게 요청해주세요.");
			}
			// 2. load와 auth의 비밀번호가 서로 같으면 같은 유저, 선택한 그룹사 코드가 일치
			if (user.getUserPassword().equals(load.getUserPassword().toUpperCase()) 
					&& user.getUserId().equals(load.getUserId())
					&& user.getBusinessCode().equals(load.getBusinessCode())) {
				
				// IT관리자, 사별관리자 권한이 아니라면
				if (load.getRoleId() == null || (!load.getRoleId().equals("1") && !load.getRoleId().equals("2"))) {
					throw new AuthenticationServiceException("해당 권한을 가지고 있지 않습니다");
				}
				
				// 로그인 성공 시, 실패 횟수, 차단 여부 기본값으로 설정
				user.setRbacPolicy(0);
				user.setUserStateInfo("0");
				
				userBiz.updateLoginCnt(user);
				
				return new BDPAuthenticationToken(load, load.getAuthorities());
			} else {
				// 3. 비밀번호가 서로 다르다면 틀린 비밀번호 에러
				// 3-1. 비밀번호 틀린 횟수 추가
				int failCnt = load.getRbacPolicy() + 1;
				String retnMsg = "비밀번호가 " + failCnt + "회이상 일치하지 않았습니다. 로그인이 차단됩니다.         시스템 관리자에게 요청해주세요.";
				load.setRbacPolicy(failCnt);
				userBiz.updateLoginCnt(load);
				load.setUserStateInfo("1");
				if (failCnt < 5) {
					retnMsg = "비밀번호가 " + failCnt + " 회 일치하지 않았습니다. 5회 이상 일치하지 않을 시 로그인이 차단됩니다";
					load.setUserStateInfo("0");
				} 
				throw new BadCredentialsException(retnMsg);
			}
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return BDPAuthenticationToken.class.isAssignableFrom(authentication);
	}
}
