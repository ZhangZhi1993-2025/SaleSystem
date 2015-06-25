<%@page import="jdk.nashorn.internal.ir.RuntimeNode.Request"%>
<%@ page contentType="text/html; charset=utf-8"%>
<link href="/booksales/css/bootstrap.css" rel="stylesheet" />
<link href="/booksales/css/site.css" rel="stylesheet" />
<link href="/booksales/css/buttons.css" rel="stylesheet" />

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
			<form action="/booksales/hot_books" class="navbar-form navbar-left"
				method="post" role="search">
				<div class="form-group" style="margin-top: 3px;">
					<input name="keywords" type="text" class="form-control"
						onfocus="showConditions()" style="width: 300px;"
						placeholder="你想要什么书？" />
					<button type="submit" class="button button-flat-primary"
						style="margin-left: -8px;">搜 索</button>
				</div>
			</form>
			<%
				Cookie[] cookies = request.getCookies();
				boolean isLogOn = false;
				if (cookies != null) {
					for (int i = 0; i < cookies.length; i++) {
						if (cookies[i].getName() == "id") {
							isLogOn = true;
							break;
						}
					}
				}
				if (isLogOn == true) {
					String userid = cookies[0].getValue();
					String name = cookies[1].getValue();
					int score = Integer.parseInt(cookies[2].getValue());
					String userUrl = "/booksales/user/history_order?user=" + userid;
			%>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="<%=userUrl%>" id="loginLink"
					style="font-family: 微软雅黑;"><%=name%></a></li>
				<li><a href="<%=userUrl%>" id="loginLink"
					style="font-family: 微软雅黑;">积分: <%=score%></a></li>
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

<div id="conditions" class="col-md-offset-3"
	style="background-color: #222222; height: 60px; width: 440px; position: fixed; display: none;">

	<select id="category" name="category" class="col-md-4"
		style="margin-top: 20px; float: left; margin-left: 30px;">
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
			id="price" name="price" type="checkbox"> 价格↑
		</label>
	</div>
	<div class="checkbox-inline" style="margin-top: 20px;">
		<label style="font-family: 微软雅黑; color: #ffffff;"> <input
			id="sale" name="sale" type="checkbox"> 销量↓
		</label>
	</div>
	<div class="checkbox-inline" style="margin-top: 20px;">
		<label style="font-family: 微软雅黑; color: #ffffff;"> <input
			id="star" name="star" type="checkbox"> 好评↓
		</label>
	</div>
	<a
		style="float: right; margin-right: 20px; margin-top: 20px; white-space: nowrap; cursor: pointer;"
		onclick="hideConditions()"><img alt="关闭"
		src="/booksales/resource/close.png"></a>

</div>

<script type="text/javascript">
	function showConditions() {
		$("#conditions").slideDown(200);
	}
	function hideConditions() {
		$("#conditions").slideUp(200);
	}
</script>