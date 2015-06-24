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
import cn.edu.njnu.viewmodel.UserViewModel;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;

/**
 * ********************UserController(�������� )****************************
 * 1.�޸�����;2.�޸İ��ֻ���;3.����Ʒ���빺�ﳵ;4.ע���˻�;5.ȷ���µ�;6.��ǰ�����б�;7.��ʷ�����б�;8.���ﳵ�ڵ���ȷ���µ�;
 * 9.�鿴��������;10.�鿴���ﳵ;11.��������ѯ��Ʒ;
 * ************************************************************
 * UserController�ķ���Ȩ�ޣ������Ѿ���½���û�;
 */

@Before(UserInterceptor.class)
public class UserController extends Controller {

	UserService userService = new UserService();
	OrderService orderService = new OrderService();
	BookService bookService = new BookService();
	ShoppingCarService carService = new ShoppingCarService();

	// 1.�޸�����
	public void change_password() {
		int userid = Integer.parseInt(getPara("user"));
		String newpassword = getPara("newpassword");
		if (userService.userUpdate(userid, newpassword, 0) == true) {
			String phone = userService.getUserPhone(userid);
			UserViewModel model;
			model = userService.getUserInfo(phone);
			setAttr("userInfo", model);
			redirect("/WEB-INF/content/main.jsp");
		} else
			renderJson(false);
	}

	// 2.�޸İ��ֻ���
	public void change_phone_number() {
		int userid = Integer.parseInt(getPara("user"));
		String newphone = getPara("newphone");
		if (userService.userUpdate(userid, newphone, 1) == true) {
			UserViewModel model;
			model = userService.getUserInfo(newphone);
			setAttr("userInfo", model);
			redirect("/WEB-INF/content/main.jsp");
		} else
			renderJson(false);
	}

	// 3.����Ʒ���빺�ﳵ
	public void add_item_to_car() {
		int userid = Integer.parseInt(getPara("user"));
		int bookid = Integer.parseInt(getPara("book"));
		int amount = Integer.parseInt(getPara("amount"));
		carService.addItem(userid, bookid, amount);
		renderJson(true);
	}

	// 4.ע���˻�
	public void delete_account() {
		int userid = Integer.parseInt(getPara("userid"));
		if (userService.userDelete(userid) == true)
			redirect("/WEB-INF/content/main.jsp");
		else
			renderJson(false);
	}

	// 5.ȷ���µ�
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

	// 6.��ǰ�����б�
	public void current_order() {
		int userid = Integer.parseInt(getPara("userid"));
		List<OrderViewModel> models = orderService.userGetOrder(userid);
		setAttr("currentOrder", models);
		render("/WEB-INF/content/order/current_order_list.jsp");
	}

	// 7.��ʷ�����б�
	public void history_order() {
		int userid = Integer.parseInt(getPara("userid"));
		List<OrderViewModel> models = orderService.userGetOrder(userid);
		setAttr("historyOrder", models);
		render("/WEB-INF/content/order/history_order_list.jsp");
	}

	// 8.���ﳵ�ڵ���ȷ���µ�
	public void create_order_by_shoppingcar() {
		int userid = Integer.parseInt(getPara("user"));
		if (carService.createOrder(userid) == true)
			renderJson(true);
		else
			renderJson(false);
	}

	// 9.�鿴��������
	public void detail_order() {
		OrderDetailViewModel model = orderService.userDetailOrder(Integer
				.parseInt(getPara("id")));
		setAttr("detailOrder", model);
		render("/WEB-INF/content/order/detail_order.jsp");
	}

	// 10.�鿴���ﳵ
	public void car_content() {
		int userid = Integer.parseInt(getPara("userid"));
		ShoppingCarViewModel model = carService.getAllItem(userid);
		setAttr("shoppingcar", model);
		render("/WEB-INF/content/shoppingcar/shopping_car.jsp");
	}

	// 11.��������ѯ��Ʒ
	public void search() {
		String name = getPara("name");
		String category = getPara("category");
		boolean priceSort = Boolean.parseBoolean(getPara("price"));
		boolean starSort = Boolean.parseBoolean(getPara("star"));
		boolean saleSort = Boolean.parseBoolean(getPara("sale"));
		List<BookViewModel> models = bookService.search(name, category,
				priceSort, starSort, saleSort);
		setAttr("searchResult", models);
		render("/WEB-INF/content/search/search_result.jsp");
	}

}
