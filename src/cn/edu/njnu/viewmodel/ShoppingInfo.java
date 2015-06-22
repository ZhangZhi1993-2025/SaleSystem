package cn.edu.njnu.viewmodel;

/**
 * һ�ݶ�������Ҫ�Ļ�������,����д���ݿ�
 */

public class ShoppingInfo {

	// �µ��û�id��
	private int userid;

	// ������Ʒ��
	private ShoppingDetail[] shoppingDetail;

	public ShoppingInfo(int userid, ShoppingDetail... shoppingDetail) {
		this.shoppingDetail = shoppingDetail;
		this.userid = userid;
	}

	/* setter and getter for every member */

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public ShoppingDetail[] getShoppingDetail() {
		return shoppingDetail;
	}

	public void setShoppingDetail(ShoppingDetail[] shoppingDetail) {
		this.shoppingDetail = shoppingDetail;
	}

}
