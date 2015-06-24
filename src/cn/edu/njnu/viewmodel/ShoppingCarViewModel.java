package cn.edu.njnu.viewmodel;

public class ShoppingCarViewModel {

	// 总价
	private double price;

	// 购买商品详情
	private ShoppingDetail[] shoppingDetail;

	public ShoppingCarViewModel(double price, ShoppingDetail... shoppingDetail) {
		this.price = price;
		this.shoppingDetail = shoppingDetail;
	}
	
	/* setter and getter for every member */

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public ShoppingDetail[] getShoppingDetail() {
		return shoppingDetail;
	}

	public void setShoppingDetail(ShoppingDetail[] shoppingDetail) {
		this.shoppingDetail = shoppingDetail;
	}

}
