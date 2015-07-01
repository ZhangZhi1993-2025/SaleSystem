<%@page contentType="text/html; charset=utf-8"%>
<%@page import="cn.edu.njnu.viewmodel.ShoppingDetail"%>
<%@page import="cn.edu.njnu.viewmodel.ShoppingCarViewModel"%>
<%@page import="java.util.List"%>
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
		String createOrderUrl = "/booksales/user/create_order_by_shoppingcar?user="
				+ userid;
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

		<%
			ShoppingCarViewModel car = (ShoppingCarViewModel) request
					.getAttribute("shoppingcar");
			double price = car.getPrice();
		%>

		<div style="float: left; width: 80%; height: auto; padding-left: 4%">

			<h2 style="font-family: 微软雅黑; float: left; margin-right: 20px;">我的购物车</h2>
			<h2 style="float: left; color: #ff0000; margin-top: 22px;">
				￥
				<%=price%></h2>

			<table class="table" style="clear: both;">
				<tr>
					<th>id</th>
					<th>书名</th>
					<th>单价</th>
					<th>购买数量</th>
					<th></th>
				</tr>
				<%
					ShoppingDetail[] detailList = car.getShoppingDetail();
					for (int i = 0; i < detailList.length; i++) {
						String remove = "/booksales/user/remove_item_from_car?user="
								+ userid + "&book=" + detailList[i].getBookid();
				%>
				<tr>
					<td><%=detailList[i].getBookid()%></td>
					<td><%=detailList[i].getName()%></td>
					<td><%=detailList[i].getPrice()%></td>
					<td><%=detailList[i].getAmount()%></td>
					<td><a href="<%=remove%>">移除</a></td>
				</tr>
				<%
					}
				%>
			</table>
			<%
				if (detailList.length != 0) {
			%>
			<a href="<%=createOrderUrl%>" class="btn btn-primary btn-lg"
				style="font-family: 微软雅黑; float: right; margin-right: 90px; margin-top: 30px;">一键下单</a>
			<%
				} else {
			%>
			<a class="btn btn-primary btn-lg"
				style="font-family: 微软雅黑; float: right; margin-right: 90px; margin-top: 30px;">一键下单</a>
			<%
				}
			%>
		</div>
		<div
			style="width: 100%; height: 120px; clear: both; color: transparent">.</div>
		<%@include file="../footer.jsp"%>
	</div>
</body>
</html>