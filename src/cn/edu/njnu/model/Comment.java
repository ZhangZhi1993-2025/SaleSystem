package cn.edu.njnu.model;

import java.util.List;

import com.jfinal.plugin.activerecord.Model;

/**
 * 富领域模型Comment，对应数据库表t_comment
 */

public class Comment extends Model<Comment> {

	private static final long serialVersionUID = 1L;

	// 静态全局变量commentDao作为t_comment表查询的通用入口，简化编程
	public static final Comment commentDao = new Comment();

	/**
	 * 封装Comment的数据库操纵接口
	 */

	public List<Comment> findCommentById(int bookid) {
		return find(
				"select c.comment, c.userid from t_comment c where c.bookid = ?",
				bookid);
	}

}
