package cn.edu.njnu.controller;

import cn.edu.njnu.controller.interceptor.AdminInterceptor;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;

/**
 * ********************AdminController(��˹���Ա)***********************************
 * 1.��ĳ�������ӿ��;*****************************************************************
 * AdminController�ķ���Ȩ�ޣ�������˹���ԱȨ��;
 * */

@Before(AdminInterceptor.class)
public class AdminController extends Controller {

	// 1.��ĳ�������ӿ��
	public void add_amount(int bookid, int addition) {

	}

}
