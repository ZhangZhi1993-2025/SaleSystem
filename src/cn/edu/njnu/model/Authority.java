package cn.edu.njnu.model;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;

/**
 * 富领域模型Authority，对应数据库表t_authority
 */

public class Authority extends Model<Authority> {

	private static final long serialVersionUID = 1L;

	// 静态全局变量authDao作为t_authority表查询的通用入口，简化编程
	public static final Authority authDao = new Authority();

	/**
	 * 封装Authority的数据库操纵接口
	 */

	// 依据给定的用户及权限代号判断用户是否符合权限
	public boolean hasAuthority(int userid, int authNeed) {
		List<Authority> authList = find(
				"select * from t_authority where userid = ? and auth = ?",
				userid, authNeed);
		if (authList.size() > 0)
			return true;
		else
			return false;
	}

	// 插入新的用户权限
	public boolean saveAuthority(int userid, int auth) {
		return new Authority().set("userid", userid).set("auth", auth).save();
	}

	// 删除指定用户的指定权限
	public boolean deleteAuthority(int userid, int auth) {
		return Db.update(
				"delete from t_authority where userid = ? and auth = ?",
				userid, auth) > 0;
	}

	// 删除指定用户的所有权限，通常伴随着删除用户
	public boolean deleteAuthority(int userid) {
		return Db.update("delete from t_authority where userid = ?", userid) > 0;
	}

}
