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
 * ������ģ��ShoppingCar����Ӧ���ݿ��t_shoppingcar
 */

public class ShoppingCar extends Model<ShoppingCar> {

	private static final long serialVersionUID = 1L;

	// ��̬ȫ�ֱ���carDao��Ϊt_shoppingcar���ѯ��ͨ����ڣ��򻯱��
	public static final ShoppingCar carDao = new ShoppingCar();

	/**
	 * ��װShoppingCar�����ݿ���ݽӿ�
	 */

	// �����û�id�������������ﳵ�������Ʒ
	public boolean addItem(int userid, ShoppingDetail detail) {
		return new ShoppingCar().set("userid", userid)
				.set("bookid", detail.getBookid())
				.set("amount", detail.getAmount()).save();
	}

	// �����û�id���ع��ﳵ�������
	public List<ShoppingCar> findItemByUserId(int userid) {
		List<ShoppingCar> list = find(
				"select c.bookid, c.amount from t_shoppingcar c "
						+ "where c.userid = ?", userid);
		return list;
	}

	// �����û�id�����ﳵ�ڵ����µ�
	public boolean createOrder(int userid) {
		return Db.tx(new IAtom() {

			@Override
			public boolean run() throws SQLException {

				// �����ﳵ�ڵ���Ʒת��ΪShoppingDetail[]
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

				// ��չ��ﳵ
				deleteById(userid);

				// �µ�
				ShoppingInfo info = new ShoppingInfo(userid, details);
				orderDao.createOrder(info);

				return true;
			}
		});
	}
}
