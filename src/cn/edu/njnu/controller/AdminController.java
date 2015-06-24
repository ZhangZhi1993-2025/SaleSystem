package cn.edu.njnu.controller;

import java.util.List;

import cn.edu.njnu.controller.interceptor.AdminInterceptor;
import cn.edu.njnu.service.BookService;
import cn.edu.njnu.viewmodel.BookViewModel;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;

/**
 * ********************AdminController(审核管理员)***********************************
 * 1.对某本书增加库存;*****************************************************************
 * AdminController的访问权限：具有审核管理员权限;
 * */

@Before(AdminInterceptor.class)
public class AdminController extends Controller {

	BookService bookService = new BookService();

	// 1.对某本书增加库存
	public void add_amount() {
		int bookid = Integer.parseInt(getPara("book"));
		int addition = Integer.parseInt(getPara("addition"));
		if (bookService.updateBookAmount(bookid, addition) == true)
			renderJson(true);
		else
			renderJson(false);
	}
	
	//

	// .查看某书的状况
	public void monitor_book() {
		String name = getPara("name");
		String category = getPara("category");
		List<BookViewModel> models = bookService.search(name, category, false,
				false, false);
		setAttr("searchResult", models);
		render("/WEB-INF/content/");
	}

}
