<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>后台管理</title>
   	<jsp:include page="../common/common.jsp"></jsp:include>
</head>
<body>
<jsp:include page="../common/top.jsp"></jsp:include>
<div class="container clearfix">
    <jsp:include page="../common/left.jsp"></jsp:include>
    <!--/sidebar-->
    <div class="main-wrap">
        
        <div class="result-wrap">
            <div class="result-title">
                <h1>系统基本信息</h1>
            </div>
            <div class="result-content">
                <ul class="sys-info-list">
                    <li>
                        <label class="res-lab">操作系统</label><span class="res-info"><%=System.getProperty("os.name") %></span>
                    </li>
                    <li>
                        <label class="res-lab">运行环境</label><span class="res-info"><%=System.getProperty("java.vm.specification.name") %></span>
                    </li>
                    <li>
                        <label class="res-lab">域名/IP</label><span class="res-info"><%=request.getServerName() %></span>
                    </li>
                
                </ul>
            </div>
        </div>
       
    </div>
    <!--/main-->
</div>
</body>
</html>