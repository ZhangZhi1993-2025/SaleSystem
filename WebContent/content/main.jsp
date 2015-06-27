<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="cn.edu.njnu.viewmodel.BookViewModel"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>BookSales</title>
</head>
<body>
	<%@include file="header.jsp"%>
	<div class="container body-content">
		<div class="jumbotron">
			<h1>BookSales</h1>
			<p class="lead" style="font-family: 微软雅黑;">BookSales是一个为大家提供在线购书服务的好网站!</p>
			<p>
				<a href="/booksales/hot_books?page=1" class="btn btn-primary btn-lg">热卖图书
					&raquo;</a>
			</p>
		</div>
		<div class="row">
			<div class="col-md-4">
				<h2>Welcome</h2>
				<p>Mark your calendar now with the dates of The Friends Book
					Sales. Books, CDs, DVDs, audio and video tapes, books-on-tape/CD
					and record albums will be sold at bargain prices. Items may be
					purchased with cash, check, MasterCard or Visa.</p>
				<p>
					<a class="btn btn-default" href="http://cl.yaocl.me/index.php">Learn
						more &raquo;</a>
				</p>
			</div>
			<div class="col-md-4">
				<h2>Find Your Favorite</h2>
				<p>Shop for books by your favorite authors or in your areas of
					interest. Feel free to come by to just to browse. We’re sure you
					will find something which will appeal to you.</p>
				<p>
					<a class="btn btn-default" href="http://cl.yaocl.me/index.php">Learn
						more &raquo;</a>
				</p>
			</div>
			<div class="col-md-4">
				<h2>Abundant Resources</h2>
				<p>We offer gently used hardback and paperback books in every
					genre. Our fiction books include mystery, horror, romance, science
					fiction, anime and westerns.</p>
				<p>
					<a class="btn btn-default" href="http://cl.yaocl.me/index.php">Learn
						more &raquo;</a>
				</p>
			</div>
		</div>
		<%@include file="footer.jsp"%>
	</div>
</body>
</html>
