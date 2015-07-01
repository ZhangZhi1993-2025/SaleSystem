package cn.edu.njnu.viewmodel;

public class CommentViewModel {

	// 评论内容
	private String comment;

	// 评论用户
	private String user;

	public CommentViewModel(String comment, String user) {
		this.comment = comment;
		this.user = user;
	}

	/* setter and getter for every member */

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

}
