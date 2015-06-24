<%@ page contentType="text/html; charset=utf-8"%>
<div class="navbar navbar-inverse navbar-fixed-top">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
		</div>
		<div class="navbar-collapse collapse">
			<ul class="nav navbar-nav">
				<li><a href="/">首页</a></li>
				<li><a href="/Home/About">关于</a></li>
				<li><a href="/Home/Contact">联系我们</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="/Account/Login?returnUrl=%2F" id="loginLink">登陆</a></li>
			</ul>
		</div>
	</div>
</div>