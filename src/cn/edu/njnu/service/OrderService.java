﻿package cn.edu.njnu.service;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.IAtom;
import com.jfinal.plugin.activerecord.Record;

import cn.edu.njnu.model.Book;
import cn.edu.njnu.model.Order;
import cn.edu.njnu.viewmodel.FeedbackViewModel;
import cn.edu.njnu.viewmodel.ShoppingInfo;
import cn.edu.njnu.viewmodel.OrderViewModel;
import cn.edu.njnu.viewmodel.OrderDetailViewModel;
import static cn.edu.njnu.model.Order.orderDao;
import static cn.edu.njnu.model.Book.bookDao;
import static cn.edu.njnu.model.User.usrDao;

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
	public List<OrderViewModel> userGetOrder(int userid, int state) {
		List<Order> result = new ArrayList<Order>();
		result = orderDao.findOrderByUser(userid, state);
		List<OrderViewModel> models = new ArrayList<OrderViewModel>();
		for (int i = 0; i < result.size(); i++) {
			models.add(new OrderViewModel(result.get(i).getInt("id"),
					(Timestamp) result.get(i).get("createtime"), result.get(i)
							.getBoolean("state"), result.get(i).getDouble(
							"price"), userid));
		}
		return models;
	}

	/* 3.用户查看订单详情 */
	public OrderDetailViewModel userDetailOrder(int orderid) {
		return orderDao.findDetailOrderById(orderid);
	}

	/* 4.待评价商品列表 */
	public List<FeedbackViewModel> getTobeComment(int userid) {
		List<Record> records = orderDao.findToBeComment(userid);
		List<FeedbackViewModel> models = new ArrayList<FeedbackViewModel>();
		int bookid = 0;
		String category = "";
		for (int i = 0; i < records.size(); i++) {
			bookid = records.get(i).getInt("bookid");
			Book book = bookDao.findById(bookid);
			category = bookDao.findCategoryById(book.getInt("category"));
			models.add(new FeedbackViewModel(records.get(i).getInt("orderid"),
					bookid, book.getStr("name"), book.getDouble("price"),
					category));
		}
		return models;
	}

	/* 5.改变某本书的订单状态 */
	public boolean makeCompleteOrder(int orderid) {
		return Db.tx(new IAtom() {

			@Override
			public boolean run() throws SQLException {
				orderDao.changeOrderState(orderid);
				double price = orderDao.findById(orderid).getDouble("price");
				int userid = orderDao.findById(orderid).getInt("userid");
				int score = usrDao.findById(userid).getInt("score");
				score = (int) price + score;
				usrDao.updateScore(userid, score);
				return true;
			}
		});
	}

	// 6.使评论状态变为 已评论
	public boolean makeisComment(int userid, int orderid, int bookid) {
		return orderDao.makeisComment(userid, orderid, bookid);
	}
}
