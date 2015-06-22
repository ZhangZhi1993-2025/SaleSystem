package cn.edu.njnu.controller;

import java.util.List;

import cn.edu.njnu.controller.interceptor.UserInterceptor;
import cn.edu.njnu.service.BookService;
import cn.edu.njnu.service.OrderService;
import cn.edu.njnu.service.ShoppingCarService;
import cn.edu.njnu.service.UserService;
import cn.edu.njnu.viewmodel.BookViewModel;
import cn.edu.njnu.viewmodel.OrderDetailViewModel;
import cn.edu.njnu.viewmodel.ShoppingCarViewModel;
import cn.edu.njnu.viewmodel.ShoppingDetail;
import cn.edu.njnu.viewmodel.ShoppingInfo;
import cn.edu.njnu.viewmodel.OrderViewModel;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;

/**
 * ********************UserController(个人中心 )****************************
 * 1.修改密码;2.修改绑定手机号;3.将物品放入购物车;4.注销账户;5.确认下单;6.当前订单列表;7.历史订单列表;8.购物车内的书确认下单;
 * 9.查看订单详情;10.查看购物车;11.多条件查询商品;
 * ************************************************************
 * UserController的访问权限：任意已经登陆的用户;
 */

@Before(UserInterceptor.class)
public class UserController extends Controller {

	UserService userService = new UserService();
	OrderService orderService = new OrderService();
	BookService bookService = new BookService();
	ShoppingCarService carService = new ShoppingCarService();

	// 1.修改密码
	public void change_password() {
		int userid = Integer.parseInt(getPara("user"));
		String newpassword = getPara("newpassword");
		if (userService.userUpdate(userid, newpassword, 0) == true)
			renderJson(true);
		else
			renderJson(false);
	}

	// 2.修改绑定手机号
	public void change_phone_number() {
		int userid = Integer.parseInt(getPara("user"));
		String newphone = getPara("newphone");
		if (userService.userUpdate(userid, newphone, 0) == true)
			renderJson(true);
		else
			renderJson(false);
	}

	// 3.将物品放入购物车
	public void add_item_to_car() {
		int userid = Integer.parseInt(getPara("user"));
		int bookid = Integer.parseInt(getPara("book"));
		int amount = Integer.parseInt(getPara("amount"));
		carService.addItem(userid, bookid, amount);
	}

	// 4.注销账户
	public void delete_account() {
		int userid = Integer.parseInt(getPara("userid"));
		if (userService.userDelete(userid) == true)
			renderJson(true);
		else
			renderJson(false);
	}

	// 5.确认下单
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
	public void current_order() {
		int userid = Integer.parseInt(getPara("userid"));
		List<OrderViewModel> models = orderService.userGetOrder(userid);
		renderJson(models);
	}

	// 7.历史订单列表
	public void history_order() {
		int userid = Integer.parseInt(getPara("userid"));
		List<OrderViewModel> models = orderService.userGetOrder(userid);
		renderJson(models);
	}

	// 8.购物车内的书确认下单
	public void create_order_by_shoppingcar() {
		int userid = Integer.parseInt(getPara("user"));
		carService.createOrder(userid);
	}

	// 9.查看订单详情
	public void detail_order() {
		OrderDetailViewModel model = orderService.userDetailOrder(Integer
				.parseInt(getPara("id")));
		renderJson(model);
	}

	// 10.查看购物车
	public void car_content() {
		int userid = Integer.parseInt(getPara("userid"));
		ShoppingCarViewModel model = carService.getAllItem(userid);
		renderJson(model);
	}

	// 11.多条件查询商品
	public void search() {
		String name = getPara("name");
		String category = getPara("category");
		boolean priceSort = Boolean.parseBoolean(getPara("price"));
		boolean starSort = Boolean.parseBoolean(getPara("star"));
		boolean saleSort = Boolean.parseBoolean(getPara("sale"));
		List<BookViewModel> models = bookService.search(name, category,
				priceSort, starSort, saleSort);
		renderJson(models);
	}

}
