<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"  isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<!DOCTYPE html>
<html>
<head>
	<%
	  response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");
	  response.setHeader("Expires", "0");
	  response.setHeader("Pragma","no-cache"); 
	%>
    <meta charset="UTF-8">
    <title>后台管理</title>
   	<jsp:include page="../common/common.jsp"></jsp:include>
</head>
<body>
<jsp:include page="../common/top.jsp"></jsp:include>
<div class="container clearfix">
    <jsp:include page="../common/left.jsp"></jsp:include>
  	 <div class="main-wrap">

       <div class="search-wrap">
            <div class="search-content">
                <form method="post">
                    <table class="search-tab">
                        <tr>
                            <th width="120">选择分类:</th>
                            <td>
                                <select id = "isEnterpriseUser" name="search-sort">
                                    <option value="2">全部</option>
                                <c:choose>
	                                <c:when test="${isEnterpriseUser eq '1'}">
	                                    <option value="1" selected="selected">企业用户</option>
	                                    <option value="0">普通用户</option>
	                                 </c:when>
	                                 <c:when test="${isEnterpriseUser eq '0'}">
	                                    <option value="1" >企业用户</option>
	                                    <option value="0" selected="selected">普通用户</option>
	                                 </c:when>
	                                 
	                                 <c:otherwise>
	                                	<option value="1" >企业用户</option>
	                                    <option value="0" >普通用户</option>
	                                 </c:otherwise>
                                 </c:choose>
                                </select>
                            </td>
                            <th width="70">关键字:</th>
                            <td><input class="common-text" id = "keyword" placeholder="关键字" name="keyword" value="${keyword}" id="" type="text"></td>
                          
                            <td><button type = "button" id = "queryButton"  class="btn btn-primary btn2">查询</button></td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>
        <div class="result-wrap">
            <form name="myform" id="myform" method="post">
                <div class="result-title">
                    <div class="result-list">
                        <a href="addUI"><i class="icon-font"></i>新增</a>
                 
                    </div>
                </div>
                <div class="result-content">
                    <table class="result-tab" width="100%">
                        <tr>
                           
                            <th>用户名</th>
                            <th>用户类型</th>
                            <th>注册时间</th>
                            <th>操作</th>
                        </tr>
                        <c:forEach items = "${userList}" var = "user">
                         <tr>
                         	<td>${user.name}</td>
                         	<td>
                         	<c:if test="${user.isEnterpriseUser eq '1' }">
                         		企业用户
                         	</c:if>
                         	<c:if test="${user.isEnterpriseUser eq '0' }">
                         		普通用户
                         	</c:if>
                         	</td>
                         	<td>
                         		<fmt:formatDate value="${user.registerTime}" pattern="yyyy-MM-dd  HH:mm:ss"/>
                         	</td>
                         	<td>
                                <a onclick = "deleteUser('${user.name}')"  class="link-del" href="javascript:void(0)">删除</a>
                            </td>
                        </tr>
                        
                        </c:forEach>
                       
                    </table>
                    <div id = "page" class="list-page">
                    	<c:if test="${page != 1 }">
                    		<a href = "list?keyword=${keyword}&isEnterpriseUser=${isEnterpriseUser}&page=${page-1}">上一页</a>
                    	</c:if>
                    	<c:choose>
	                    	<c:when test="${page-5 > 0}">
	                    		<c:forEach begin="${page-5}" end="${page-1}" var="i">
	                    			<a href = "list?keyword=${keyword}&isEnterpriseUser=${isEnterpriseUser}&page=${i}">${i}</a>
	                    		</c:forEach>
	                    	</c:when>
	                    	<c:otherwise>
	                    		<c:forEach begin="1" end="${page-1}" var="i">
	                    			<a href = "list?keyword=${keyword}&isEnterpriseUser=${isEnterpriseUser}&page=${i}">${i}</a>
	                    		</c:forEach>
	                    	</c:otherwise>
                    	</c:choose>
                    	${page}
                    	<c:choose>
	                    	<c:when test="${page+5 < pageCount}">
	                    		<c:forEach begin="${page+1}" end="${page+5}" var="i">
	                    			<a href = "list?keyword=${keyword}&isEnterpriseUser=${isEnterpriseUser}&page=${i}">${i}</a>
	                    		</c:forEach>
	                    	</c:when>
	                    	<c:otherwise>
	                    		<c:forEach begin="${page+1}" end="${pageCount}" var="i">
	                    			<a href = "list?keyword=${keyword}&isEnterpriseUser=${isEnterpriseUser}&page=${i}">${i}</a>
	                    		</c:forEach>
	                    	</c:otherwise>
                    	</c:choose>
                    	<%--
                    	<c:forEach begin="1" end="${pageCount}" var="i">
                    		<c:choose>
                    			<c:when test="${page eq i }">${i}</c:when>
                    			<c:otherwise>
                    				<a href = "list?keyword=${keyword}&isEnterpriseUser=${isEnterpriseUser}&page=${i}">${i}</a>
                    			</c:otherwise>
                    		</c:choose>
                    		
                    	</c:forEach>
                    	 --%>
                    	<c:if test="${page != pageCount}">
                    		<a href = "list?keyword=${keyword}&isEnterpriseUser=${isEnterpriseUser}&page=${page+1}">下一页</a>
                    	</c:if>
                    	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${page}/${pageCount} 页
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
$(document).ready(function(){
	$("#queryButton").click(function(){
		var keyword = $("#keyword").val() ; 
		if(keyword == "")
			{
				alert("请输入关键字") ;
			}
		else
			{
				var isEnterpriseUser = $("#isEnterpriseUser").val() ; 
				url = "list?keyword="+keyword+"&isEnterpriseUser="+isEnterpriseUser ; 
				location.href = url ; 
			}
	}) ; 
	
	
	
	
}) ; 
</script>
</html>

