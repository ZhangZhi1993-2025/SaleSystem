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
		<hr />

		<%
			BookViewModel book = (BookViewModel) request.getAttribute("book");
			List<CommentViewModel> comments = (List<CommentViewModel>) request
					.getAttribute("comment");
			String createOrderUrl = "/booksales/user/create_order?user="
					+ userid + "&book=" + book.getId();
			String values = userid + "," + book.getId();
		%>
		<p id="values" style="display: none;"><%=values%></p>

		<div style="width: 40%; float: left;">
			<h3 style="font-family: 微软雅黑; margin-top: 0px; margin-left: 60px;">
				<%=book.getName()%>&nbsp;&nbsp;<small><a
					href="/booksales/search?keywords=&category=<%=book.getCategory()%>">
						<%=book.getCategory()%></a></small>
			</h3>
			<dl class="dl-horizontal" style="font-family: 微软雅黑;">
				<dt>价格</dt>
				<dd style="color: #ff0000"><%=book.getPrice()%></dd>

				<dt>出版社</dt>
				<dd><%=book.getPress()%></dd>

				<dt>销量</dt>
				<dd><%=book.getSale()%></dd>

				<dt>库存</dt>
				<dd><%=book.getAmount()%></dd>

				<dt>评分</dt>
				<dd><%=book.getStar()%></dd>

				<dt>详细描述</dt>
				<dd><%=book.getDesc()%></dd>
			</dl>

			<div
				style="width: 100%; height: 30px; clear: both; color: transparent">.</div>

			<form action="<%=createOrderUrl%>" method="post"
				class="form-horizontal" role="form">
				<div class="form-group">
					<label class="col-md-2 control-label" style="font-family: 微软雅黑;">数量</label>
					<input
						style="width: 45px; margin: 5px 15px 5px -10px; padding-left: 5px; float: left;"
						name="amount" id="amount" value="1" type="number" min=1 step=1 />
					<input type="submit" value="确认购买" class="btn btn-primary"
						style="font-family: 微软雅黑; margin-left: 45px; float: left;" /> <a
						class="btn btn-info" id="car" onclick="getUrl()"
						style="font-family: 微软雅黑; float: left; margin-left: 15px;">加入购物车</a>
				</div>
			</form>

		</div>
		<div style="float: left; width: 40%; padding-left: 4%">
			<p style="margin-top: 0px;">
				<img alt="图书图片" src="<%=book.getPic()%>"
					style="width: 180px; height: 240px;">
			</p>
		</div>

		<div style="width: 100%; height: 0; clear: both; color: transparent">.</div>

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
				<td><%=comments.get(i).getUser()%></td>
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
	<script type="text/javascript">
		function getUrl() {
			var url = "/booksales/user/add_item_to_car?user=";
			var amount = document.getElementById("amount").value;
			var link = document.getElementById("car");
			var values = document.getElementById("values").innerHTML.split(",");
			link.href = url + values[0] + "&book=" + values[1] + "&amount="
					+ amount;
		}
	</script>
</body>
</html>
