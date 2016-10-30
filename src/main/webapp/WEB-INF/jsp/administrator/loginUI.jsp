<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>后台管理</title>
<%
	response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
	response.setHeader("Expires", "0");
	response.setHeader("Pragma", "no-cache");
%>
<jsp:include page="../common/common.jsp"></jsp:include>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery.validate.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/messages_zh.min.js"></script>
<link href="<%=request.getContextPath()%>/css/admin_login.css"
	rel="stylesheet" type="text/css" />
</head>
<body>
	<div class="admin_login_wrap">
		<h1>后台管理</h1>
		<div class="adming_login_border">
			<div class="admin_input">
				<form id="form" action="login" method="post">
					<ul class="admin_items">
						<li><label for="name">用户名：</label> <input type="text"
							name="name" value="admin" id="name" size="40" required
							maxlength="8" class="admin_input_style" /></li>
						<li><label for="password">密码：</label> <input type="password"
							name="password" value="admin" id="password" size="40" required
							maxlength="16" class="admin_input_style" /></li>
						<li><label for="code">验证码： <img id="codeImage"
								name="code" alt="验证码"
								src="<%=request.getContextPath()%>/manager/administratorController/code"></label>
							<input type="text" id="code" name="code" size="40"
							class="admin_input_style" required /></li>
						<li><input type="submit" value="提交" class="btn btn-primary">
						</li>
					</ul>
				</form>
			</div>
		</div>
	</div>
</body>
<style>
.error {
	color: red;
	font-size: 14px;
}
</style>
<script type="text/javascript">
	$(document).ready(function() {
		$("#codeImage").click(function() {
			var i = Math.random();
			$("#codeImage").attr("src", "code?i=" + i);
		});
		$("#form").validate({
			submitHandler : function(form) {
				login();
			}
		});

	});

	function login() {
		var url = "login";
		$.ajax({
			cache : true,
			type : "POST",
			url : url,
			data : $('#form').serialize(),
			async : false,
			dataType : "json",
			error : function(request) {
				alert("Connection error");
			},
			success : function(data) {
				var state = data.state;

				switch (state) {
				case '1':
					location.href = "../homeController/index";
					break;
				case '2':
					$.blockUI({
						message : "服务器异常"
					});
					window.setTimeout("$.unblockUI()", 1000);
					break;
				case '3':
					$.blockUI({
						message : "验证码输入错误"
					});
					window.setTimeout("$.unblockUI()", 1000);
					break;
				case '4':
					$.blockUI({
						message : "用户名或密码错误"
					});
					window.setTimeout("$.unblockUI()", 1000);
					break;
				}
			}
		});
	}
</script>
</html>