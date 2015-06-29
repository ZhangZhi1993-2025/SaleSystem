package cn.edu.njnu.controller;

import java.util.List;

import cn.edu.njnu.controller.interceptor.AdminInterceptor;
import cn.edu.njnu.service.BookService;
import cn.edu.njnu.viewmodel.BookViewModel;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;

/**
 * ********************AdminController(管理员)***********************************
 * 1.查看某书的状况;*****************************************************************
 * AdminController的访问权限：具有管理员权限;
 * */

@Before(AdminInterceptor.class)
public class AdminController extends Controller {

	BookService bookService = new BookService();

	// 1.查看某书的状况
	public void monitor_book() {
		String name = getPara("name");
		String category = getPara("category");
		List<BookViewModel> models = bookService.search(name, category, false,
				false, false);
		setAttr("searchResult", models);
		render("/WEB-INF/content/");
	}

	// 2.增加一本书
	public void addBook() {
		String name = getPara("name");
		double price = Double.parseDouble(getPara("price"));
		String category = getPara("category");
		int amount = Integer.parseInt(getPara("amount"));
		String desc = getPara("desc");
		bookService.addBook(name, price, category, amount, desc);
		redirect("/admin/");
	}

	// 3.对某本书更新库存
	public void update_amount() {
		int bookid = Integer.parseInt(getPara("book"));
		int addition = Integer.parseInt(getPara("addition"));
		if (bookService.updateBookAmount(bookid, addition) == true)
			renderJson(true);
		else
			renderJson(false);
	}

	// 4.对某本书更新分类
	public boolean update_category() {
		int bookid = Integer.parseInt(getPara("book"));
		String info = getPara("newcategory");
		return bookService.updateBookCategory(bookid, info);
	}

	// 5.对某本书更新价格
	public boolean update_price() {
		int bookid = Integer.parseInt(getPara("book"));
		double info = Double.parseDouble(getPara("newprice"));
		return bookService.updateBookPrice(bookid, info);
	}

	// 6.对某本书更新名字
	public boolean update_name() {
		int bookid = Integer.parseInt(getPara("book"));
		String info = getPara("newname");
		return bookService.updateBookName(bookid, info);
	}

	// 7.对某本书更新描述
	public boolean update_desc() {
		int bookid = Integer.parseInt(getPara("book"));
		String info = getPara("newdesc");
		return bookService.updateBookDesc(bookid, info);
	}

}
