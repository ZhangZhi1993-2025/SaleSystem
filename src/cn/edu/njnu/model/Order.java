package cn.edu.njnu.model;

import java.sql.Timestamp;
import java.sql.SQLException;
import java.util.List;

import cn.edu.njnu.viewmodel.OrderDetailViewModel;
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
	public List<Order> findOrderByUser(int userid, int state) {
		List<Order> orders = find(
				"select o.createtime, o.price, o.state, o.id from t_order o "
						+ " where o.userid = ? and o.state = ?", userid, state);
		return orders;
	}

	// 根据订单号返回订单详细信息
	public OrderDetailViewModel findDetailOrderById(int orderid) {
		Order orders = findFirst("select o.createtime, u.phone, o.id, "
				+ " o.price from t_user u, t_order o "
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
		OrderDetailViewModel model = new OrderDetailViewModel(
				orders.getInt("id"), (Timestamp) orders.get("createtime"),
				orders.getStr("phone"), orders.getDouble("price"), booksDetails);

		return model;
	}

	// 关闭某订单的活动状态
	public boolean changeOrderState(int orderid) {
		return findById(orderid).set("state", false).update();
	}

	// 使评论状态变为 已评论
	public boolean makeisComment(int userid, int orderid, int bookid) {
		return Db.update("update t_order_books set iscomment = true "
				+ " where userid = ? and orderid = ? and bookid = ?", userid,
				orderid, bookid) == 1;
	}

	// 根据用户id查询已购买但未评论的商品
	public List<Record> findToBeComment(int userid) {
		return Db.find(
				"select ob.bookid, ob.orderid from t_order_books ob where "
						+ " ob.userid = ? and ob.iscomment = false", userid);
	}

	// 生成用户新订单,所需事务内容如下：(1). t_order表新增一条记录;(2).t_order_books新增对应的商品集
	public boolean createOrder(ShoppingInfo info) {
		return Db.tx(new IAtom() {

			@Override
			public boolean run() throws SQLException {

				// 生成总价
				double price = 0;
				for (int i = 0; i < info.getShoppingDetail().length; i++) {
					price += info.getShoppingDetail()[i].getPrice()
							* info.getShoppingDetail()[i].getAmount();
				}

				// t_order增加一条记录
				new Order()
						.set("userid", info.getUserid())
						.set("state", true)
						// 订单是激活的
						.set("price", price)
						.set("createtime",
								new Timestamp(System.currentTimeMillis()))
						.save();

				// t_order_books增加若干条记录，标识用户购买的商品集
				List<Order> orders = find(
						"select o.id from t_order o where o.userid = ?",
						info.getUserid());
				int orderid = orders.get(orders.size() - 1).getInt("id");

				Record record;
				for (int i = 0; i < info.getShoppingDetail().length; i++) {
					record = new Record()
							.set("bookid",
									info.getShoppingDetail()[i].getBookid())
							.set("amount",
									info.getShoppingDetail()[i].getAmount())
							.set("orderid", orderid).set("iscomment", false)
							.set("userid", info.getUserid());// 冗余是为了提高查询效率
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
