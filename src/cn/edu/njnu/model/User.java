package cn.edu.njnu.model;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.IAtom;
import com.jfinal.plugin.activerecord.Model;

/**
 * ������ģ��User����Ӧ���ݿ��t_user
 */

public class User extends Model<User> {

	private static final long serialVersionUID = 1L;

	// ��̬ȫ�ֱ���usrDao��Ϊt_user���ѯ��ͨ����ڣ��򻯱��
	public static final User usrDao = new User();

	/**
	 * ��װUser�����ݿ���ݽӿ�
	 */

	// ͨ�������ֻ��Ų�ѯ��ֵ���Ҳ���Ŀ���򷵻ؿռ�����Ҫ���ڵ�½��֤�û��Ƿ����
	public List<User> findSaltByPhone(String phone) {
		return find("select u.salt from t_user u where u.phone = ?", phone);
	}

	// ���ڵ�½��ͨ���ֻ�����������֤�û����
	public List<User> findByPhoneAndPwd(String phone, String saltedPassword) {
		return find(
				"select * from t_user where t_user.phone = ? and t_user.password = ?",
				phone, saltedPassword);
	}

	// ͨ�������ֻ��Ų����û�������ע��ʱ�ж��˺��Ƿ��ѱ�ע��
	public List<User> findUserByGivenPhone(String phone) {
		return find("select u.id from t_user u where t_user.phone = ?", phone);
	}

	// ͨ�����̲��ҵ�����id��
	public List<User> findSellerIdByStore(int storeid) {
		return find("select u.id from t_user u, t_store_user su where "
				+ "su.storeid = ? and u.id = su.userid", storeid);
	}

	// ͨ�������ֻ��ŷ���ָ�����û���Ϣ
	public List<User> findUserInfo(String phone) {
		return find("select u.id, u.phone, u.cash, u.score, u.avatar_addr "
				+ "from t_user u where u.phone = ?", phone);
	}

	// ����ָ���û�id�ŷ���ѧУid��
	public List<User> findSchoolById(int id) {
		return find("select u.schoolid from user u where u.id = ?", id);
	}

	// ����ע�����һ���û��˻���Ϣ
	public boolean saveUser(String phone, String salt, String password) {
		return new User().set("phone", phone).set("salt", salt)
				.set("password", password)
				.set("createtime", new Timestamp(System.currentTimeMillis()))
				.save();
	}

	// ���û��޸ĸ�����Ϣʱ���¼�¼
	public boolean updateUser(int userid, String info, int type) {
		switch (type) {
		case 0:/* �޸����� */
			return findById(userid).set("password", info).update();
		case 1:/* �޸��ֻ��� */
			return findById(userid).set("phone", info).update();
		case 2:/* �޸�Ĭ�ϵ�ַ */
			return Db.tx(new IAtom() {

				@Override
				public boolean run() throws SQLException {
					return /* �Ƚ�ԭĬ�ϵ�ַ��Ϊ��ͨ��ַ */
					Db.update("update t_address set isdefault = 0  where "
							+ "userid = ? and isdefault = 1", userid) == 1
							&& /* �ٽ�ָ����ͨ��ַ��ΪĬ�ϵ�ַ */
							Db.update(
									"update t_address set isdefault = 1 where "
											+ "userid = ? and id = ?", userid,
									info) == 1;
				}

			});
		default:
			return false;
		}
	}

	// ע���û�����
	public boolean deleteUser(int userID) {
		return Db.update("delete from t_user where userid = ?", userID) == 1;
	}

}
