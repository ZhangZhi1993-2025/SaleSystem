package cn.edu.njnu.viewmodel;

/**
 * 书目类别
 */

public class CategoryViewModel {

	// 类别
	private String name;

	// id号
	private int id;

	public CategoryViewModel(String name, int id) {
		this.name = name;
		this.id = id;
	}

	/* setter and getter for every member */

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
