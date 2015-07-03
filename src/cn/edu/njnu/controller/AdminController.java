package cn.edu.njnu.controller;

import java.util.List;

import cn.edu.njnu.controller.interceptor.AdminInterceptor;
import cn.edu.njnu.service.BookService;
import cn.edu.njnu.service.UserService;
import cn.edu.njnu.viewmodel.BookViewModel;
import cn.edu.njnu.viewmodel.UserViewModel;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.upload.UploadFile;

/**
 * ********************AdminController(管理员)***********************************
 * 1.查看某书的状况;*****************************************************************
 * AdminController的访问权限：具有管理员权限;
 * */

@Before(AdminInterceptor.class)
public class AdminController extends Controller {

	BookService bookService = new BookService();
	UserService userService = new UserService();

	// 1.查看某书的状况
	public void monitor_book() {
		int bookid = Integer.parseInt(getPara("book"));
		BookViewModel book = bookService.findBook(bookid, true);
		List<String> categories = bookService.getAllCategory();
		List<String> presses = bookService.getAllPress();
		setAttr("categories", categories);
		setAttr("presses", presses);
		setAttr("book", book);
		render("/content/admin/monitor_book.jsp");
	}

	// 2.增加一本书
	public void add_a_book() {
		UploadFile file = getFile("pic", "/resource/bookpic", 1000 * 1024,
				"utf-8");
		String picAddress = "./resource/bookpic/" + file.getFileName();
		String name = getPara("name");
		String press = getPara("press");
		double price = Double.parseDouble(getPara("price"));
		String category = getPara("category");
		int amount = Integer.parseInt(getPara("amount"));
		String desc = getPara("desc");
		bookService.addBook(name, price, category, amount, desc, press,
				picAddress);
		redirect("/");
	}

	// 3.增加书的界面
	public void add_book() {
		List<String> categories = bookService.getAllCategory();
		List<String> presses = bookService.getAllPress();
		setAttr("categories", categories);
		setAttr("presses", presses);
		render("/content/admin/add_book.jsp");
	}

	// 4.对某本书更新
	public void update_book() {
		UploadFile file = getFile("pic", "/resource/bookpic", 1000 * 1024,
				"utf-8");
		String picAddress = "./resource/bookpic/" + file.getFileName();
		int bookid = Integer.parseInt(getPara("book"));
		bookService.updateBookPic(bookid, picAddress);
		int newamount = Integer.parseInt(getPara("newamount"));
		bookService.updateBookAmount(bookid, newamount);
		String newcategory = getPara("newcategory");
		bookService.updateBookCategory(bookid, newcategory);
		double newprice = Double.parseDouble(getPara("newprice"));
		bookService.updateBookPrice(bookid, newprice);
		String newname = getPara("newname");
		bookService.updateBookName(bookid, newname);
		String newdesc = getPara("newdesc");
		bookService.updateBookDesc(bookid, newdesc);
		String newpress = getPara("newpress");
		bookService.updateBookPress(bookid, newpress);
		String url = "/monitor_book?book=" + bookid;
		redirect(url);
	}

	// 5.用户管理界面
	public void user_management() {
		List<UserViewModel> models = userService.getUserList();
		setAttr("users", models);
		render("/content/admin/user_management.jsp");
	}

	// 6.冻结用户
	public void user_freeze() {
		int userid = Integer.parseInt(getPara("user"));
		boolean state = userService.freezeUser(userid);
		renderJson(state);
	}

	// 6.解冻用户
	public void user_unfreeze() {
		int userid = Integer.parseInt(getPara("user"));
		boolean state = userService.unfreezeUser(userid);
		renderJson(state);
	}

}
