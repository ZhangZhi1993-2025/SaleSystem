<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="cn.edu.njnu.viewmodel.BookViewModel"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>BookSales</title>
</head>
<body>
	<%@include file="../header.jsp"%>
	<div class="container body-content">

		<%
			if (request.getAttribute("type") == "search") {
		%>
		<h2 style="font-family: 微软雅黑;">热卖书籍</h2>
		<%
			} else {
		%>
		<h2 style="font-family: 微软雅黑;">书籍查询结果</h2>
		<%
			}
			BookViewModel[] models = (BookViewModel[]) request
					.getAttribute("results");
		%>

		<table class="table">
			<tr>
				<th></th>
				<th>书名</th>
				<th>分类</th>
				<th>价格</th>
				<th>销量</th>
				<th>库存</th>
				<th>评分</th>
			</tr>
			<%
				for (int i = 0; i < models.length; i++) {
					String detail = "/booksales/book_detail?book="
							+ models[i].getId();
			%>
			<tr>
				<td><%=models[i].getId()%></td>
				<td><a href="<%=detail%>"><%=models[i].getName()%></a></td>
				<td><%=models[i].getCategory()%></td>
				<td><%=models[i].getPrice()%></td>
				<td><%=models[i].getSale()%></td>
				<td><%=models[i].getAmount()%></td>
				<td><%=models[i].getStar()%></td>
			</tr>
			<%
				}
			%>
		</table>
		<%@include file="../footer.jsp"%>
	</div>
</body>
</html>
