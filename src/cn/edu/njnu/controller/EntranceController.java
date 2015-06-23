package cn.edu.njnu.controller;

import cn.edu.njnu.service.OrderService;
import cn.edu.njnu.service.UserService;
import cn.edu.njnu.viewmodel.UserViewModel;

import com.jfinal.core.Controller;

/**
 * ********************EntranceController(ϵͳ��� )****************************
 * 0.��ҳ;1.��½; 2.ע��;*******************************************************
 * EntranceController�ķ���Ȩ�ޣ���Ȩ��Ҫ��;
 */

public class EntranceController extends Controller {

	UserService userService = new UserService();
	OrderService orderService = new OrderService();

	// 0.��ҳ
	
	public void index() {
		render("/WEB-INF/content/main.jsp");
	}

	// 1.��½
	public void login() {
		String phone = getPara("phone");
		String password = getPara("password");
		UserViewModel model;
		if (userService.userLogin(phone, password) == true) {
			model = userService.getUserInfo(phone);
			renderJson(model);
		} else {
			renderJson("�û��������벻��ȷ��");
		}
	}

	// 2.ע��
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
			renderJson("���û����Ѿ���ע�ᣡ");
			break;
		case 0:
			renderJson("�û������Ϸ���");
			break;
		default:
			renderJson("ע��ʧ�ܣ�");
		}
	}

}
