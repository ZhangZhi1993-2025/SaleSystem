package cn.edu.njnu.model;

import java.sql.Timestamp;
import java.sql.SQLException;
import java.util.List;

import cn.edu.njnu.viewmodel.ShoppingDetail;
import cn.edu.njnu.viewmodel.ShoppingInfo;
import static cn.edu.njnu.model.Book.bookDao;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.IAtom;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Record;

/**
 * 富领域模型Order，对应数据库表t_order
 */

public class Order extends Model<Order> {

	private static final long serialVersionUID = 1L;

	// 静态全局变量orderDao作为t_order表查询的通用入口，简化编程
	public static final Order orderDao = new Order();

	/**
	 * 封装Order的数据库操纵接口
	 */

	// 根据用户id号及状态返回订单概要信息
	public List<Order> findOrderByUser(int userid) {
		List<Order> orders = find(
				"select o.createtime, o.price, o.state, o.id from t_order o "
						+ " where o.userid = ?", userid);
		return orders;
	}

	// 根据订单号返回订单详细信息
	public List<Order> findDetailOrderById(int orderid) {
		List<Order> orders = find(
				"select o.createtime, u.phone, o.id, s.name, "
						+ " o.price, from t_user u, t_order o "
						+ " where o.id = ? and o.userid = u.id ", orderid);
		// 获取详细的购买商品
		List<Order> goodsList = find("select ob.amount, b.name, b.price, b.id "
				+ "from t_book b, t_order_books ob where ob.orderid = ? and "
				+ "ob.bookid = b.id ", orderid);
		ShoppingDetail[] booksDetails = new ShoppingDetail[goodsList.size()];
		for (int i = 0; i < goodsList.size(); i++) {
			booksDetails[i] = new ShoppingDetail(goodsList.get(i).getInt("id"),
					goodsList.get(i).getStr("name"), goodsList.get(i).getInt(
							"amount"), goodsList.get(i).getDouble("price"));
		}
		orders.get(0).set("booksDetails", booksDetails);

		return orders;
	}

	// 关闭某订单的活动状态
	public boolean changeOrderState(int orderid) {
		return findById(orderid).set("state", false).update();
	}

	// 生成用户新订单,所需事务内容如下：(1). t_order表新增一条记录;(2).t_order_books新增对应的商品集
	public boolean createOrder(ShoppingInfo info) {
		return Db.tx(new IAtom() {

			@Override
			public boolean run() throws SQLException {

				// 生成总价
				double price = 0;
				for (int i = 0; i < info.getShoppingDetail().length; i++) {
					price += info.getShoppingDetail()[i].getPrice();
				}

				// t_order增加一条记录
				set("userid", info.getUserid())
						.set("state", false)
						.set("price", price)
						.set("createtime",
								new Timestamp(System.currentTimeMillis()))
						.save();

				// t_order_books增加若干条记录，标识用户购买的商品集
				int orderid = find(
						"select o.id from t_order o where o.userid = ?",
						info.getUserid()).get(0).getInt("id");
				Record record;
				for (int i = 0; i < info.getShoppingDetail().length; i++) {
					record = new Record()
							.set("bookid",
									info.getShoppingDetail()[i].getBookid())
							.set("amount",
									info.getShoppingDetail()[i].getAmount())
							.set("orderid", orderid);
					Db.save("t_order_books", record);
					
					// t_book对应书记录的存货量与销量字段发生改变
					bookDao.updateBookData(info.getShoppingDetail()[i]
							.getBookid());
				}
				return true;
			}

		});
	}

}
