package cn.edu.njnu.viewmodel;

/**
 * ��Ӧ�ڶ����е���Ʒ������������������
 */

public class ShoppingDetail {

	// ��id��
	private int bookid;

	// ����
	private String name;

	// ��������
	private int amount;

	// ����
	private double price;

	public ShoppingDetail(int bookid, String name, int amount, double price) {
		this.bookid = bookid;
		this.name = name;
		this.amount = amount;
		this.price = price;
	}

	public ShoppingDetail(int bookid, int amount, double price) {
		super();
		this.bookid = bookid;
		this.amount = amount;
		this.price = price;
	}

	public ShoppingDetail(int bookid, int amount) {
		this.bookid = bookid;
		this.amount = amount;
	}
	
	/* setter and getter for every member */

	public int getBookid() {
		return bookid;
	}

	public void setBookid(int bookid) {
		this.bookid = bookid;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
