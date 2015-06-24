package cn.edu.njnu.viewmodel;

/**
 * 用户模型——基本必要的用户信息
 */

public class UserViewModel {

	// id号
	private int id;

	// 积分
	private int score;

	// 昵称
	private String name;

	public UserViewModel(int id, int score, String name) {
		this.name = name;
		this.score = score;
		this.id = id;
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

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

}
