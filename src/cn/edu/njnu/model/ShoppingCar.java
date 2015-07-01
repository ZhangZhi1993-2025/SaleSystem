package cn.edu.njnu.model;

import java.sql.SQLException;
import java.util.List;

import cn.edu.njnu.viewmodel.ShoppingDetail;
import cn.edu.njnu.viewmodel.ShoppingInfo;
import static cn.edu.njnu.model.Book.bookDao;
import static cn.edu.njnu.model.Order.orderDao;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.IAtom;
import com.jfinal.plugin.activerecord.Model;

/**
 * 富领域模型ShoppingCar，对应数据库表t_shoppingcar
 */

public class ShoppingCar extends Model<ShoppingCar> {

	private static final long serialVersionUID = 1L;

	// 静态全局变量carDao作为t_shoppingcar表查询的通用入口，简化编程
	public static final ShoppingCar carDao = new ShoppingCar();

	/**
	 * 封装ShoppingCar的数据库操纵接口
	 */

	// 根据用户id及购书内容向购物车里添加物品
	public boolean addItem(int userid, ShoppingDetail detail) {
		return new ShoppingCar().set("userid", userid)
				.set("bookid", detail.getBookid())
				.set("amount", detail.getAmount()).save();
	}

	// 根据用户id移除对应的购物车内物品
	public boolean removeItem(int userid, int bookid) {
		ShoppingCar sc = findFirst(
				"select * from t_shoppingcar s where s.userid = ? and s.bookid = ?",
				userid, bookid);
		return sc.delete();
	}

	// 根据用户id返回购物车里的内容
	public List<ShoppingCar> findItemByUserId(int userid) {
		List<ShoppingCar> list = find(
				"select c.bookid, c.amount from t_shoppingcar c "
						+ "where c.userid = ?", userid);
		return list;
	}

	// 根据用户id将购物车内的书下单
	public boolean createOrder(int userid) {
		return Db.tx(new IAtom() {

			@Override
			public boolean run() throws SQLException {

				// 将购物车内的商品转化为ShoppingDetail[]
				List<ShoppingCar> list = find(
						"select c.bookid, c.amount from t_shoppingcar c "
								+ "where c.userid = ?", userid);
				ShoppingDetail[] details = new ShoppingDetail[list.size()];
				for (int i = 0; i < details.length; i++) {
					int bookid = list.get(i).getInt("bookid");
					List<Book> bookList = bookDao.findBookById(bookid);
					details[i] = new ShoppingDetail(bookid, bookList.get(0)
							.getInt("amount"), bookList.get(0).getDouble(
							"price"));
				}

				// 清空购物车
				deleteById(userid);

				// 下单
				ShoppingInfo info = new ShoppingInfo(userid, details);
				orderDao.createOrder(info);

				return true;
			}
		});
	}
}
