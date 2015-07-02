<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="cn.edu.njnu.viewmodel.UserViewModel"%>
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

		<h2 style="font-family: 微软雅黑;">用户管理</h2>
		<%
			List<UserViewModel> users = (List<UserViewModel>) request
					.getAttribute("users");
		%>
		<table class="table">
			<tr>
				<th>用户id</th>
				<th>昵称</th>
				<th>积分</th>
				<th>激活状态</th>
			</tr>
			<%
				for (int i = 0; i < users.size(); i++) {
					String switchid = "switch" + users.get(i).getId();
			%>
			<tr>
				<td><%=users.get(i).getId()%></td>
				<td><%=users.get(i).getName()%></td>
				<td><%=users.get(i).getScore()%></td>
				<%
					if(users.get(i).isAlive() == true) {
				%>
				<td><input id="<%=switchid%>"
					onchange="freezeUser(<%=users.get(i).getId()%>)" type="checkbox"
					name="my-checkbox" checked /></td>
				<%
					} else {
				%>
				<td><input id="<%=switchid%>"
					onchange="freezeUser(<%=users.get(i).getId()%>)" type="checkbox"
					name="my-checkbox" /></td>
				<%
					}
				%>
			</tr>
			<%
				}
			%>
		</table>
		<%@include file="../footer.jsp"%>
	</div>
	<script type="text/javascript">
		$("[name='my-checkbox']").bootstrapSwitch();
		function freezeUser(userid) {
			var id="#switch" + userid;
			if ($(id).is(':checked') == false) {
				$.ajax({
					type : "GET",
					url : "/booksales/admin/user_freeze",
					data : {
						user : userid
					},
					dataType : "json",
					success : function(data) {
						if (data == true)
							alert("冻结成功!");
						else
							alert("冻结失败,请重试!");
					}
				});
			} else {
				$.ajax({
					type : "GET",
					url : "/booksales/admin/user_unfreeze",
					data : {
						user : userid
					},
					dataType : "json",
					success : function(data) {
						if (data == true)
							alert("解冻成功!");
						else
							alert("解冻失败,请重试!");
					}
				});
			}
		}
	</script>
</body>
</html>
