<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>BookSales</title>
</head>
<body>
	<%@include file="../header.jsp"%>
	<%
		String oldPhone = (String) request.getAttribute("oldPhone");
		boolean isAlive = (boolean) request.getAttribute("isAlive");
	%>
	<div class="container body-content">
		<%
			if (isAlive != true) {
		%>
		<h1 style="font-family: 微软雅黑; color: #ff0000;">该账户已被冻结，正在审查中，一个月内无法登陆此账户!</h1>
		<div style="height: 450px;">.</div>
		<%
			} else {
		%>
		<h2 style="font-family: 微软雅黑;">用户名或密码错误</h2>

		<div class="row">
			<div class="col-md-8">
				<section id="loginForm">
					<form action="/booksales/login_validate" method="post"
						class="form-horizontal" role="form">

						<h4 style="font-family: 微软雅黑;">请使用您的BookSales账号登陆</h4>
						<hr />

						<div class="form-group">
							<label class="col-md-2 control-label" for="phone"
								style="font-family: 微软雅黑;">手机号</label>
							<div class="col-md-10">
								<input class="form-control" data-val="true"
									data-val-required="手机号 字段是必需的。" id="phone" name="phone"
									type="text" value="<%=oldPhone%>" /> <span
									class="field-validation-valid text-danger"
									data-valmsg-for="phone" data-valmsg-replace="true"></span>
							</div>
						</div>

						<div class="form-group">
							<label class="col-md-2 control-label" for="password"
								style="font-family: 微软雅黑;">密码</label>
							<div class="col-md-10">
								<input class="form-control" data-val="true"
									data-val-required="密码 字段是必需的。" id="password" name="password"
									type="password" /> <span
									class="field-validation-valid text-danger"
									data-valmsg-for="password" data-valmsg-replace="true"></span>
							</div>
						</div>

						<div class="form-group">
							<div class="col-md-offset-2 col-md-10">
								<div class="checkbox">
									<input data-val="true" data-val-required="记住密码 字段是必需的。"
										id="rememberme" name="rememberme" type="checkbox" value="true" />
									<input name="rememberme" type="hidden" value="false" /> <label
										for="rememberme">记住密码</label>
								</div>
							</div>
						</div>

						<div class="form-group">
							<div class="col-md-offset-2 col-md-10">
								<input type="submit" value="登 陆" class="btn btn-primary"
									style="font-family: 微软雅黑;" />
							</div>
						</div>

					</form>
				</section>
			</div>
		</div>
		<%
			}
		%>
		<%@include file="../footer.jsp"%>
	</div>

	<script src="/booksales/js/jquery.validate.js"></script>
	<script src="/booksales/js/jquery.validate.unobtrusive.js"></script>

</body>
</html>
