package cn.edu.njnu.model;

import java.util.List;

import com.jfinal.plugin.activerecord.Model;

/**
 * ������ģ��Book����Ӧ���ݿ��t_book
 */

public class Book extends Model<Book> {

	private static final long serialVersionUID = 1L;

	// ��̬ȫ�ֱ���bookDao��Ϊt_book���ѯ��ͨ����ڣ��򻯱��
	public static final Book bookDao = new Book();

	/**
	 * ��װBook�����ݿ���ݽӿ�
	 */

	// ����������ߵ�ǰ20����
	public List<Book> pushHotBooks(int page) {
		return paginate(
				page,
				20,
				"select b.id, b.name, b.price, b.category, b.amount, b.star, b.desc, b.sale ",
				"from t_book b orderby b.sale asc").getList();
	}

	// ����ĳ�����id�ŷ��ظ�Ҫ����
	public List<Book> findBookById(int bookid) {
		List<Book> books = find("select b.id, b.name, b.price, b.amount from "
				+ "t_book where b.id = ?", bookid);
		return books;
	}

	// �µ����޸�״̬
	public boolean updateBookData(int bookid) {
		List<Book> list = find("select b.amount, b.sale from t_book b where "
				+ "b.id = ?", bookid);
		int amount = list.get(0).getInt("id");
		int sale = list.get(0).getInt("sale");
		return set("amount", amount - 1).set("sale", sale + 1).save();
	}

	// ����Ա���������Ϣ
	public boolean updateBook(int bookid, int type, Object info) {
		boolean success = false;
		List<Book> list;
		switch (type) {
		case 0:// ������Ŀ��
			list = find("select b.amount from t_book b where " + "b.id = ?",
					bookid);
			int amount = list.get(0).getInt("id");
			success = set("amount", amount + (int) info).save();
		case 1:// ������ķ���
			success = findById(bookid).set("category", (String) info).update();
		case 2:// ������ļ۸�
			success = findById(bookid).set("price", (double) info).update();
		case 3:// �����������
			success = findById(bookid).set("name", (String) info).update();
		case 4:// �����������
			success = findById(bookid).set("desc", (String) info).update();
		default:
		}
		return success;
	}

	// �û�Ϊĳ��������
	public boolean updateStar(int bookid, double star) {
		Book book = findById(bookid);
		int star_number = book.getInt("star_number");
		double oldStar = book.getDouble("star");
		return book
				.set("star_number", star_number + 1)
				.set("star", (oldStar * star_number + star) / (star_number + 1))
				.update();
	}

	// ���ݸ��������ۺϲ�ѯ
	public List<Book> findBookByGivenConditions(String name, String category,
			boolean priceSort, boolean starSort, boolean saleSort) {
		List<Book> list;
		String sqlSelect = "select b.id, b.name, b.price, b.category, b.amount, b.star, "
				+ "b.desc, b.sale from t_book b ";
		// ƴ�Ӳ�ѯ�ַ���
		StringBuffer sqlCondition = new StringBuffer("");
		if (name != null || category != null) {
			sqlCondition.append("where ");
			if (name != null)
				sqlCondition.append("name = ? ");
			if (category != null)
				sqlCondition.append("and category = ? ");
		}
		if (priceSort || starSort || saleSort) {
			sqlCondition.append("order by ");
			if (priceSort)
				sqlCondition.append("price asc");
			if (starSort)
				sqlCondition.append(", star desc");
			if (saleSort)
				sqlCondition.append(", sale desc");
		}
		String sql = sqlSelect + sqlCondition.toString();
		if (name != null && category != null)
			list = find(sql, name, category);
		else if (name != null)
			list = find(sql, name);
		else if (category != null)
			list = find(sql, category);
		else
			list = find(sql);
		return list;
	}

}
