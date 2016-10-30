<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>后台管理</title>
<%
	  response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");
	  response.setHeader("Expires", "0");
	  response.setHeader("Pragma","no-cache"); 
%>
<jsp:include page="../common/common.jsp"></jsp:include>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery.validate.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/messages_zh.min.js"></script>
</head>
<body>
	<jsp:include page="../common/top.jsp"></jsp:include>
	<div class="container clearfix">
		<jsp:include page="../common/left.jsp"></jsp:include>
		<div class="main-wrap">

			<div class="result-wrap">
				<div class="result-content">
					<form action="add" method="post" id="myform" name="myform">
						<input type="hidden" id="hiddenInput" value="${packageID}">
						<table class="insert-tab" width="100%">
							<tbody>
								<tr>
									<th><i class="require-red">*</i>原密码：</th>
									<td><input id="originPassword"
										class="common-text required " id="title" name="originPassword"
										size="50" type="password" required maxlength="16">(少于16个字符)</td>
								</tr>
								<tr>
									<th><i class="require-red">*</i>新密码：</th>
									<td><input id="newPassword" class="common-text required"
										id="title" name="newPassword" size="50" type="password"
										required maxlength="16"> (少于16个字符)</td>
								</tr>
								<tr>
									<th><i class="require-red">*</i>再次输入新密码：</th>
									<td><input id="twiceNewPassword"
										class="common-text required" id="title" equalTo = "#newPassword"
										name="twiceNewPassword" size="50" type="password" required
										maxlength="16"> (少于16个字符)</td>
								</tr>
								<tr>
									<th></th>
									<td><input type="submit" value="提交"
										class="btn btn-primary btn6 mr10"> <input
										class="btn btn6" onclick="history.go(-1)" value="返回"
										type="button"></td>
								</tr>
							</tbody>
						</table>
					</form>
				</div>
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
		$("#myform").validate({
			submitHandler : function(form) {
				updatePassword();
			}
		});
	});

	function updatePassword() {
		var originPassword = $("#originPassword").val();
		var newPassword = $("#newPassword").val();
		var twiceNewPassword = $("#twiceNewPassword").val();
		
		var data = {
			"originPassword" : originPassword,
			"newPassword" : newPassword
		};
		url = "updatePassword";
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
				$.blockUI({message : "操作失败"}) ; 
				window.setTimeout("$.unblockUI()" , 1000) ; 
			},
			success : function(result) {
				$.unblockUI();
				if (result.state == "1") {
					$.blockUI({message : "操作成功"}) ; 
					window.setTimeout("history.go(-1)" , 1000) ; 
				} else if (result.state == "0") {
					$.blockUI({message : "操作失败"}) ; 
					window.setTimeout("$.unblockUI()" , 1000) ; 
				} else if (result.state == "2") {
					$.blockUI({message : "服务器异常"}) ; 
					window.setTimeout("$.unblockUI()" , 1000) ; 
				} else if (result.state == "3") {
					$.blockUI({message : "原密码输入错误"}) ; 
					window.setTimeout("$.unblockUI()" , 1000) ; 
				}
			}
		});
	}
</script>
</html>

