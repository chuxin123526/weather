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
									<th><i class="require-red">*</i>名称：</th>
									<td><input id="name" value="${packageObject.name }"
										class="common-text required"  name="name" size="50" required maxlength="10"
										type="text"> (少于10个字符)</td>
								</tr>
								<tr>
									<th><i class="require-red">*</i>价格：</th>
									<td><input id="price" value="${packageObject.price }"
										class="common-text" name="price" size="50" type="text" required digits="true">
									(请输入整数)</td>
								</tr>
								<tr>
									<th><i class="require-red">*</i>详细描述：</th>
									<td><textarea id="detailDescribe" rows="10" cols="50" required maxlength="100"
											name="detailDescribe">${packageObject.detailDescribe }</textarea>
										(少于100个字符)</td>
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
				updatePackage();
			}
		});
	});

	function updatePackage() {
		var packageID = $("#hiddenInput").val();
		var name = $("#name").val();
		var price = $("#price").val();
		var detailDescribe = $("#detailDescribe").val();

		
		var data = {
			"packageID" : packageID,
			"name" : name,
			"price" : price,
			"detailDescribe" : detailDescribe
		};
		url = "update";
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
				$.unblockUI();
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
				}
			}
		});
	}
</script>
</html>

