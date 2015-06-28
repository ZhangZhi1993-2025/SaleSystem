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
		String currentOrderUrl = "/booksales/user/current_order?user="
				+ userid;
		String updateAccountUrl = "/booksales/user/update_account?user="
				+ userid;
		String shoppingcarUrl = "/booksales/user/car_content?user="
				+ userid;
		String feedbackUrl = "/booksales/user/feedback?user=" + userid;
		String changePhoneUrl = "/booksales/user/change_phone_number?user="
				+ userid;
		String changePasswordUrl = "/booksales/user/change_password?user="
				+ userid;
		String changeNameUrl = "/booksales/user/change_name?user=" + userid;
	%>
	<div class="container body-content">
		<div style="width: 20%; height: 100%; float: left; color: transparent">.</div>
		<div
			style="position: fixed; text-align: center; width: 15%; background-color: #f7f7f7; height: auto; margin-top: 20px;">
			<div style="background-color: #428bca; height: 7px; width: 100%;">.</div>
			<ul class="nav nav-pills nav-stacked">
				<li><a href="<%=userUrl%>" style="font-family: 微软雅黑;">历史订单列表</a></li>
				<li><a href="<%=currentOrderUrl%>" style="font-family: 微软雅黑;">当前订单列表</a></li>
				<li><a href="<%=updateAccountUrl%>" style="font-family: 微软雅黑;">修改账户信息</a></li>
				<li><a href="<%=shoppingcarUrl%>" style="font-family: 微软雅黑;">我的购物车</a></li>
				<li><a href="<%=feedbackUrl%>" style="font-family: 微软雅黑;">待评价的商品</a></li>
			</ul>
			<div style="background-color: #428bca; height: 7px; width: 100%;">.</div>
		</div>
		<div style="float: left; width: 80%; height: auto; padding-left: 4%">

			<h2 style="font-family: 微软雅黑;">修改账户信息</h2>

			<div class="row">
				<div class="col-md-8">
					<section id="loginForm">
						<form action="<%=changePhoneUrl%>" method="post"
							class="form-horizontal" role="form">

							<h4 style="font-family: 微软雅黑;">修改手机号</h4>
							<hr />
							<div class="form-group">
								<label class="col-md-2 control-label" for="newphone"
									style="font-family: 微软雅黑;">新手机号</label>
								<div class="col-md-10">
									<input class="form-control" id="newphone" name="newphone"
										type="text" value="" />
								</div>
							</div>
							<div class="form-group">
								<div class="col-md-offset-2 col-md-3">
									<input type="submit" value="修  改" class="btn btn-primary"
										style="font-family: 微软雅黑;" />
								</div>
							</div>
						</form>

						<form action="<%=changePasswordUrl%>" method="post"
							class="form-horizontal" role="form">
							<h4 style="font-family: 微软雅黑;">修改密码</h4>
							<hr />
							<div class="form-group">
								<label class="col-md-2 control-label" for="oldpassword"
									style="font-family: 微软雅黑;">原密码</label>
								<div class="col-md-10">
									<input class="form-control" data-val="true"
										data-val-required="原密码 字段是必需的。" id="oldpassword"
										name="oldpassword" type="password" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-2 control-label" for="newpassword"
									style="font-family: 微软雅黑;">新密码</label>
								<div class="col-md-10">
									<input class="form-control" data-val="true"
										data-val-required="新密码 字段是必需的。" id="newpassword"
										name="newpassword" type="password" />
								</div>
							</div>
							<div class="form-group">
								<div class="col-md-offset-2 col-md-3">
									<input type="submit" value="修  改" class="btn btn-primary"
										style="font-family: 微软雅黑;" />
								</div>
							</div>
						</form>

						<form action="<%=changeNameUrl%>" method="post"
							class="form-horizontal" role="form">
							<h4 style="font-family: 微软雅黑;">修改昵称</h4>
							<hr />
							<div class="form-group">
								<label class="col-md-2 control-label" for="newname"
									style="font-family: 微软雅黑;">新昵称</label>
								<div class="col-md-10">
									<input class="form-control" id="newname" name="newname"
										type="text" value="" />
								</div>
							</div>
							<div class="form-group">
								<div class="col-md-offset-2 col-md-3">
									<input type="submit" value="修  改" class="btn btn-primary"
										style="font-family: 微软雅黑;" />
								</div>
							</div>
						</form>
					</section>
				</div>
			</div>
		</div>
		<div
			style="width: 100%; height: 120px; clear: both; color: transparent">.</div>
		<%@include file="../footer.jsp"%>
	</div>
</body>
</html>
