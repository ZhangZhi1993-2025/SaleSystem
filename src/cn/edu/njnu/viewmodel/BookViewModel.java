package cn.edu.njnu.viewmodel;

public class BookViewModel {

	// id��
	private int id;

	// ����
	private String name;

	// �۸�
	private double price;

	// ����
	private String category;

	// ����
	private int sale;

	// �����
	private int amount;

	// ����
	private double star;

	// ����
	private String desc;

	public BookViewModel(int id, String name, double price, String category,
			int sale, int amount, double star, String desc) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.category = category;
		this.sale = sale;
		this.amount = amount;
		this.star = star;
		this.desc = desc;
	}

	public BookViewModel(int id, String name, double price) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
	}
	
	public BookViewModel(int id, double price) {
		super();
		this.id = id;
		this.price = price;
	}

	/* setter and getter for every member */

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public int getSale() {
		return sale;
	}

	public void setSale(int sale) {
		this.sale = sale;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public double getStar() {
		return star;
	}

	public void setStar(double star) {
		this.star = star;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
