package cn.edu.njnu.controller;

import java.util.List;

import javax.servlet.http.Cookie;

import cn.edu.njnu.controller.interceptor.UserInterceptor;
import cn.edu.njnu.service.BookService;
import cn.edu.njnu.service.CommentService;
import cn.edu.njnu.service.OrderService;
import cn.edu.njnu.service.ShoppingCarService;
import cn.edu.njnu.service.UserService;
import static cn.edu.njnu.service.UserService.REG_SUCCESS;
import cn.edu.njnu.viewmodel.BookViewModel;
import cn.edu.njnu.viewmodel.CommentViewModel;
import cn.edu.njnu.viewmodel.OrderDetailViewModel;
import cn.edu.njnu.viewmodel.ShoppingCarViewModel;
import cn.edu.njnu.viewmodel.ShoppingDetail;
import cn.edu.njnu.viewmodel.ShoppingInfo;
import cn.edu.njnu.viewmodel.OrderViewModel;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;

/**
 * ********************UserController(个人中心 )****************************
 * 1.修改密码;2.修改绑定手机号;3.将物品放入购物车;4.注销账户;5.确认下单;6.当前订单列表;7.历史订单列表;8.购物车内的书确认下单;
 * 9.查看订单详情;10.查看购物车;11.多条件查询商品;12.热门商品推送;13.进入某本书的详情;14.给某本书评分并评论;
 * ************************************************************
 * UserController的访问权限：任意已经登陆的用户;
 */

public class UserController extends Controller {

	UserService userService = new UserService();
	OrderService orderService = new OrderService();
	BookService bookService = new BookService();
	ShoppingCarService carService = new ShoppingCarService();
	CommentService commentService = new CommentService();

	// 1.修改密码
	@Before(UserInterceptor.class)
	public void change_password() {
		int userid = Integer.parseInt(getPara("user"));
		String oldpassword = getPara("oldpassword");
		String newpassword = getPara("newpassword");
		if (userService
				.userLogin(userService.getUserPhone(userid), oldpassword) == true) {
			if (userService.userUpdate(userid, newpassword, 0) == true) {
				render("/content/user/account.jsp");
			} else
				renderJson("修改失败,请稍后重试!");
		} else
			renderJson("原密码输入错误，请重试!");
	}

	// 2.修改手机号
	@Before(UserInterceptor.class)
	public void change_phone_number() {
		int userid = Integer.parseInt(getPara("user"));
		String newphone = getPara("newphone");
		if (userService.userRegister(newphone) == REG_SUCCESS) {
			if (userService.userUpdate(userid, newphone, 1) == true) {
				render("/content/user/account.jsp");
			} else
				renderJson("修改失败,请稍后重试!");
		} else
			renderJson("新用户名不能被注册!");
	}

	// 修改昵称
	@Before(UserInterceptor.class)
	public void change_name() {
		int userid = Integer.parseInt(getPara("user"));
		String newname = getPara("newname");
		if (userService.userUpdate(userid, newname, 2) == true) {
			Cookie[] cookies = getRequest().getCookies();
			for (int i = 0; i < cookies.length; i++) {
				if (cookies[i].getName().equals("name")) {
					cookies[i].setValue(newname);
					cookies[i].setPath("/");
					getResponse().addCookie(cookies[i]);
					break;
				}
			}
			render("/content/user/account.jsp");
		} else
			renderJson("修改失败,请稍后重试!");
	}

