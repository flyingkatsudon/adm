package com.bdp.adm.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bdp.adm.biz.UserBiz;
import com.bdp.adm.util.CheckPwd;
import com.bdp.adm.vo.UserVO;

@Controller
public class LoginCtrl {
	
	@Autowired
	private UserBiz userBiz;
	
	@Autowired
	private CheckPwd checkPwd;

	@RequestMapping(value = "/")
	public String login(HttpServletRequest request, HttpServletResponse respose) {
		return "redirect:/starter.jsp";
	}
	
	@RequestMapping(value = {"/fail", "/process"})
	public ModelAndView failMsg(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("msg", request.getAttribute("msg"));
		mav.setViewName("login.jsp");
		return mav;
	}
	
	@RequestMapping(value = "/auth")
	public String auth(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Object o = request.getSession().getAttribute("isInitPwd");
		if (o == null) return "redirect:/logout";
		return !((boolean) o) ? "redirect:/starter.jsp" : "redirect:/changePwd.jsp";  
	}
	
	@RequestMapping(value = "/update")
	@ResponseBody
	public Map<String, Object> update(HttpServletRequest request, HttpServletResponse response, @RequestBody UserVO user) throws IOException {

		Map<String, Object> map = new HashMap<String, Object>();
		
		/*// 영문 대소문자, 특수문자 포함 10~20자리
		Pattern pattern = Pattern.compile("/(?=.*[a-z])(?=.*[A-Z])(?=.*[#$^+=!*()@%&]).{10,20}$/");

        Matcher matcher = pattern.matcher(user.getUserPassword());
        boolean matches = matcher.matches();*/
        
		try {
			// 비밀번호 교체의 경우
			if (user != null && user.getOldUserPassword() != null) {
				request.setAttribute("changePwd", null);
				
	    		String encodedPw = checkPwd.passwordEncoder.encodePassword(user.getUserPassword(), "").toUpperCase();
	    		user.setUserPassword(encodedPw);

	    		// 변경하려는 비밀번호 = 초기 비밀번호
	    		if(encodedPw.equals(user.getOldUserPassword())){
	    			map.put("msg", "초기 비밀번호로 변경할 수 없습니다");
	    		} else {
	    			userBiz.updatePwd(user);
	    			map.put("msg", "변경이 완료되었습니다");
	    			map.put("location", "logout");
	    			
	    			return map;
	    		}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return map;
	}
}
