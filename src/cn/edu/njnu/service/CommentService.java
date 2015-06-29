package cn.edu.njnu.service;

import java.util.ArrayList;
import java.util.List;

import cn.edu.njnu.model.Comment;
import cn.edu.njnu.viewmodel.CommentViewModel;
import static cn.edu.njnu.model.Comment.commentDao;

/**
 * *****************************评论相关服务*****************************************
 * 1.根据书id号查询相关评论;2.对某本书评论;
 */

public class CommentService {

	// 1.根据书id号查询相关评论
	public List<CommentViewModel> getCommentById(int bookid) {
		List<CommentViewModel> models = new ArrayList<CommentViewModel>();
		List<Comment> results = commentDao.findCommentById(bookid);
		for (int i = 0; i < results.size(); i++) {
			models.add(new CommentViewModel(results.get(i).getStr("comment"),
					results.get(i).getInt("userid")));
		}
		return models;
	}
	
	// 2.对某本书评论
	public boolean commentBook(int userid, int bookid, String comment) {
		return commentDao.addComment(userid, bookid, comment);
	}
}
