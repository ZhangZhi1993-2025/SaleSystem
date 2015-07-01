package cn.edu.njnu.service;

import static cn.edu.njnu.model.ShoppingCar.carDao;
import static cn.edu.njnu.model.Book.bookDao;

import java.text.DecimalFormat;
import java.util.List;

import cn.edu.njnu.model.Book;
import cn.edu.njnu.model.ShoppingCar;
import cn.edu.njnu.viewmodel.ShoppingCarViewModel;
import cn.edu.njnu.viewmodel.ShoppingDetail;

/**
 * *****************************购物车相关服务*****************************************
 * 1.购物车内的书确认下单;2.向购物车增加书;3.移除购物车内某物品;4.查看购物车;
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

	// 3.移除购物车内某物品
	public boolean removeItem(int userid, int bookid) {
		return carDao.removeItem(userid, bookid);
	}

	// 4.查看购物车
	public ShoppingCarViewModel getAllItem(int userid) {
		List<ShoppingCar> result = carDao.findItemByUserId(userid);
		DecimalFormat df = new DecimalFormat("#.##");
		double price = 0;
		String name = "";
		int amount = 0;
		Book book;
		ShoppingDetail[] details = new ShoppingDetail[result.size()];
		for (int i = 0; i < result.size(); i++) {
			int bookid = result.get(i).getInt("bookid");
			book = bookDao.findBookById(bookid).get(0);
			name = book.getStr("name");
			amount = result.get(i).getInt("amount");
			price += book.getDouble("price") * amount;
			details[i] = new ShoppingDetail(bookid, name, amount,
					book.getDouble("price"));
		}
		String priceStr = df.format(price);
		ShoppingCarViewModel model = new ShoppingCarViewModel(
				Double.parseDouble(priceStr), details);
		return model;
	}
}
