<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="com.somebody.po.Administrator" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="topbar-wrap white">
	<div class="topbar-inner clearfix">
		<div class="topbar-logo-wrap clearfix">
			<h1 class="topbar-logo none">
				<a href="index.html" class="navbar-brand">后台管理</a>
			</h1>
			<ul class="navbar-list clearfix">
				<li><a class="on"
					href="<%=request.getContextPath()%>/manager/homeController/index">首页</a></li>
			</ul>
		</div>
		<div class="top-info-wrap">
			<ul class="top-info-list clearfix">
				<li>
	<%=((Administrator) request.getSession().getAttribute("administrator")).getName() %>
			
				
				
				
				</li>
				<li><a href="../administratorController/updatePasswordUI">修改密码</a></li>
				<li><a href="../administratorController/exit">退出</a></li>
			</ul>
		</div>
	</div>
</div>
