<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="cn.edu.njnu.viewmodel.BookViewModel"%>
<%@ page import="java.util.List"%>
<%@ page import="cn.edu.njnu.viewmodel.CommentViewModel"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>BookSales</title>
</head>
<body>
	<%@include file="../header.jsp"%>
	<div class="container body-content">

		<h2 style="font-family: 微软雅黑;">图书详细信息</h2>

		<%
			BookViewModel book = (BookViewModel) request.getAttribute("book");
			List<CommentViewModel> comments = (List<CommentViewModel>) request
					.getAttribute("comment");
		%>

		<div>
			<hr />
			<dl class="dl-horizontal">
				<dt>id号</dt>
				<dd><%=book.getId()%></dd>

				<dt>书名</dt>
				<dd><%=book.getName()%></dd>

				<dt>分类</dt>
				<dd><%=book.getCategory()%></dd>

				<dt>价格</dt>
				<dd><%=book.getPrice()%></dd>

				<dt>销量</dt>
				<dd><%=book.getSale()%></dd>

				<dt>库存</dt>
				<dd><%=book.getAmount()%></dd>

				<dt>评分</dt>
				<dd><%=book.getStar()%></dd>

				<dt>详细描述</dt>
				<dd><%=book.getDesc()%></dd>
			</dl>
		</div>

		<h2 style="font-family: 微软雅黑;">相关评论</h2>
		<%
			if (comments.size() != 0) {
		%>
		<table class="table">
			<tr>
				<th>评论者</th>
				<th>评论内容</th>
			</tr>

			<%
				for (int i = 0; i < comments.size(); i++) {
			%>
			<tr>
				<td><%=comments.get(i).getUserid()%></td>
				<td><%=comments.get(i).getComment()%></td>
			</tr>
			<%
				}
			%>
		</table>
		<%
			} else {
		%>
		<h4 style="font-family: 微软雅黑;">暂无相关商品评论</h4>
		<%
			}
		%>
		<%@include file="../footer.jsp"%>
	</div>
</body>
</html>
