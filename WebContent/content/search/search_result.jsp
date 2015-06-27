<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="cn.edu.njnu.viewmodel.BookViewModel"%>
<%@ page import="java.util.List"%>
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
			if (request.getAttribute("type") == "hot") {
		%>
		<h2 style="font-family: 微软雅黑;">热卖图书</h2>
		<%
			} else {
		%>
		<h2 style="font-family: 微软雅黑;">书籍查询结果</h2>
		<%
			}
			List<BookViewModel> books = (List<BookViewModel>) request
					.getAttribute("results");
			if (books.size() != 0) {
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
				for (int i = 0; i < books.size(); i++) {
						String detail = "/booksales/book_detail?book="
								+ books.get(i).getId();
			%>
			<tr>
				<td><%=books.get(i).getId()%></td>
				<td><a href="<%=detail%>"><%=books.get(i).getName()%></a></td>
				<td><%=books.get(i).getCategory()%></td>
				<td><%=books.get(i).getPrice()%></td>
				<td><%=books.get(i).getSale()%></td>
				<td><%=books.get(i).getAmount()%></td>
				<td><%=books.get(i).getStar()%></td>
			</tr>
			<%
				}
			%>
		</table>
		<%
			} else {
		%>
		<h4 style="font-family: 微软雅黑;">抱歉,按您的查询要求未找到匹配目标！</h4>
		<%
			}
		%>
		<%@include file="../footer.jsp"%>
	</div>
</body>
</html>
