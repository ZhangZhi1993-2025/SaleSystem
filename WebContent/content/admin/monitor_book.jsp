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

		<h2 style="font-family: 微软雅黑;">图书详细信息</h2>

		<%
			BookViewModel book = (BookViewModel) request.getAttribute("book");
			String submitUrl = "/booksales/admin/update_book?book="
					+ book.getId();
		%>

		<div>
			<h4 style="font-family: 微软雅黑;">请在需要更新的栏目上修改并提交</h4>
			<hr />
			<form action="<%=submitUrl%>" method="post" class="form-horizontal"
				role="form">
				<dl class="dl-horizontal">
					<dt>id号</dt>
					<dd><%=book.getId()%></dd>

					<dt style="margin-top: 20px;">书名</dt>
					<dd style="margin-top: 10px; margin-bottom: 10px;">
						<input class="form-control" name="newname" type="text"
							value="<%=book.getName()%>" />
					</dd>

					<dt style="margin-top: 10px;">分类</dt>
					<dd style="margin-top: 10px; margin-bottom: 10px;">
						<input class="form-control" name="newcategory" type="text"
							value="<%=book.getCategory()%>" />
					</dd>

					<dt style="margin-top: 10px;">出版社</dt>
					<dd style="margin-top: 10px; margin-bottom: 10px;">
						<input class="form-control" name="newpress" type="text"
							value="<%=book.getPress()%>" />
					</dd>

					<dt style="margin-top: 10px;">库存</dt>
					<dd style="margin-top: 10px; margin-bottom: 10px;">
						<input class="form-control" name="newamount" type="text"
							value="<%=book.getAmount()%>" />
					</dd>

					<dt style="margin-top: 10px;">价格</dt>
					<dd style="margin-top: 10px; margin-bottom: 10px;">
						<input class="form-control" name="newprice" type="text"
							value="<%=book.getPrice()%>" />
					</dd>

					<dt style="margin-top: 10px;">详细描述</dt>
					<dd style="margin-top: 10px; margin-bottom: 10px;">
						<textarea class="form-control" name="newdesc"><%=book.getDesc()%></textarea>
					</dd>

					<dt style="margin-top: 10px;">图片</dt>
					<dd style="margin-top: 10px; margin-bottom: 10px;">
						<input style="margin-top: 10px;" type="file" value="" />
					</dd>

				</dl>

				<div class="fo
				rm-group">
					<div class="col-md-offset-2 col-md-10">
						<input type="submit" value="提  交" class="btn btn-primary"
							style="font-family: 微软雅黑;" />
					</div>
				</div>

			</form>
		</div>

		<%@include file="../footer.jsp"%>
	</div>
</body>
</html>
