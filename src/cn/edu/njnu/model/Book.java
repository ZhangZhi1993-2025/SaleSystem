package cn.edu.njnu.model;

import java.util.List;

import com.jfinal.plugin.activerecord.Model;

/**
 * 富领域模型Book，对应数据库表t_book
 */

public class Book extends Model<Book> {

	private static final long serialVersionUID = 1L;

	// 静态全局变量bookDao作为t_book表查询的通用入口，简化编程
	public static final Book bookDao = new Book();

	/**
	 * 封装Book的数据库操纵接口
	 */

	// 返回销量最高的前20本书
	public List<Book> pushHotBooks(int page) {
		return paginate(
				page,
				20,
				"select b.id, b.name, b.price, b.category, b.amount, b.star, b.desc, b.sale ",
				"from t_book b orderby b.sale asc").getList();
	}

	// 根据某本书的id号返回概要内容
	public List<Book> findBookById(int bookid) {
		List<Book> books = find("select b.id, b.name, b.price, b.amount from "
				+ "t_book where b.id = ?", bookid);
		return books;
	}

	// 下单后修改状态
	public boolean updateBookData(int bookid) {
		List<Book> list = find("select b.amount, b.sale from t_book b where "
				+ "b.id = ?", bookid);
		int amount = list.get(0).getInt("id");
		int sale = list.get(0).getInt("sale");
		return set("amount", amount - 1).set("sale", sale + 1).save();
	}

	// 管理员增加书入库存货量
	public boolean updateBookAmount(int bookid, int addition) {
		List<Book> list = find("select b.amount from t_book b where "
				+ "b.id = ?", bookid);
		int amount = list.get(0).getInt("id");
		return set("amount", amount + addition).save();
	}

	// 根据给定条件综合查询
	public List<Book> findBookByGivenConditions(String name, String category,
			boolean priceSort, boolean starSort, boolean saleSort) {
		List<Book> list;
		String sqlSelect = "select b.id, b.name, b.price, b.category, b.amount, b.star, "
				+ "b.desc, b.sale from t_book b ";
		// 拼接查询字符串
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
