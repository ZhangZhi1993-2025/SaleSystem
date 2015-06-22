package cn.edu.njnu.viewmodel;

/**
 * 一份订单所需要的基本内容,用于写数据库
 */

public class ShoppingInfo {

	// 下单用户id号
	private int userid;

	// 购买商品集
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
