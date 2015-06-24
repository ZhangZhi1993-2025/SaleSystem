package cn.edu.njnu.controller.interceptor;

//import javax.servlet.http.Cookie;

import com.jfinal.aop.Interceptor;
import com.jfinal.core.ActionInvocation;

public class UserInterceptor implements Interceptor {

	@Override
	public void intercept(ActionInvocation invocation) {

		/*
		 * Cookie[] cookies =
		 * invocation.getController().getRequest().getCookies();
		 * 
		 * if (cookies != null) { invocation.invoke(); return; }
		 * 
		 * invocation.getController().renderJson("È¨ÏÞ²»×ã");
		 */

	}
}
