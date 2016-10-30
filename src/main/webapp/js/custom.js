function deleteUser(userName) {
	if (confirm('删除用户后将会删除该用户的所有订单，且不可恢复') == false) {
		return;
	}
	url = "delete";
	var data = {
		"userName" : userName
	};
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
			window.setTimeout("location.reload()", 1000);
		},
		success : function(result) {
			var messageText = "";
			if (result.state == "1") {
				messageText = "操作成功";
			}

			else {
				messageText = "操作成功";
			}
			$.blockUI({
				message : messageText
			});
			window.setTimeout("location.reload()", 1000);
		}
	});
}

function deletePackage(packageID) {
	if (confirm('删除套餐后将会删除该套餐的所有订单，且不可恢复') == false) {
		return;
	}
	url = "delete";
	var data = {
		"packageID" : packageID
	};
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
		},
		success : function(result) {
			var messageText = "";
			if (result.state == "1") {
				messageText = "操作成功";
			} else if (result.state == "3") {
				messageText = "请求参数不合法";
			} else if (result.state == "2") {
				messageText = "服务器异常";
			} else if (result.state == "0") {
				messageText = "操作失败";
			}
			$.blockUI({
				message : messageText
			});
			window.setTimeout("location.reload()", 1000);
		}
	});
}

function updatePackage(packageID) {
	url = "updateUI";
	location.href = url + "?packageID=" + packageID;
}

function deleteOrders(ordersID) {
	if (confirm('如订单未过期，删除订单后用户无法使用该套餐') == false) {
		return;
	}
	url = "delete";
	var data = {
		"ordersID" : ordersID
	};
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
			$.unblockUI();
			alert("操作失败");
			location.reload();
		},
		success : function(result) {
			$.unblockUI();
			var messageText = "";
			if (result.state == "1") {
				messageText = "操作成功";
			} else {
				messageText = "操作失败";
			}
			$.blockUI({
				message : messageText
			});
			window.setTimeout("location.reload()", 1000);
		}
	});
}
