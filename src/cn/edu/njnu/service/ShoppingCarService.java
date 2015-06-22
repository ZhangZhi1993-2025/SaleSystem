package cn.edu.njnu.service;

import static cn.edu.njnu.model.ShoppingCar.carDao;
import static cn.edu.njnu.model.Book.bookDao;

import java.util.List;

import cn.edu.njnu.model.Book;
import cn.edu.njnu.model.ShoppingCar;
import cn.edu.njnu.viewmodel.ShoppingCarViewModel;
import cn.edu.njnu.viewmodel.ShoppingDetail;

/**
 * *****************************������ط���*****************************************
 * 1.���ﳵ�ڵ���ȷ���µ�;2.���ﳵ������;3.�鿴���ﳵ;
 */

public class ShoppingCarService {

	// 1.���ﳵ�ڵ���ȷ���µ�
	public boolean createOrder(int userid) {
		return carDao.createOrder(userid);
	}

	// 2.���ﳵ������
	public boolean addItem(int userid, int bookid, int amount) {
		ShoppingDetail detail = new ShoppingDetail(bookid, amount);
		return carDao.addItem(userid, detail);
	}

	// 3.�鿴���ﳵ
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