	// 账户修改页面
	@Before(UserInterceptor.class)
	public void update_account() {
		render("/content/user/account.jsp");
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

	// 将物品移除购物车
	@Before(UserInterceptor.class)
	public void remove_item_from_car() {
		int userid = Integer.parseInt(getPara("user"));
		int bookid = Integer.parseInt(getPara("book"));
		carService.removeItem(userid, bookid);
		String url = "/user/car_content?user=" + userid;
		redirect(url);
	}

	// 4.注销账户
	@Before(UserInterceptor.class)
	public void delete_account() {
		int userid = Integer.parseInt(getPara("user"));
		if (userService.userDelete(userid) == true)
			redirect("/");
		else
			renderJson(false);
	}

	// 退出登陆
	@Before(UserInterceptor.class)
	public void logout() {
		Cookie[] cookies = getRequest().getCookies();
		for (int i = 0; i < cookies.length; i++) {
			cookies[i].setValue("");
			cookies[i].setMaxAge(0);
			cookies[i].setPath("/");
			getResponse().addCookie(cookies[i]);
		}
		redirect("/");
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
		if (orderService.createOrder(info) == true) {
			String url = "/user/current_order?user=" + userid;
			redirect(url);
		} else
			renderJson("下单失败，请稍后重试!");
	}

	// 6.当前订单列表
	@Before(UserInterceptor.class)
	public void current_order() {
		int userid = Integer.parseInt(getPara("user"));
		List<OrderViewModel> models = orderService.userGetOrder(userid);
		setAttr("currentOrder", models);
		render("/content/user/current_order_list.jsp");
	}

	// 7.历史订单列表
	@Before(UserInterceptor.class)
	public void history_order() {
		int userid = Integer.parseInt(getPara("user"));
		List<OrderViewModel> models = orderService.userGetOrder(userid);
		setAttr("historyOrder", models);
		render("/content/user/history_order_list.jsp");
	}

	// 8.购物车内的书确认下单
	@Before(UserInterceptor.class)
	public void create_order_by_shoppingcar() {
		int userid = Integer.parseInt(getPara("user"));
		if (carService.createOrder(userid) == true) {
			String url = "/user/current_order?user=" + userid;
			redirect(url);
		} else
			renderJson("下单失败，请稍后重试!");
	}

	// 9.查看订单详情
	@Before(UserInterceptor.class)
	public void detail_order() {
		OrderDetailViewModel model = orderService.userDetailOrder(Integer
				.parseInt(getPara("order")));
		setAttr("detailOrder", model);
		render("/content/user/detail_order.jsp");
	}

	// 10.查看购物车
	@Before(UserInterceptor.class)
	public void car_content() {
		int userid = Integer.parseInt(getPara("user"));
		ShoppingCarViewModel model = carService.getAllItem(userid);
		setAttr("shoppingcar", model);
		render("/content/user/shopping_car.jsp");
	}

	// 11.多条件查询商品
	@ActionKey("/search")
	public void search() {
		String name = getPara("keywords");
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
		int page = Integer.parseInt(getPara("page"));
		List<BookViewModel> models = bookService.getBooks(page);
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
		List<CommentViewModel> cmodel = commentService.getCommentById(bookid);
		setAttr("comment", cmodel);
		render("/content/search/book_detail.jsp");
	}

	// 14.给某本书评分并评论
	public void feedback_book() {
		int star = Integer.parseInt(getPara("star"));
		int bookid = Integer.parseInt(getPara("book"));
		int userid = Integer.parseInt(getPara("user"));
		String comment = getPara("comment");
		if (star != 0)
			bookService.scoreBook(bookid, star);
		if (comment != null)
			commentService.commentBook(userid, bookid, comment);
		String url = "/user/feedback?user=" + userid;
		redirect(url);
	}

	// 待评价列表
	@Before(UserInterceptor.class)
	public void feedback() {
		int userid = Integer.parseInt(getPara("user"));
		List<BookViewModel> models = orderService.getTobeComment(userid);
		setAttr("books", models);
		render("/content/user/feedback.jsp");
	}

	// 对某本书的评价页面
	@Before(UserInterceptor.class)
	public void feedback_detail() {
		int bookid = Integer.parseInt(getPara("book"));
		setAttr("book", bookid);
		render("/content/user/feedback_detail.jsp");
	}

}
