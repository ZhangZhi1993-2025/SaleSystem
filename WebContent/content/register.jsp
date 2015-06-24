<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>注册</title>
</head>
<body>
	<%@include file="header.jsp"%>
	<div class="container body-content">
		<h2 style="font-family: 微软雅黑;">用户注册</h2>
		<div class="row">
			<div class="col-md-8">
				<section id="loginForm">
					<form action="/booksales/register_validate" method="post"
						class="form-horizontal" role="form">

						<h4 style="font-family: 微软雅黑;">请填写您的账号名与密码</h4>
						<hr />

						<div class="form-group">
							<label class="col-md-2 control-label" for="accountNum"
								style="font-family: 微软雅黑;">账号</label>
							<div class="col-md-10">
								<input class="form-control" data-val="true"
									data-val-required="账号 字段是必需的。" id="accountNum"
									name="accountNum" type="text" value="" /> <span
									class="field-validation-valid text-danger"
									data-valmsg-for="accountNum" data-valmsg-replace="true"></span>
							</div>
						</div>

						<div class="form-group">
							<label class="col-md-2 control-label" for="Password"
								style="font-family: 微软雅黑;">密码</label>
							<div class="col-md-10">
								<input class="form-control" data-val="true"
									data-val-length="密码 字段至少需要6位长度,最多不超过100位"
									data-val-length-max="100" data-val-length-min="6"
									data-val-required="密码 字段是必需的。" id="Password" name="Password"
									type="password" /> <span
									class="field-validation-valid text-danger"
									data-valmsg-for="Password" data-valmsg-replace="true"></span>
							</div>
						</div>

						<div class="form-group">
							<label class="col-md-2 control-label" for="ConfirmPassword"
								style="font-family: 微软雅黑;">密码确认</label>
							<div class="col-md-10">
								<input class="form-control" data-val="true"
									data-val-equalto="两次密码输入不一致"
									data-val-equalto-other="*.Password" id="ConfirmPassword"
									name="ConfirmPassword" type="password" /> <span
									class="field-validation-valid text-danger"
									data-valmsg-for="ConfirmPassword" data-valmsg-replace="true"></span>
							</div>
						</div>

						<div class="form-group">
							<div class="col-md-offset-2 col-md-10">
								<input type="submit" class="btn btn-primary" value="注  册" />
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
