package cn.edu.njnu.controller;

import cn.edu.njnu.service.OrderService;
import cn.edu.njnu.service.UserService;
import cn.edu.njnu.viewmodel.UserViewModel;

import com.jfinal.core.Controller;

/**
 * ********************EntranceController(系统入口 )****************************
 * 0.首页;1.登陆; 2.注册;*******************************************************
 * EntranceController的访问权限：无权限要求;
 */

public class EntranceController extends Controller {

	UserService userService = new UserService();
	OrderService orderService = new OrderService();

	// 0.首页
	
	public void index() {
		render("/WEB-INF/content/main.jsp");
	}

	// 1.登陆
	public void login() {
		String phone = getPara("phone");
		String password = getPara("password");
		UserViewModel model;
		if (userService.userLogin(phone, password) == true) {
			model = userService.getUserInfo(phone);
			renderJson(model);
		} else {
			renderJson("用户名或密码不正确！");
		}
	}

	// 2.注册
	public void register() {
		String phone = getPara("phone");
		String password = getPara("password");
		UserViewModel model;
		int result = userService.userRegister(phone, password);
		switch (result) {
		case 2:
			model = userService.getUserInfo(phone);
			renderJson(model);
			break;
		case 1:
			renderJson("该用户名已经被注册！");
			break;
		case 0:
			renderJson("用户名不合法！");
			break;
		default:
			renderJson("注册失败！");
		}
	}

}
