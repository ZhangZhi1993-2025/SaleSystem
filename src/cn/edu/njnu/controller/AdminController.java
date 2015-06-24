package cn.edu.njnu.controller;

import java.util.List;

import cn.edu.njnu.controller.interceptor.AdminInterceptor;
import cn.edu.njnu.service.BookService;
import cn.edu.njnu.viewmodel.BookViewModel;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;

/**
 * ********************AdminController(��˹���Ա)***********************************
 * 1.��ĳ�������ӿ��;*****************************************************************
 * AdminController�ķ���Ȩ�ޣ�������˹���ԱȨ��;
 * */

@Before(AdminInterceptor.class)
public class AdminController extends Controller {

	BookService bookService = new BookService();

	// 1.��ĳ�������ӿ��
	public void add_amount() {
		int bookid = Integer.parseInt(getPara("book"));
		int addition = Integer.parseInt(getPara("addition"));
		if (bookService.updateBookAmount(bookid, addition) == true)
			renderJson(true);
		else
			renderJson(false);
	}
	
	//

	// .�鿴ĳ���״��
	public void monitor_book() {
		String name = getPara("name");
		String category = getPara("category");
		List<BookViewModel> models = bookService.search(name, category, false,
				false, false);
		setAttr("searchResult", models);
		render("/WEB-INF/content/");
	}

}
