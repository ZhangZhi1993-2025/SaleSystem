package cn.edu.njnu.controller;

import javax.servlet.http.Cookie;

import cn.edu.njnu.service.UserService;
import cn.edu.njnu.viewmodel.UserViewModel;

import com.jfinal.core.Controller;

/**
 * ********************EntranceController(系统入口 )****************************
 * 0.首页;1.登陆验证; 2.注册验证;3.登陆页面;4.注册页面;**********************************
 * EntranceController的访问权限：无权限要求;
 */

public class EntranceController extends Controller {

	UserService userService = new UserService();

	// 0.首页
	public void index() {
		render("/content/main.jsp");
	}

	// 1.登陆验证
	public void login_validate() {
		String phone = getPara("phone");
		String password = getPara("password");
		if (userService.userLogin(phone, password) == true) {
			// 验证成功，推送cookies
			UserViewModel model = userService.getUserInfo(phone);
			Cookie[] cookies = new Cookie[4];
			cookies[0] = new Cookie("id", String.valueOf(model.getId()));
			cookies[1] = new Cookie("name", model.getName());
			cookies[2] = new Cookie("score", String.valueOf(model.getScore()));
			cookies[3] = new Cookie("admin", String.valueOf(userService
					.userAuthority(model.getId(), 0)));
			for (int i = 0; i < cookies.length; i++) {
				cookies[i].setMaxAge(60 * 20 * 24);
				cookies[i].setPath("/");
				getResponse().addCookie(cookies[i]);
			}
			redirect("/");
		} else {
			setAttr("oldPhone", phone);
			render("/content/error/login_error.jsp");
		}
	}

	// 2.注册验证
	public void register_validate() {
		String phone = getPara("phone");
		String password = getPara("password");
		int result = userService.userRegister(phone, password);
		switch (result) {
		case 2:
			// 注册成功,推送cookies
			UserViewModel model = userService.getUserInfo(phone);
			Cookie[] cookies = new Cookie[4];
			cookies[0] = new Cookie("id", String.valueOf(model.getId()));
			cookies[1] = new Cookie("name", model.getName());
			cookies[2] = new Cookie("score", String.valueOf(model.getScore()));
			cookies[3] = new Cookie("admin", String.valueOf(false));
			for (int i = 0; i < cookies.length; i++) {
				cookies[i].setMaxAge(60 * 60 * 24);
				cookies[i].setPath("/");
				getResponse().addCookie(cookies[i]);
			}
			redirect("/");
			return;
		case 1:
			setAttr("errorReason", "该用户名已经被注册!");
			break;
		case 0:
			setAttr("errorReason", "用户名不合法!");
			break;
		default:
			setAttr("errorReason", "注册失败!");
		}
		render("/content/error/register_error.jsp");
	}

	// 3.登陆页面
	public void login() {
		render("/content/login.jsp");
	}

	// 4.注册页面
	public void register() {
		render("/content/register.jsp");
	}

}
