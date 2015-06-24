package cn.edu.njnu.viewmodel;

import java.sql.Timestamp;

/**
 * 订单概要信息模型
 */

public class OrderViewModel {

	// 订单号
	private int id;

	// 下单时间
	private Timestamp createtime;

	// 订单状态
	private boolean state;

	// 总价
	private double price;

	// 用户
	private int userid;

	public OrderViewModel(int id, Timestamp createtime, boolean state,
			double price, int userid) {
		this.id = id;
		this.createtime = createtime;
		this.state = state;
		this.price = price;
		this.userid = userid;
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

	public boolean getState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

}
