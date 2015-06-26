package cn.edu.njnu.controller;

import java.util.List;

import cn.edu.njnu.controller.interceptor.UserInterceptor;
import cn.edu.njnu.service.BookService;
import cn.edu.njnu.service.CommentService;
import cn.edu.njnu.service.OrderService;
import cn.edu.njnu.service.ShoppingCarService;
import cn.edu.njnu.service.UserService;
import cn.edu.njnu.viewmodel.BookViewModel;
import cn.edu.njnu.viewmodel.CommentViewModel;
import cn.edu.njnu.viewmodel.OrderDetailViewModel;
import cn.edu.njnu.viewmodel.ShoppingCarViewModel;
import cn.edu.njnu.viewmodel.ShoppingDetail;
import cn.edu.njnu.viewmodel.ShoppingInfo;
import cn.edu.njnu.viewmodel.OrderViewModel;
import cn.edu.njnu.viewmodel.UserViewModel;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;

/**
 * ********************UserController(个人中心 )****************************
 * 1.修改密码;2.修改绑定手机号;3.将物品放入购物车;4.注销账户;5.确认下单;6.当前订单列表;7.历史订单列表;8.购物车内的书确认下单;
 * 9.查看订单详情;10.查看购物车;11.多条件查询商品;12.热门商品推送;13.进入某本书的详情;
 * ************************************************************
 * UserController的访问权限：任意已经登陆的用户;
 */

public class UserController extends Controller {

	UserService userService = new UserService();
	OrderService orderService = new OrderService();
	BookService bookService = new BookService();
	ShoppingCarService carService = new ShoppingCarService();
	CommentService CommentService = new CommentService();

	// 1.修改密码
	@Before(UserInterceptor.class)
	public void change_password() {
		int userid = Integer.parseInt(getPara("user"));
		String newpassword = getPara("newpassword");
		if (userService.userUpdate(userid, newpassword, 0) == true) {
			String phone = userService.getUserPhone(userid);
			UserViewModel model;
			model = userService.getUserInfo(phone);
			setAttr("userInfo", model);
			redirect("/content/main.jsp");
		} else
			renderJson(false);
	}

	// 2.修改手机号
	@Before(UserInterceptor.class)
	public void change_phone_number() {
		int userid = Integer.parseInt(getPara("user"));
		String newphone = getPara("newphone");
		if (userService.userUpdate(userid, newphone, 1) == true) {
			UserViewModel model;
			model = userService.getUserInfo(newphone);
			setAttr("userInfo", model);
			redirect("/content/main.jsp");
		} else
			renderJson(false);
	}

	// 3.将物品放入购物车
	@Before(UserInterceptor.class)
	public void add_item_to_car() {
		int userid = Integer.parseInt(getPara("user"));
		int bookid = Integer.parseInt(getPara("book"));
		int amount = Integer.parseInt(getPara("amount"));
		carService.addItem(userid, bookid, amount);
		renderJson(true);
	}

	// 4.注销账户
	@Before(UserInterceptor.class)
	public void delete_account() {
		int userid = Integer.parseInt(getPara("user"));
		if (userService.userDelete(userid) == true)
			redirect("/content/main.jsp");
		else
			renderJson(false);
	}

	// 5.确认下单
	@Before(UserInterceptor.class)
	public void create_order() {
		int userid = Integer.parseInt(getPara("user"));
		int bookid = Integer.parseInt(getPara("book"));
		int amount = Integer.parseInt(getPara("amount"));
		BookViewModel model = bookService.findBook(bookid, false);
		ShoppingDetail detail = new ShoppingDetail(model.getId(), amount,
				model.getPrice());
		ShoppingInfo info = new ShoppingInfo(userid, detail);
		if (orderService.createOrder(info) == true)
			renderJson(true);
		else
			renderJson(false);
	}

	// 6.当前订单列表
	@Before(UserInterceptor.class)
	public void current_order() {
		int userid = Integer.parseInt(getPara("user"));
		List<OrderViewModel> models = orderService.userGetOrder(userid);
		setAttr("currentOrder", models);
		render("/content/order/current_order_list.jsp");
	}

	// 7.历史订单列表
	@Before(UserInterceptor.class)
	public void history_order() {
		int userid = Integer.parseInt(getPara("user"));
		List<OrderViewModel> models = orderService.userGetOrder(userid);
		setAttr("historyOrder", models);
		render("/content/order/history_order_list.jsp");
	}

	// 8.购物车内的书确认下单
	@Before(UserInterceptor.class)
	public void create_order_by_shoppingcar() {
		int userid = Integer.parseInt(getPara("user"));
		if (carService.createOrder(userid) == true)
			renderJson(true);
		else
			renderJson(false);
	}

	// 9.查看订单详情
	@Before(UserInterceptor.class)
	public void detail_order() {
		OrderDetailViewModel model = orderService.userDetailOrder(Integer
				.parseInt(getPara("order")));
		setAttr("detailOrder", model);
		render("/content/order/detail_order.jsp");
	}

	// 10.查看购物车
	@Before(UserInterceptor.class)
	public void car_content() {
		int userid = Integer.parseInt(getPara("user"));
		ShoppingCarViewModel model = carService.getAllItem(userid);
		setAttr("shoppingcar", model);
		render("/content/shoppingcar/shopping_car.jsp");
	}

	// 11.多条件查询商品
	@ActionKey("/search")
	public void search() {
		String name = getPara("name");
		String category = getPara("category");
		boolean priceSort = Boolean.parseBoolean(getPara("price"));
		boolean starSort = Boolean.parseBoolean(getPara("star"));
		boolean saleSort = Boolean.parseBoolean(getPara("sale"));
		List<BookViewModel> models = bookService.search(name, category,
				priceSort, starSort, saleSort);
		setAttr("results", models);
		setAttr("type", "search");// 标识商品查询
		render("/content/search/search_result.jsp");
	}

	// 12.热门商品推送
	@ActionKey("/hot_books")
	public void hot_books() {
		List<BookViewModel> models = bookService.getBooks(0);
		setAttr("results", models);
		setAttr("type", "hot");// 标识热门推送
		render("/content/search/search_result.jsp");
	}

	// 13.进入某本书的详情
	@ActionKey("/book_detail")
	public void book_detail() {
		int bookid = Integer.parseInt(getPara("book"));
		BookViewModel bmodel = bookService.findBook(bookid, true);
		setAttr("book", bmodel);
		List<CommentViewModel> cmodel = CommentService.getCommentById(bookid);
		setAttr("comment", cmodel);
		render("/content/search/book_detail.jsp");
	}

}
