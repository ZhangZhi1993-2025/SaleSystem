package cn.edu.njnu.viewmodel;

/**
 * 对应于订单中的商品名及购买数量及单价
 */

public class ShoppingDetail {

	// 书id号
	private int bookid;

	// 书名
	private String name;

	// 购买数量
	private int amount;

	// 单价
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
