package cn.edu.njnu.service;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
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
 * *****************************�����û��ķ���*****************************************
 * 1.��½;2.ͨ�������ֻ��ŷ���ָ�����û���Ϣ;3.���ݸ������û���Ȩ�޴����ж��û��Ƿ����Ȩ��;4.ע��;5.�û��˺���Ϣ�޸�;6.ע���û�;
 */

public class UserService {

	/* �û������Ϸ� */
	public static final int INVALID_USR_NUMBER = 0;

	/* �û��Ѿ����� */
	public static final int USR_HAS_EXISTED = 1;

	/* �ɹ�ע�� */
	public static final int REG_SUCCESS = 2;

	/* 1.��½ */
	public boolean userLogin(String phone, String password) {
		List<User> saltList = usrDao.findSaltByPhone(phone);
		if (saltList.size() >= 1) {
			String salt = saltList.get(0).getStr("salt");
			// ������ֵ��ʹ��SHA-256��ϣ����
			password = hashPassword(password + salt);
			List<User> userList = usrDao.findByPhoneAndPwd(phone, password);
			if (userList.size() == 1)
				return true;
		}
		return false;
	}

	/* 2.ͨ�������ֻ��ŷ���ָ�����û���Ϣ */
	public UserViewModel getUserInfo(String phone) {
		List<User> users = usrDao.findUserInfo(phone);
		UserViewModel model = new UserViewModel(users.get(0).getInt("id"),
				users.get(0).getInt("score"), users.get(0).getStr("name"));
		return model;
	}

	/* ͨ��������id�ŷ����û��ֻ��� */
	public String getUserPhone(int userid) {
		return usrDao.findPhoneById(userid);
	}

	/* 3.���ݸ������û���Ȩ�޴����ж��û��Ƿ����Ȩ�� */
	public boolean userAuthority(int userid, int authNeed) {
		return authDao.hasAuthority(userid, authNeed);
	}

	/* 4.ע�� */
	public int userRegister(String phone, String password) {
		// ���Ϸ����ֻ���
		Pattern p = Pattern
				.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
		if (p.matcher(phone).matches() == false)
			return INVALID_USR_NUMBER;

		// ���û��Ѿ�����
		List<User> users = usrDao.findUserByGivenPhone(phone);
		if (users.size() > 0)
			return USR_HAS_EXISTED;

		// ����Ϊע��ɹ��Ĵ���

		// ����һ����ֵ
		String salt = randomStr();
		password = hashPassword(password + salt);
		usrDao.saveUser(phone, salt, password);
		return REG_SUCCESS;
	}

	/* 5.�û��˻���Ϣ�޸� */
	public boolean userUpdate(int userid, String info, int type) {
		switch (type) {
		case 0:// �޸�����
			String salt = randomStr();
			info = hashPassword(salt + info);
			return usrDao.updateUser(userid, info, type);
		case 1:// �޸��ֻ���
			return usrDao.updateUser(userid, info, type);
		case 2:// �޸��ǳ�
			return usrDao.updateUser(userid, info, type);
		default:
			return false;
		}
	}

	/*
	 * 6.ע���û�����,����Ҫ�������������£�(1).ɾ��user���м�¼��(2).ɾ��authority���м�¼��
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

	/** ���ڸ����� ��ֵ+���� ��ϴ����ع�ϣֵ */
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

	/** ����һ�������ֵ */
	private String randomStr() {
		Random random = new Random();
		int length = random.nextInt(30);
		return RandomStringUtils.randomAlphanumeric(length);
	}

}
