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
 * ������ģ��Order����Ӧ���ݿ��t_order
 */

public class Order extends Model<Order> {

	private static final long serialVersionUID = 1L;

	// ��̬ȫ�ֱ���orderDao��Ϊt_order���ѯ��ͨ����ڣ��򻯱��
	public static final Order orderDao = new Order();

	/**
	 * ��װOrder�����ݿ���ݽӿ�
	 */

	// �����û�id�ż�״̬���ض�����Ҫ��Ϣ
	public List<Order> findOrderByUser(int userid) {
		List<Order> orders = find(
				"select o.createtime, o.price, o.state, o.id from t_order o"
						+ "where o.userid = ?", userid);
		return orders;
	}

	// ���ݶ����ŷ��ض�����ϸ��Ϣ
	public List<Order> findDetailOrderById(int orderid) {
		List<Order> orders = find(
				"select o.createtime, u.phone, o.id, s.name, "
						+ "o.price, from t_user u, t_order o "
						+ "where o.id = ? and o.userid = u.id ", orderid);
		// ��ȡ��ϸ�Ĺ�����Ʒ
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

	// �ر�ĳ�����Ļ״̬
	public boolean changeOrderState(int orderid) {
		return findById(orderid).set("state", false).update();
	}

	// �����û��¶���,���������������£�(1). t_order������һ����¼;(2).t_order_books������Ӧ����Ʒ��
	public boolean createOrder(ShoppingInfo info) {
		return Db.tx(new IAtom() {

			@Override
			public boolean run() throws SQLException {

				// �����ܼ�
				double price = 0;
				for (int i = 0; i < info.getShoppingDetail().length; i++) {
					price += info.getShoppingDetail()[i].getPrice();
				}

				// t_order����һ����¼
				set("userid", info.getUserid())
						.set("state", false)
						.set("price", price)
						.set("createtime",
								new Timestamp(System.currentTimeMillis()))
						.save();

				// t_order_books������������¼����ʶ�û��������Ʒ��
				int orderid = find(
						"select o.id from t_order o where o.userid = ?",
						info.getUserid()).get(0).getInt("id");
				Record goodsRecord;
				for (int i = 0; i < info.getShoppingDetail().length; i++) {
					goodsRecord = new Record()
							.set("bookid",
									info.getShoppingDetail()[i].getBookid())
							.set("amount",
									info.getShoppingDetail()[i].getAmount())
							.set("orderid", orderid);
					Db.save("t_order_books", goodsRecord);
					
					// t_book��Ӧ���¼�Ĵ�����������ֶη����ı�
					bookDao.updateBookData(info.getShoppingDetail()[i]
							.getBookid());
				}
				return true;
			}

		});
	}

}
