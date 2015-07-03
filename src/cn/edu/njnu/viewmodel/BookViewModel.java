package cn.edu.njnu.viewmodel;

/**
 * 图书视图模型
 */

public class BookViewModel {

	// id号
	private int id;

	// 书名
	private String name;

	// 价格
	private double price;

	// 分类
	private String category;

	// 销量
	private int sale;

	// 存货量
	private int amount;

	// 评分
	private double star;

	// 描述
	private String desc;

	// 图片
	private String pic;

	// 出版社
	private String press;

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

	public BookViewModel(int id, String name, double price, String category,
			int sale, int amount, double star, String desc, String press) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.category = category;
		this.sale = sale;
		this.amount = amount;
		this.star = star;
		this.desc = desc;
		this.press = press;
	}

	public BookViewModel(int id, String name, double price, String category,
			int sale, int amount, double star, String desc, String pic,
			String press) {
		this.press = press;
		this.id = id;
		this.name = name;
		this.price = price;
		this.category = category;
		this.sale = sale;
		this.amount = amount;
		this.star = star;
		this.desc = desc;
		this.pic = pic;
	}

	public BookViewModel(int id, String name, double price) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
	}

	public BookViewModel(int id, String name, double price, String category) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.category = category;
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

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getPress() {
		return press;
	}

	public void setPress(String press) {
		this.press = press;
	}

}
