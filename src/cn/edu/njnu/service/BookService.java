package cn.edu.njnu.service;

import java.util.ArrayList;
import java.util.List;

import cn.edu.njnu.model.Book;
import cn.edu.njnu.viewmodel.BookViewModel;
import static cn.edu.njnu.model.Book.bookDao;

/**
 * *****************************������ط���*****************************************
 * 0.Ĭ�ϸ�����������һ����;1.������id�Ų���ĳ����;2.�ۺϲ�ѯ;
 */

public class BookService {

	// 0.Ĭ�ϸ�����������һ����
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

	// 1.������id�Ų���ĳ����
	public BookViewModel findBook(int bookid, boolean isDetail) {
		Book book;
		List<Book> list;
		BookViewModel model;
		// �������ɶ���ʱ��ѯ��Ҫ����Ϣ
		if (!isDetail) {
			list = bookDao.findBookById(bookid);
			model = new BookViewModel(bookid, list.get(0).getDouble("price"));
		}// ������ϸ�������
		else {
			book = bookDao.findById(bookid);
			model = new BookViewModel(bookid, book.getStr("name"),
					book.getDouble("price"), book.getStr("category"),
					book.getInt("sale"), book.getInt("amount"),
					book.getDouble("star"), book.getStr("desc"));
		}
		return model;
	}

	// 2.�ۺϲ�ѯ
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

	// 3.��ĳ������¿��
	public boolean updateBookAmount(int bookid, int addition) {
		return bookDao.updateBook(bookid, 0, addition);
	}
}
