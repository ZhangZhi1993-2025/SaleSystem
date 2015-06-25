package cn.edu.njnu.controller.interceptor;

import javax.servlet.http.Cookie;

import com.jfinal.aop.Interceptor;
import com.jfinal.core.ActionInvocation;

public class UserInterceptor implements Interceptor {

	@Override
	public void intercept(ActionInvocation invocation) {

		Cookie[] cookies = invocation.getController().getRequest().getCookies();
		boolean isLogOn = false;
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				if (cookies[i].getName() == "id") {
					isLogOn = true;
					break;
				}
			}
		}
		if (isLogOn == true)
			invocation.invoke();
		invocation.getController().renderJson("权限不足");
	}
}
