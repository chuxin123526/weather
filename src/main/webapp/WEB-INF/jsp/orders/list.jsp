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
    
     <link rel="stylesheet" href="<%=request.getContextPath() %>/css/jquery-ui.theme.min.css">
     <link rel="stylesheet" href="<%=request.getContextPath() %>/css/jquery-ui.min.css">
	 <script src="<%=request.getContextPath() %>/js/jquery-ui.min.js"></script>
	 <script src="<%=request.getContextPath() %>/js/datepicker-zh-TW.js"></script>
	  <script>
		  $( function() {
			  $( "#datepickerBegin" ).datepicker( $.datepicker.regional[ "zh-TW" ] );
			    
			  $( "#datepickerEnd" ).datepicker( $.datepicker.regional[ "zh-TW" ] );
			   
		  } );
	  </script>
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
                                <select id = "packageID" name="search-sort">
                                    <option  value="0">全部</option>
	                               	<c:forEach items="${packageList}" var="packageObject">
	                               	<c:choose>
	                               		<c:when test="${packageObject.id eq packageID}">
	                               			<option selected="selected" value="${packageObject.id }">${packageObject.name }</option>
	                               		</c:when>
	                               		<c:otherwise>
	                               			<option value="${packageObject.id }">${packageObject.name }</option>
	                               		</c:otherwise>
	                               	</c:choose>
	                               	</c:forEach>
                                </select>
                            </td>
                            <th width="70">时间:</th>
                           	<td><input id = "datepickerBegin" class="common-text" type="text" value = "${beginTime}" /></td>
                           	<td>&nbsp;&nbsp;至&nbsp;&nbsp;</td>
                          	
                          	<td><input id = "datepickerEnd" class="common-text" type="text" value = "${endTime}" /></td>
                            <td><button type = "button" id = "queryButton"  class="btn btn-primary btn2">查询</button></td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>
        <div class="result-wrap">
            <form name="myform" id="myform" method="post">
                <div class="result-title">
                   
                </div>
                <div class="result-content">
                    <table class="result-tab" width="100%">
                        <tr>
                            <th>订单号</th>
                            <th>用户名</th>
                            <th>套餐名称</th>
                            <th>购买时长(年)</th>
                            
                            <th>总价</th>
                            <th>购买时间</th>
                            <th>到期时间</th>
                             <th>操作</th>
                        </tr>
                        <c:forEach items = "${ordersList}" var = "ordersObject">
                         <tr>
                         	<td>
                         		${ordersObject.id }
                         	</td>
                         	<td>
                         		${ordersObject.userName }
                         	</td>
                         	
                         	<td>
                         		${ordersObject.packageName }
                         	</td>
                         	<td>
                         		${ordersObject.duration }
                         	</td>
                         	<td>
                         		${ordersObject.price }
                         	</td>
                         	<td>
                         		<fmt:formatDate value="${ordersObject.buyTime}"  pattern="yyyy-MM-dd  HH:mm:ss"/>
                         	</td>
                         	<td>
                         		<fmt:formatDate value="${ordersObject.deadline}"  pattern="yyyy-MM-dd  HH:mm:ss"/>
                         	</td>
                         	
                         	<td>
                                <a onclick = "deleteOrders('${ordersObject.id}')"  class="link-del" href="javascript:void(0)">删除</a>
                            </td>
                        </tr>
                        
                        </c:forEach>
                       
                    </table>
                    <div id = "page" class="list-page">
                    	<c:if test="${page != 1 }">
                    		<a href = "list?packageID=${packageID}&beginTime=${beginTime}&endTime=${endTime}&page=${page-1}">上一页</a>
                    	</c:if>
                    	<c:choose>
	                    	<c:when test="${page-5 > 0}">
	                    		<c:forEach begin="${page-5}" end="${page-1}" var="i">
	                    			<a href = "list?packageID=${packageID}&beginTime=${beginTime}&endTime=${endTime}&page=${i}">${i}</a>
	                    		</c:forEach>
	                    	</c:when>
	                    	<c:otherwise>
	                    		<c:forEach begin="1" end="${page-1}" var="i">
	                    			<a href = "list?packageID=${packageID}&beginTime=${beginTime}&endTime=${endTime}&page=${i}">${i}</a>
	                    		</c:forEach>
	                    	</c:otherwise>
                    	</c:choose>
                    	${page}
                    	<c:choose>
	                    	<c:when test="${page+5 < pageCount}">
	                    		<c:forEach begin="${page+1}" end="${page+5}" var="i">
	                    			<a href = "list?packageID=${packageID}&beginTime=${beginTime}&endTime=${endTime}&page=${i}">${i}</a>
	                    		</c:forEach>
	                    	</c:when>
	                    	<c:otherwise>
	                    		<c:forEach begin="${page+1}" end="${pageCount}" var="i">
	                    			<a href = "list?packageID=${packageID}&beginTime=${beginTime}&endTime=${endTime}&page=${i}">${i}</a>
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
                    		<a href = "list?keyword=${keyword}&page=${page+1}">下一页</a>
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
		var packageID = $("#packageID").val() ; 
		var beginTime = $("#datepickerBegin").val() ; 
		var endTime = $("#datepickerEnd").val() ; 
		
		url = "list?packageID="+packageID + "&beginTime=" + beginTime + "&endTime=" + endTime ; 
		location.href = url ; 

	}) ; 
	
	
	
	
}) ; 
</script>
</html>