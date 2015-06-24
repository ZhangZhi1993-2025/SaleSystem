package cn.edu.njnu.model;

import java.sql.Timestamp;
import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;

/**
 * 富领域模型User，对应数据库表t_user
 */

public class User extends Model<User> {

	private static final long serialVersionUID = 1L;

	// 静态全局变量usrDao作为t_user表查询的通用入口，简化编程
	public static final User usrDao = new User();

	/**
	 * 封装User的数据库操纵接口
	 */

	// 通过给定手机号查询盐值，找不到目标则返回空集，主要用于登陆验证用户是否存在
	public List<User> findSaltByPhone(String phone) {
		return find("select u.salt from t_user u where u.phone = ?", phone);
	}

	// 用于登陆，通过手机号与密码验证用户身份
	public List<User> findByPhoneAndPwd(String phone, String saltedPassword) {
		return find(
				"select * from t_user where t_user.phone = ? and t_user.password = ?",
				phone, saltedPassword);
	}

	// 通过id号返回用户手机号
	public String findPhoneById(int userid) {
		return findById(userid).getStr("phone");
	}

	// 通过给定手机号查找用户，用于注册时判断账号是否已被注册
	public List<User> findUserByGivenPhone(String phone) {
		return find("select u.id from t_user u where t_user.phone = ?", phone);
	}

	// 通过给定信息返回指定的用户信息
	public List<User> findUserInfo(String phone) {
		return find("select u.id, u.score, u.name"
				+ "from t_user u where u.phone = ?", phone);
	}

	// 用于注册插入一条用户账户信息
	public boolean saveUser(String phone, String salt, String password) {
		return new User().set("phone", phone).set("salt", salt)
				.set("password", password)
				.set("createtime", new Timestamp(System.currentTimeMillis()))
				.save();
	}

	// 当用户修改个人信息时更新记录
	public boolean updateUser(int userid, String info, int type) {
		switch (type) {
		case 0:/* 修改密码 */
			return findById(userid).set("password", info).update();
		case 1:/* 修改手机号 */
			return findById(userid).set("phone", info).update();
		case 2:/* 修改昵称 */
			return findById(userid).set("name", info).update();
		default:
			return false;
		}
	}

	// 注销用户操作
	public boolean deleteUser(int userid) {
		return Db.update("delete from t_user where userid = ?", userid) == 1;
	}

}
