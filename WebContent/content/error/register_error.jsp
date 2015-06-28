<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>注册</title>
</head>
<body>
	<%@include file="../header.jsp"%>
	<%
		String errorReason = (String) request.getAttribute("errorReason");
	%>
	<div class="container body-content">
		<h2 style="font-family: 微软雅黑;"><%=errorReason%></h2>
		<div class="row">
			<div class="col-md-8">
				<section id="loginForm">
					<form action="/booksales/register_validate" method="post"
						class="form-horizontal" role="form">

						<h4 style="font-family: 微软雅黑;">请填写您的手机号与密码</h4>
						<hr />

						<div class="form-group">
							<label class="col-md-2 control-label" for="phone"
								style="font-family: 微软雅黑;">手机号</label>
							<div class="col-md-10">
								<input class="form-control" data-val="true"
									data-val-required="手机号 字段是必需的。" id="phone" name="phone"
									type="text" value="" /> <span
									class="field-validation-valid text-danger"
									data-valmsg-for="phone" data-valmsg-replace="true"></span>
							</div>
						</div>

						<div class="form-group">
							<label class="col-md-2 control-label" for="password"
								style="font-family: 微软雅黑;">密码</label>
							<div class="col-md-10">
								<input class="form-control" data-val="true"
									data-val-length="密码 字段至少需要6位长度,最多不超过100位"
									data-val-length-max="100" data-val-length-min="6"
									data-val-required="密码 字段是必需的。" id="password" name="password"
									type="password" /> <span
									class="field-validation-valid text-danger"
									data-valmsg-for="password" data-valmsg-replace="true"></span>
							</div>
						</div>

						<div class="form-group">
							<label class="col-md-2 control-label" for="confirmpassword"
								style="font-family: 微软雅黑;">密码确认</label>
							<div class="col-md-10">
								<input class="form-control" data-val="true"
									data-val-equalto="两次密码输入不一致"
									data-val-equalto-other="*.Password" id="confirmpassword"
									name="confirmpassword" type="password" /> <span
									class="field-validation-valid text-danger"
									data-valmsg-for="confirmpassword" data-valmsg-replace="true"></span>
							</div>
						</div>

						<div class="form-group">
							<div class="col-md-offset-2 col-md-10">
								<input type="submit" class="btn btn-primary" value="注  册"
									style="font-family: 微软雅黑;" />
							</div>
						</div>

					</form>
				</section>
			</div>
		</div>
		<%@include file="../footer.jsp"%>
	</div>

	<script src="/booksales/js/jquery.validate.js"></script>
	<script src="/booksales/js/jquery.validate.unobtrusive.js"></script>

</body>
</html>
