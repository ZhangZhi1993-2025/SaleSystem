<%@page contentType="text/html; charset=utf-8"%>
<%@page import="cn.edu.njnu.viewmodel.ShoppingDetail"%>
<%@page import="cn.edu.njnu.viewmodel.OrderDetailViewModel"%>
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
		String completeOrderUrl = "/booksales/user/order_complete?user="
				+ userid + "&order=" + request.getAttribute("order");
		boolean isCurrent = (boolean) request.getAttribute("isCurrent");
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
			OrderDetailViewModel order = (OrderDetailViewModel) request
					.getAttribute("detailOrder");
			double price = order.getPrice();
		%>

		<div style="float: left; width: 80%; height: auto; padding-left: 4%">

			<h2 style="font-family: 微软雅黑; float: left; margin-right: 20px;">订单详情</h2>
			<h2 style="float: left; color: #ff0000; margin-top: 22px;">
				￥
				<%=price%></h2>

			<h4 style="font-family: 微软雅黑; clear: both;">基本信息</h4>
			<table class="table">
				<tr>
					<th>订单号</th>
					<th>联系电话</th>
					<th>总价</th>
					<th>下单时间</th>
				</tr>
				<tr>
					<td><%=order.getId()%></td>
					<td><%=order.getPhone()%></td>
					<td><%=order.getPrice()%></td>
					<td><%=order.getCreatetime()%></td>
				</tr>
			</table>
			<div style="width: 100%; height: 40px; color: transparent">.</div>
			<h4 style="font-family: 微软雅黑;">购书详情</h4>
			<table class="table">
				<tr>
					<th>书id号</th>
					<th>书名</th>
					<th>单价</th>
					<th>购买数量</th>
				</tr>
				<%
					ShoppingDetail[] detailList = order.getGoodsDetail();
					for (int i = 0; i < detailList.length; i++) {
				%>
				<tr>
					<td><%=detailList[i].getBookid()%></td>
					<td><%=detailList[i].getName()%></td>
					<td><%=detailList[i].getPrice()%></td>
					<td><%=detailList[i].getAmount()%></td>
				</tr>
				<%
					}
				%>
			</table>
			<%
				if (isCurrent == true) {
			%>
			<a href="<%=completeOrderUrl%>" class="btn btn-primary btn-lg"
				style="font-family: 微软雅黑; float: right; margin-right: 90px; margin-top: 30px;">确认完成</a>
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