package cn.edu.njnu.service;

import static cn.edu.njnu.model.ShoppingCar.carDao;
import static cn.edu.njnu.model.Book.bookDao;

import java.util.List;

import cn.edu.njnu.model.Book;
import cn.edu.njnu.model.ShoppingCar;
import cn.edu.njnu.viewmodel.ShoppingCarViewModel;
import cn.edu.njnu.viewmodel.ShoppingDetail;

/**
 * *****************************订单相关服务*****************************************
 * 1.购物车内的书确认下单;2.向购物车增加书;3.查看购物车;
 */

public class ShoppingCarService {

	// 1.购物车内的书确认下单
	public boolean createOrder(int userid) {
		return carDao.createOrder(userid);
	}

	// 2.向购物车增加书
	public boolean addItem(int userid, int bookid, int amount) {
		ShoppingDetail detail = new ShoppingDetail(bookid, amount);
		return carDao.addItem(userid, detail);
	}

	// 3.查看购物车
	public ShoppingCarViewModel getAllItem(int userid) {
		List<ShoppingCar> result = carDao.findItemByUserId(userid);
		double price = 0;
		String name = "";
		Book book;
		ShoppingDetail[] details = new ShoppingDetail[result.size()];
		for (int i = 0; i < result.size(); i++) {
			int bookid = result.get(i).getInt("bookid");
			book = bookDao.findBookById(bookid).get(0);
			name = book.getStr("name");
			price += book.getDouble("price");
			details[i] = new ShoppingDetail(bookid, name, result.get(i).getInt(
					"amount"), book.getDouble("price"));
		}
		ShoppingCarViewModel model = new ShoppingCarViewModel(price, details);
		return model;
	}
}
