package cn.edu.njnu.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

import cn.edu.njnu.model.Book;
import cn.edu.njnu.model.Order;
import cn.edu.njnu.viewmodel.BookViewModel;
import cn.edu.njnu.viewmodel.ShoppingDetail;
import cn.edu.njnu.viewmodel.ShoppingInfo;
import cn.edu.njnu.viewmodel.OrderViewModel;
import cn.edu.njnu.viewmodel.OrderDetailViewModel;
import static cn.edu.njnu.model.Order.orderDao;
import static cn.edu.njnu.model.Book.bookDao;

/**
 * *****************************订单相关服务*****************************************
 * 1.用户生成新订单;2.用户查看订单概要;3.用户查看订单详情;
 */

public class OrderService {

	/* 1.生成新订单 */
	public boolean createOrder(ShoppingInfo info) {
		return orderDao.createOrder(info);
	}

	/* 2.用户查看订单 */
	public List<OrderViewModel> userGetOrder(int userid) {
		List<Order> result = new ArrayList<Order>();
		result = orderDao.findOrderByUser(userid);
		List<OrderViewModel> models = new ArrayList<OrderViewModel>();
		for (int i = 0; i < result.size(); i++) {
			models.add(new OrderViewModel(result.get(i).getInt("id"),
					(Timestamp) result.get(0).get("createtime"), result.get(i)
							.getBoolean("state"), result.get(i).getDouble(
							"price"), result.get(i).getInt("userid")));
		}
		return models;
	}

	/* 3.用户查看订单详情 */
	public OrderDetailViewModel userDetailOrder(int orderid) {
		List<Order> result = orderDao.findDetailOrderById(orderid);
		OrderDetailViewModel model = new OrderDetailViewModel(result.get(0)
				.getInt("id"), (Timestamp) result.get(0).get("createtime"),
				result.get(0).getStr("phone"),
				result.get(0).getDouble("price"), (ShoppingDetail[]) result
						.get(0).get("shoppingDetails"));
		return model;
	}

	/* 4.待评价商品列表 */
	public List<BookViewModel> getTobeComment(int userid) {
		List<Record> records = orderDao.findToBeComment(userid);
		List<BookViewModel> models = new ArrayList<BookViewModel>();
		int bookid = 0;
		for (int i = 0; i < records.size(); i++) {
			bookid = records.get(i).getInt("bookid");
			Book book = bookDao.findById(bookid);
			models.add(new BookViewModel(bookid, book.getStr("name"), book
					.getDouble("price")));
		}
		return models;
	}
}
