package com.azurealstn.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import com.azurealstn.beans.UserBean;

public class CheckLoginInterceptor implements HandlerInterceptor {

	private UserBean loginUserBean;
	
	public CheckLoginInterceptor(UserBean loginUserBean) {
		this.loginUserBean = loginUserBean;
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		if (loginUserBean.isUserLogin() == false) {
			String contextPathString = request.getContextPath();
			response.sendRedirect(contextPathString + "/user/not_login");
			return false;
		}
		return true;
	}
}
