package cn.edu.njnu.controller.interceptor;

import javax.servlet.http.Cookie;

import cn.edu.njnu.service.UserService;

import com.jfinal.aop.Interceptor;
import com.jfinal.core.ActionInvocation;

public class AdminInterceptor implements Interceptor {

	UserService usrService;

	/* 管理员的权限级别 */
	public static final int ADMIN_LEVEL = 0;

	@Override
	public void intercept(ActionInvocation invocation) {

		Cookie[] cookies = invocation.getController().getRequest().getCookies();
		boolean isLogOn = false;
		String userid = "";
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				if (cookies[i].getName() == "id") {
					isLogOn = true;
					userid = cookies[i].getValue();
					break;
				}
			}
			if (isLogOn
					&& usrService.userAuthority(Integer.valueOf(userid),
							ADMIN_LEVEL))
				invocation.invoke();
		}

		invocation.getController().renderJson("权限不足");

	}
}
