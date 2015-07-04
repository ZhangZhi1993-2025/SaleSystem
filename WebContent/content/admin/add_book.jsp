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

		<h2 style="font-family: 微软雅黑;">添加新书</h2>

		<div>
			<h4 style="font-family: 微软雅黑;">请填写必要的图书信息并提交</h4>
			<hr />
			<form action="/booksales/admin/add_a_book" method="post"
				class="form-horizontal" role="form" enctype="multipart/form-data">
				<dl class="dl-horizontal" style="font-family: 微软雅黑;">

					<dt style="margin-top: 10px;">书名</dt>
					<dd style="margin-top: 10px; margin-bottom: 10px;">
						<input class="form-control" name="name" type="text" value="" />
					</dd>

					<dt style="margin-top: 10px;">分类</dt>
					<dd style="margin-top: 10px; margin-bottom: 10px;">
						<input class="form-control" name="category" type="text" value="" />
					</dd>

					<dt style="margin-top: 10px;">出版社</dt>
					<dd style="margin-top: 10px; margin-bottom: 10px;">
						<input class="form-control" name="press" type="text" value="" />
					</dd>

					<dt style="margin-top: 10px;">库存</dt>
					<dd style="margin-top: 10px; margin-bottom: 10px;">
						<input class="form-control" name="amount" type="text" value="" />
					</dd>

					<dt style="margin-top: 10px;">价格</dt>
					<dd style="margin-top: 10px; margin-bottom: 10px;">
						<input class="form-control" name="price" type="text" value="" />
					</dd>

					<dt style="margin-top: 10px;">详细描述</dt>
					<dd style="margin-top: 10px; margin-bottom: 10px;">
						<textarea class="form-control" name="desc"></textarea>
					</dd>

					<dt style="margin-top: 10px;">图片</dt>
					<dd style="margin-top: 10px; margin-bottom: 10px;">
						<input style="margin-top: 10px;" name="pic" type="file" value="" />
					</dd>

				</dl>

				<div class="form-group">
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
