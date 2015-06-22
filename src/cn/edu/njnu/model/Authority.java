package cn.edu.njnu.model;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;

/**
 * ������ģ��Authority����Ӧ���ݿ��t_authority
 */

public class Authority extends Model<Authority> {

	private static final long serialVersionUID = 1L;

	// ��̬ȫ�ֱ���authDao��Ϊt_authority���ѯ��ͨ����ڣ��򻯱��
	public static final Authority authDao = new Authority();

	/**
	 * ��װAuthority�����ݿ���ݽӿ�
	 */

	// ���ݸ������û���Ȩ�޴����ж��û��Ƿ����Ȩ��
	public boolean hasAuthority(int userID, int authNeed) {
		List<Authority> authList = find(
				"select * from authority where userID = ?0 and auth = ?1",
				userID, authNeed);
		if (authList.size() > 0)
			return true;
		else
			return false;
	}

	// �����µ��û�Ȩ��
	public boolean saveAuthority(int userID, int auth) {
		return new Authority().set("userID", userID).set("auth", auth).save();
	}

	// ɾ��ָ���û���ָ��Ȩ��
	public boolean deleteAuthority(int userID, int auth) {
		return Db.update(
				"delete from authority where userID = ?0 and auth = ?1",
				userID, auth) > 0;
	}

	// ɾ��ָ���û�������Ȩ�ޣ�ͨ��������ɾ���û�
	public boolean deleteAuthority(int userID) {
		return Db.update("delete from authority where userID = ?0", userID) > 0;
	}

}
