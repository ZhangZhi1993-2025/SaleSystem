package cn.edu.njnu.viewmodel;

import java.sql.Timestamp;

/**
 * ������Ҫ��Ϣģ��
 */

public class OrderViewModel {

	// ������
	private int id;

	// �µ�ʱ��
	private Timestamp createtime;

	// ����״̬
	private boolean state;

	// �ܼ�
	private double price;

	// �û�
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
