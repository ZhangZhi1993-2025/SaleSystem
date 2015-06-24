package cn.edu.njnu.controller;

import cn.edu.njnu.service.OrderService;
import cn.edu.njnu.service.UserService;
import cn.edu.njnu.viewmodel.UserViewModel;

import com.jfinal.core.Controller;

/**
 * ********************EntranceController(ϵͳ��� )****************************
 * 0.��ҳ;1.��½��֤; 2.ע����֤;3.��½ҳ��;4.ע��ҳ��;**********************************
 * EntranceController�ķ���Ȩ�ޣ���Ȩ��Ҫ��;
 */

public class EntranceController extends Controller {

	UserService userService = new UserService();
	OrderService orderService = new OrderService();

	// 0.��ҳ
	public void index() {
		render("/content/main.jsp");
	}

	// 1.��½��֤
	public void login_validate() {
		String phone = getPara("phone");
		String password = getPara("password");
		if (userService.userLogin(phone, password) == true) {
			UserViewModel model;
			model = userService.getUserInfo(phone);
			setAttr("userInfo", model);
			redirect("/WEB-INF/content/main.jsp");
		} else {
			renderJson(false);
		}
	}

	// 2.ע����֤
	public void register_validate() {
		String phone = getPara("phone");
		String password = getPara("password");
		UserViewModel model;
		int result = userService.userRegister(phone, password);
		switch (result) {
		case 2:
			model = userService.getUserInfo(phone);
			setAttr("userInfo", model);
			redirect("/WEB-INF/content/main.jsp");
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

	// 3.��½ҳ��
	public void login() {
		render("/content/login.jsp");
	}

	// 4.ע��ҳ��
	public void register() {
		render("/content/register.jsp");
	}

}
