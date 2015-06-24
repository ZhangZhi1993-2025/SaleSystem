package cn.edu.njnu.viewmodel;

/**
 * �û�ģ�͡���������Ҫ���û���Ϣ
 */

public class UserViewModel {

	// id��
	private int id;

	// ����
	private int score;

	// �ǳ�
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