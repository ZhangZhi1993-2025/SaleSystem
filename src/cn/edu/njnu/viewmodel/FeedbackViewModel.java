package cn.edu.njnu.viewmodel;

/**
 * 用于反馈评论的视图模型
 */
public class FeedbackViewModel {

	private int orderid;

	private int bookid;

	private String name;

	private double price;

	private String category;

	public FeedbackViewModel(int orderid, int bookid, String name,
			double price, String category) {
		this.orderid = orderid;
		this.bookid = bookid;
		this.name = name;
		this.price = price;
		this.category = category;
	}

	/* setter and getter for every member */

	public int getOrderid() {
		return orderid;
	}

	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}

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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

}
