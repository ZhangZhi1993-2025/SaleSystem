package cn.edu.njnu.controller;

import cn.edu.njnu.controller.interceptor.AdminInterceptor;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;

/**
 * ********************AdminController(审核管理员)***********************************
 * 1.对某本书增加库存;*****************************************************************
 * AdminController的访问权限：具有审核管理员权限;
 * */

@Before(AdminInterceptor.class)
public class AdminController extends Controller {

	// 1.对某本书增加库存
	public void add_amount(int bookid, int addition) {

	}

}
