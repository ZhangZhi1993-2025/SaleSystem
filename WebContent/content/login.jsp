<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>登陆</title>
</head>
<body>
	<%@include file="header.jsp"%>
	<div class="container body-content">
		<h2 style="font-family: 微软雅黑;">用户登陆</h2>
		<div class="row">
			<div class="col-md-8">
				<section id="loginForm">
					<form action="/booksales/login_validate" method="post"
						class="form-horizontal" role="form">

						<h4 style="font-family: 微软雅黑;">请使用您的BookSales账号登陆</h4>
						<hr />

						<div class="form-group">
							<label class="col-md-2 control-label" for="accountNum"
								style="font-family: 微软雅黑;">账号</label>
							<div class="col-md-10">
								<input class="form-control" data-val="true"
									data-val-required="用户 字段是必需的。" id="accountNum"
									name="accountNum" type="text" value="" /> <span
									class="field-validation-valid text-danger"
									data-valmsg-for="accountNum" data-valmsg-replace="true"></span>
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
										id="RememberMe" name="RememberMe" type="checkbox" value="true" />
									<input name="RememberMe" type="hidden" value="false" /> <label
										for="RememberMe">记住密码</label>
								</div>
							</div>
						</div>

						<div class="form-group">
							<div class="col-md-offset-2 col-md-10">
								<input type="submit" value="登 陆" class="btn btn-primary" />
							</div>
						</div>
						
					</form>
				</section>
			</div>
		</div>
		<%@include file="footer.jsp"%>
	</div>

	<script src="/booksales/js/jquery.validate.js"></script>
	<script src="/booksales/js/jquery.validate.unobtrusive.js"></script>

</body>
</html>
