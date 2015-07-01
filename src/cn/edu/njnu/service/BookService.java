﻿package cn.edu.njnu.service;

import java.util.ArrayList;
import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

import cn.edu.njnu.model.Book;
import cn.edu.njnu.viewmodel.BookViewModel;
import static cn.edu.njnu.model.Book.bookDao;

/**
 * *****************************图书相关服务*****************************************
 * 0.默认根据销量查找一类书;1.根据书id号查找某本书;2.综合查询;
 */

public class BookService {

	// 0.默认根据销量查找一类书
	public List<BookViewModel> getHotBooks(int page) {
		List<BookViewModel> models = new ArrayList<BookViewModel>();
		List<Book> list = bookDao.pushHotBooks(page);
		String category = "";
		for (int i = 0; i < list.size(); i++) {
			category = bookDao.findCategoryById(list.get(i).getInt("category"));
			models.add(new BookViewModel(list.get(i).getInt("id"), list.get(i)
					.getStr("name"), list.get(i).getDouble("price"), category,
					list.get(i).getInt("sale"), list.get(i).getInt("amount"),
					list.get(i).getDouble("star"), list.get(i).getStr("desc")));
		}
		return models;
	}

	// 1.根据书id号查找某本书
	public BookViewModel findBook(int bookid, boolean isDetail) {
		Book book;
		List<Book> list;
		BookViewModel model;
		// 用于生成订单时查询必要的信息
		if (!isDetail) {
			list = bookDao.findBookById(bookid);
			model = new BookViewModel(bookid, list.get(0).getDouble("price"));
		}// 返回详细的书介绍
		else {
			book = bookDao.findById(bookid);
			String category = bookDao.findCategoryById(book.getInt("category"));
			model = new BookViewModel(bookid, book.getStr("name"),
					book.getDouble("price"), category, book.getInt("sale"),
					book.getInt("amount"), book.getDouble("star"),
					book.getStr("desc"));
		}
		return model;
	}

	// 2.综合查询
	public List<BookViewModel> search(String name, String category,
			boolean priceSort, boolean starSort, boolean saleSort) {
		if (category.equals("全部分类"))
			category = null;
		if (name.equals(""))
			name = null;
		List<Book> list = bookDao.findBookByGivenConditions(name, category,
				priceSort, starSort, saleSort);
		List<BookViewModel> models = new ArrayList<BookViewModel>();
		String category2 = "";
		for (int i = 0; i < list.size(); i++) {
			if (category == null)
				category2 = bookDao.findCategoryById(list.get(i).getInt(
						"category"));
			else
				category2 = category;
			models.add(new BookViewModel(list.get(i).getInt("id"), list.get(i)
					.getStr("name"), list.get(i).getDouble("price"), category2,
					list.get(i).getInt("sale"), list.get(i).getInt("amount"),
					list.get(i).getDouble("star"), list.get(i).getStr("desc")));
		}
		return models;
	}

	// 3.对某本书更新库存
	public boolean updateBookAmount(int bookid, int newamount) {
		return bookDao.updateBook(bookid, 0, newamount);
	}

	// 4.对某本书更新分类
	public boolean updateBookCategory(int bookid, String newcategory) {
		int categoryid = bookDao.findIdByCategory(newcategory);
		if (categoryid == -1) {
			Record record = new Record().set("name", newcategory);
			Db.save("t_category", record);
			categoryid = Db.findFirst(
					"select c.id from t_category c where c.name = ?",
					newcategory).getInt("id");
		}
		return bookDao.updateBook(bookid, 1, categoryid);
	}

	// 5.对某本书更新价格
	public boolean updateBookPrice(int bookid, double newprice) {
		return bookDao.updateBook(bookid, 2, newprice);
	}

	// 6.对某本书更新名字
	public boolean updateBookName(int bookid, String newname) {
		return bookDao.updateBook(bookid, 3, newname);
	}

	// 7.对某本书更新描述
	public boolean updateBookDesc(int bookid, String newdesc) {
		return bookDao.updateBook(bookid, 4, newdesc);
	}

	// 8.对某本书评分
	public boolean scoreBook(int bookid, int star) {
		return bookDao.updateStar(bookid, star);
	}

	// 9.增加一本书
	public boolean addBook(String name, double price, String category,
			int amount, String desc) {
		return bookDao.addBook(name, price, category, amount, desc);
	}

}
