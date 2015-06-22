package cn.edu.njnu.controller.interceptor;

//import javax.servlet.http.Cookie;

import cn.edu.njnu.service.UserService;

import com.jfinal.aop.Interceptor;
import com.jfinal.core.ActionInvocation;

public class AdminInterceptor implements Interceptor {

	UserService usrService;

	/* 管理员的权限级别 */
	public static final int ADMIN_LEVEL = 0;

	@Override
	public void intercept(ActionInvocation invocation) {

		invocation.getController().getPara("id");
		/*
		 * Cookie[] cookies =
		 * invocation.getController().getRequest().getCookies();
		 * 
		 * if (cookies != null) { String userID = cookies[0].getValue(); if
		 * (usrService.userAuthority(Integer.valueOf(userID), ADMIN_LEVEL)) {
		 * invocation.invoke(); return; } }
		 * 
		 * invocation.getController().renderJson("权限不足");
		 */

	}
}
