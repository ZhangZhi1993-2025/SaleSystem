package cn.edu.njnu.viewmodel;

import java.sql.Timestamp;

/**
 * 订单详情模型,用于读数据库
 */

public class OrderDetailViewModel {

	// 订单号
	private int id;

	// 下单时间
	private Timestamp createtime;

	// 联系电话
	private String phone;

	// 总价
	private double price;

	// 购买商品详情
	private ShoppingDetail[] shoppingDetail;

	public OrderDetailViewModel(int id, Timestamp createtime, String phone,
			double price, ShoppingDetail[] shoppingDetail) {
		this.id = id;
		this.createtime = createtime;
		this.phone = phone;
		this.price = price;
		this.shoppingDetail = shoppingDetail;
	}

	/* setter and getter for every member */
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public ShoppingDetail[] getGoodsDetail() {
		return shoppingDetail;
	}

	public void setGoodsDetail(ShoppingDetail[] shoppingDetail) {
		this.shoppingDetail = shoppingDetail;
	}

}
