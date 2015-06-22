package cn.edu.njnu.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import cn.edu.njnu.model.Order;
import cn.edu.njnu.viewmodel.ShoppingDetail;
import cn.edu.njnu.viewmodel.ShoppingInfo;
import cn.edu.njnu.viewmodel.OrderViewModel;
import cn.edu.njnu.viewmodel.OrderDetailViewModel;
import static cn.edu.njnu.model.Order.orderDao;

/**
 * *****************************������ط���*****************************************
 * 1.�û������¶���;2.�û��鿴������Ҫ;3.�û��鿴��������;
 */

public class OrderService {

	/* 1.�����¶��� */
	public boolean createOrder(ShoppingInfo info) {
		return orderDao.createOrder(info);
	}

	/* 2.�û��鿴���� */
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

	/* 3.�û��鿴�������� */
	public OrderDetailViewModel userDetailOrder(int orderid) {
		List<Order> result = orderDao.findDetailOrderById(orderid);
		OrderDetailViewModel model = new OrderDetailViewModel(result.get(0)
				.getInt("id"), (Timestamp) result.get(0).get("createtime"),
				result.get(0).getStr("phone"),
				result.get(0).getDouble("price"), (ShoppingDetail[]) result
						.get(0).get("shoppingDetails"));
		return model;
	}
}
