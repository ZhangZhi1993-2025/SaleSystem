<%@page import="cn.edu.njnu.viewmodel.FeedbackViewModel"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="cn.edu.njnu.viewmodel.FeedbackViewModel"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>BookSales</title>
</head>
<body>
	<%@include file="../header.jsp"%>
	<%
		String currentOrderUrl = "/booksales/user/current_order?user="
				+ userid;
		String updateAccountUrl = "/booksales/user/update_account?user="
				+ userid;
		String shoppingcarUrl = "/booksales/user/car_content?user="
				+ userid;
		String feedbackUrl = "/booksales/user/feedback?user=" + userid;
	%>
	<div class="container body-content">
		<div style="width: 20%; height: 100%; float: left; color: transparent">.</div>
		<div
			style="position: fixed; text-align: center; width: 15%; background-color: #f7f7f7; height: auto; margin-top: 20px;">
			<div style="background-color: #428bca; height: 7px; width: 100%;">.</div>
			<ul class="nav nav-pills nav-stacked">
				<li><a href="<%=userUrl%>" style="font-family: 微软雅黑;">历史订单列表</a></li>
				<li><a href="<%=currentOrderUrl%>" style="font-family: 微软雅黑;">当前订单列表</a></li>
				<li><a href="<%=updateAccountUrl%>" style="font-family: 微软雅黑;">修改账户信息</a></li>
				<li><a href="<%=shoppingcarUrl%>" style="font-family: 微软雅黑;">我的购物车</a></li>
				<li><a href="<%=feedbackUrl%>" style="font-family: 微软雅黑;">待评价的商品</a></li>
			</ul>
			<div style="background-color: #428bca; height: 7px; width: 100%;">.</div>
		</div>
		<div style="float: left; width: 80%; height: auto; padding-left: 4%">

			<h2 style="font-family: 微软雅黑;">待评价的商品</h2>
			<%
				List<FeedbackViewModel> books = (List<FeedbackViewModel>) request
						.getAttribute("books");
			%>
			<table class="table">
				<tr>
					<th>id</th>
					<th>书名</th>
					<th>价格</th>
					<th>分类</th>
					<th></th>
				</tr>
				<%
					for (int i = 0; i < books.size(); i++) {
						String detail = "/booksales/user/feedback_detail?book="
								+ books.get(i).getBookid() + "&order="
								+ books.get(i).getOrderid();
				%>
				<tr>
					<td><%=books.get(i).getBookid()%></td>
					<td><%=books.get(i).getName()%></td>
					<td><%=books.get(i).getPrice()%></td>
					<td><%=books.get(i).getCategory()%></td>
					<td><a href="<%=detail%>">评价</a></td>
				</tr>
				<%
					}
				%>
			</table>
		</div>
		<div
			style="width: 100%; height: 120px; clear: both; color: transparent">.</div>
		<%@include file="../footer.jsp"%>
	</div>
</body>
</html>
