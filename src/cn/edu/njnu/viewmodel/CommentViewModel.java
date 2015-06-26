package cn.edu.njnu.viewmodel;

public class CommentViewModel {

	// 评论内容
	private String comment;

	// 评论用户
	private int userid;

	public CommentViewModel(String comment, int userid) {
		this.comment = comment;
		this.userid = userid;
	}

	/* setter and getter for every member */

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

}
