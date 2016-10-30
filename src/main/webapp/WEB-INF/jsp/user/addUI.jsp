<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>后台管理</title>
<jsp:include page="../common/common.jsp"></jsp:include>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery.validate.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/messages_zh.min.js"></script>
</head>
<style>
.error {
	color: red;
	font-size: 14px;
}
</style>
<body>
	<jsp:include page="../common/top.jsp"></jsp:include>
	<div class="container clearfix">
		<jsp:include page="../common/left.jsp"></jsp:include>
		<div class="main-wrap">
			<div class="result-wrap">
				<div class="result-content">
					<form action="add" method="post" id="myform" name="myform">
						<table class="insert-tab" width="100%">
							<tbody>
								<tr>
									<th width="120"><i class="require-red">*</i>分类：</th>
									<td><select id="isEnterpriseUser" name="isEnterpriseUser"
										id="catid" class="required" required>
											<option value="">请选择</option>
											<option value="0">普通用户</option>
											<option value="1">企业用户</option>
									</select></td>
								</tr>
								<tr>
									<th><label for=""><i class="require-red">*</i>用户名：</label></th>
									<td><input id="userName" class="common-text"
										name="userName" size="50" type="text" required maxlength="8">(由字母数字组成，少于8位)</td>
								</tr>
								<tr>
									<th><i class="require-red">*</i>密码：</th>
									<td><input id="password" class="common-text"
										name="password" size="50" type="password" required
										maxlength="16"> (由字母数字组成，少于16位)</td>
								</tr>
								<tr>
									<th></th>
									<td><input type="submit" class="btn btn-primary btn6 mr10"
										value="提交"> <input class="btn btn6"
										onclick="history.go(-1)" value="返回" type="button"></td>
								</tr>
							</tbody>
						</table>
					</form>
				</div>
			</div>

		</div>
	</div>
</body>
<script type="text/javascript">
	$(document).ready(function() {
		$("#myform").validate({
			submitHandler : function(form) {
				addUser();
			}
		});
	});

	function addUser() {
		var userName = $("#userName").val();
		var password = $("#password").val();
		var isEnterpriseUser = $("#isEnterpriseUser").val();
		if (isEnterpriseUser == '') {
			alert("请选择用户类型");
			return;
		}
		var data = {
			"userName" : userName,
			"password" : password,
			"isEnterpriseUser" : isEnterpriseUser
		};
		url = "add";
		$.blockUI({
			message : "正在操作，请稍后..."
		});
		$.ajax({
			url : url,
			contentType : "application/json;charset=utf-8",
			data : JSON.stringify(data),
			dataType : "json",
			type : "POST",
			error : function(data) {
				$.blockUI({
					message : "操作失败"
				});
				window.setTimeout("$.unblockUI()", 1000);
			},
			success : function(result) {
				if (result.state == "1") {
					$.blockUI({
						message : "操作成功"
					});
					window.setTimeout("history.go(-1)", 1000);
				} else if (result.state == "0") {
					$.blockUI({
						message : "操作失败"
					});
					window.setTimeout("$.unblockUI()", 1000);
				} else if (result.state == "3") {
					$.blockUI({
						message : "请求参数不合法"
					});
					window.setTimeout("$.unblockUI()", 1000);
				} else if (result.state == "2") {
					$.blockUI({
						message : "服务器异常"
					});
					window.setTimeout("$.unblockUI()", 1000);
				} else if (result.state == "5") {
					$.blockUI({
						message : "该用户已存在"
					});
					window.setTimeout("$.unblockUI()", 1000);
				}
			}
		});
	}
</script>
</html>

