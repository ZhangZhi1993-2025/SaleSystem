package cn.edu.njnu.service;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

import org.apache.commons.lang3.RandomStringUtils;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.IAtom;

import cn.edu.njnu.model.User;
import cn.edu.njnu.viewmodel.UserViewModel;
import static cn.edu.njnu.model.User.usrDao;
import static cn.edu.njnu.model.Authority.authDao;

/**
 * *****************************基于用户的服务*****************************************
 * 1.登陆;2.通过给定手机号返回指定的用户信息;3.依据给定的用户及权限代号判断用户是否符合权限;4.注册;5.用户账号信息修改;6.注销用户;
 */

public class UserService {

	/* 用户名不合法 */
	public static final int INVALID_USR_NUMBER = 0;

	/* 用户已经存在 */
	public static final int USR_HAS_EXISTED = 1;

	/* 成功注册 */
	public static final int REG_SUCCESS = 2;

	/* 1.登陆 */
	public int userLogin(String phone, String password) {
		if (usrDao.isAlive(phone) == false)
			return -1;
		List<User> saltList = usrDao.findSaltByPhone(phone);
		if (saltList.size() >= 1) {
			String salt = saltList.get(0).getStr("salt");
			// 混入盐值并使用SHA-256哈希加密
			password = hashPassword(password + salt);
			List<User> userList = usrDao.findByPhoneAndPwd(phone, password);
			if (userList.size() == 1)
				return 1;
		}
		return 0;
	}

	/* 2.通过给定手机号返回指定的用户信息 */
	public UserViewModel getUserInfo(String phone) {
		List<User> users = usrDao.findUserInfo(phone);
		UserViewModel model = new UserViewModel(users.get(0).getInt("id"),
				users.get(0).getInt("score"), users.get(0).getStr("name"));
		return model;
	}

	// 通过给定的id号返回用户手机号
	public String getUserPhone(int userid) {
		return usrDao.findPhoneById(userid);
	}

	// 通过给定的id号返回用户积分
	public int getUserScore(int userid) {
		return usrDao.findById(userid).getInt("score");
	}

	/* 3.依据给定的用户及权限代号判断用户是否符合权限 */
	public boolean userAuthority(int userid, int authNeed) {
		return authDao.hasAuthority(userid, authNeed);
	}

	/* 4.注册 1 */
	public int userRegister(String phone, String password) {
		// 不合法的手机号
		Pattern p = Pattern
				.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
		if (p.matcher(phone).matches() == false)
			return INVALID_USR_NUMBER;

		// 此用户已经存在
		List<User> users = usrDao.findUserByGivenPhone(phone);
		if (users.size() > 0)
			return USR_HAS_EXISTED;

		// 以下为注册成功的处理

		// 混入一段盐值
		String salt = randomStr();
		password = hashPassword(password + salt);
		usrDao.saveUser(phone, salt, password);
		int userid = usrDao.findIdByPhone(phone);
		authDao.saveAuthority(userid, 1);
		return REG_SUCCESS;
	}

	/* 4.注册 2 */
	public int userRegister(String phone) {
		// 不合法的手机号
		Pattern p = Pattern
				.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
		if (p.matcher(phone).matches() == false)
			return INVALID_USR_NUMBER;

		// 此用户已经存在
		List<User> users = usrDao.findUserByGivenPhone(phone);
		if (users.size() > 0)
			return USR_HAS_EXISTED;

		return REG_SUCCESS;
	}

	/* 5.用户账户信息修改 */
	public boolean userUpdate(int userid, String info, int type) {
		switch (type) {
		case 0:// 修改密码
			String salt = randomStr();
			info = hashPassword(info + salt);
			return usrDao.updateUser(userid, type, info, salt);
		case 1:// 修改手机号
			return usrDao.updateUser(userid, type, info);
		case 2:// 修改昵称
			return usrDao.updateUser(userid, type, info);
		default:
			return false;
		}
	}

	/*
	 * 6.注销用户操作,所需要的事务内容如下：(1).删除user表中记录；(2).删除authority表中记录；
	 */
	public boolean userDelete(int userid) {
		return Db.tx(new IAtom() {

			@Override
			public boolean run() throws SQLException {
				usrDao.deleteUser(userid);
				authDao.deleteAuthority(userid);
				return true;
			}

		});
	}

	/* 7.冻结用户 */
	public boolean freezeUser(int userid) {
		return usrDao.changeUserState(userid, false);
	}

	/* 解冻用户 */
	public boolean unfreezeUser(int userid) {
		return usrDao.changeUserState(userid, true);
	}

	/* 8.返回用户列表 */
	public List<UserViewModel> getUserList() {
		List<UserViewModel> models = new ArrayList<UserViewModel>();
		List<User> users = usrDao.findAllUsers();
		for (int i = 0; i < users.size(); i++) {
			models.add(new UserViewModel(users.get(i).getInt("id"), users
					.get(i).getInt("score"), users.get(i).getStr("name"), users
					.get(i).getBoolean("is_alive")));
		}
		return models;
	}

	/** 基于给定的 盐值+口令 混合串返回哈希值 */
	private String hashPassword(String password) {
		MessageDigest digest;
		try {
			digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(password.getBytes("UTF-8"));
			password = new String(hash, "UTF-8");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return password;
	}

	/** 产生一个随机盐值 */
	private String randomStr() {
		Random random = new Random();
		int length = random.nextInt(30);
		return RandomStringUtils.randomAlphanumeric(length);
	}

}
