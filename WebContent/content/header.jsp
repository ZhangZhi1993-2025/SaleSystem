<%@page import="jdk.nashorn.internal.ir.RuntimeNode.Request"%>
<%@ page contentType="text/html; charset=utf-8"%>
<link href="/booksales/css/bootstrap.min.css" rel="stylesheet">
<link href="/booksales/css/site.css" rel="stylesheet" />
<link href="/booksales/css/buttons.css" rel="stylesheet" />
<script src="/booksales/js/jquery.min.js"></script>
<link href="/booksales/css/star-rating.css" rel="stylesheet" />
<script src="/booksales/js/star-rating.js"></script>

<div class="navbar navbar-inverse navbar-fixed-top">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target=".navbar-collapse">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="/booksales">BookSales</a>
		</div>
		<div class="navbar-collapse collapse">
			<ul class="nav navbar-nav">
				<li><a href="/booksales/about" style="font-family: 微软雅黑;">关于</a></li>
				<li><a href="/booksales/contact" style="font-family: 微软雅黑;">联系我们</a></li>
			</ul>
			<form action="/booksales/search" class="navbar-form navbar-left"
				method="post" role="search">
				<div onmouseleave="hideConditions()" onmouseover="showConditions()">
					<div class="form-group" style="margin-top: 3px;">
						<input name="keywords" type="text" class="form-control"
							style="width: 300px;" placeholder="你想要找什么书？" />
						<button type="submit" class="button button-flat-primary"
							style="margin-left: -8px;">搜 索</button>
					</div>
					<div id="conditions"
						style="background-color: #222222; height: 60px; width: 400px; position: fixed; display: none; margin-left: -20px;">
						<select name="category" class="col-md-4"
							style="margin-top: 20px; float: left; margin-left: 25px;">
							<option>全部分类</option>
							<option>Python</option>
							<option>Algorithms</option>
							<option>Java</option>
							<option>.NET</option>
							<option>Math</option>
							<option>HTML/CSS/JS</option>
						</select>
						<div class="checkbox-inline"
							style="margin-top: 20px; margin-left: 20px;">
							<label style="font-family: 微软雅黑; color: #ffffff;"> <input
								name="price" type="checkbox" value="true"> 价格↑
							</label>
						</div>
						<div class="checkbox-inline" style="margin-top: 20px;">
							<label style="font-family: 微软雅黑; color: #ffffff;"> <input
								name="sale" type="checkbox" value="true"> 销量↓
							</label>
						</div>
						<div class="checkbox-inline" style="margin-top: 20px;">
							<label style="font-family: 微软雅黑; color: #ffffff;"> <input
								name="star" type="checkbox" value="true"> 好评↓
							</label>
						</div>
					</div>
				</div>
			</form>
			<%
				Cookie[] cookies = request.getCookies();
				boolean isLogOn = false;
				boolean isAdmin = false;
				String userid = "";
				String name = "";
				int score = 0;
				String userUrl = "";
				if (cookies != null) {
					for (int i = 0; i < cookies.length; i++) {
						if (cookies[i].getName().equals("id")) {
							userid = cookies[i].getValue();
							isLogOn = true;
							break;
						}
					}
				}
				if (isLogOn == true) {
					for (int i = 0; i < cookies.length; i++) {
						if (cookies[i].getName().equals("name"))
							name = cookies[i].getValue()+" ";
						if (cookies[i].getName().equals("score"))
							score = Integer.parseInt(cookies[i].getValue());
						if (cookies[i].getName().equals("admin"))
							isAdmin = Boolean.valueOf(cookies[i].getValue());
					}
					userUrl = "/booksales/user/history_order?user=" + userid;
			%>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="<%=userUrl%>" style="font-family: 微软雅黑;"><%=name%>
						积分:<%=score%></a></li>
				<%
					if (isAdmin == true) {
				%>
				<li><a href="/booksales/admin/add_book"
					style="font-family: 微软雅黑;">添加新书</a></li>
				<%
					}
				%>
				<li><a href="/booksales/user/logout" style="font-family: 微软雅黑;">退出</a></li>
			</ul>
			<%
				} else {
			%>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="/booksales/login" id="loginLink"
					style="font-family: 微软雅黑;">登陆</a></li>
				<li><a href="/booksales/register" id="loginLink"
					style="font-family: 微软雅黑;">注册</a></li>
			</ul>
			<%
				}
			%>

		</div>
	</div>
</div>

<script type="text/javascript">
	function showConditions() {
		$("#conditions").slideDown(200);
	}
	function hideConditions() {
		$("#conditions").slideUp(200);
	}
</script>