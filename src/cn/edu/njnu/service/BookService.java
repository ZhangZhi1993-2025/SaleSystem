package cn.edu.njnu.service;

import java.util.ArrayList;
import java.util.List;

import cn.edu.njnu.model.Book;
import cn.edu.njnu.viewmodel.BookViewModel;
import static cn.edu.njnu.model.Book.bookDao;

/**
 * *****************************订单相关服务*****************************************
 * 0.默认根据销量查找一类书;1.根据书id号查找某本书;2.综合查询;
 */

public class BookService {

	// 0.默认根据销量查找一类书
	public List<BookViewModel> getBooks(int page) {
		List<BookViewModel> models = new ArrayList<BookViewModel>();
		List<Book> list = bookDao.pushHotBooks(page);
		for (int i = 0; i < list.size(); i++) {
			models.add(new BookViewModel(list.get(i).getInt("id"), list.get(i)
					.getStr("name"), list.get(i).getDouble("price"), list
					.get(i).getStr("category"), list.get(i).getInt("sale"),
					list.get(i).getInt("amount"),
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
			model = new BookViewModel(bookid, book.getStr("name"),
					book.getDouble("price"), book.getStr("category"),
					book.getInt("sale"), book.getInt("amount"),
					book.getDouble("star"), book.getStr("desc"));
		}
		return model;
	}

	// 2.综合查询
	public List<BookViewModel> search(String name, String category,
			boolean priceSort, boolean starSort, boolean saleSort) {
		List<Book> list = bookDao.findBookByGivenConditions(name, category,
				priceSort, starSort, saleSort);
		List<BookViewModel> models = new ArrayList<BookViewModel>();
		for (int i = 0; i < list.size(); i++) {
			models.add(new BookViewModel(list.get(i).getInt("id"), list.get(i)
					.getStr("name"), list.get(i).getDouble("price"), list
					.get(i).getStr("category"), list.get(i).getInt("sale"),
					list.get(i).getInt("amount"),
					list.get(i).getDouble("star"), list.get(i).getStr("desc")));
		}
		return models;
	}

	// 3.对某本书更新库存
	public boolean updateBookAmount(int bookid, int addition) {
		return bookDao.updateBook(bookid, 0, addition);
	}
}
