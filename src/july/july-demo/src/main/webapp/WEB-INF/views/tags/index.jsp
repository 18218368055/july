<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/tlds/july.tld" prefix="july"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	tags index!!!
	<P>
	
	<c:if test="${july:isEmpty(null)}">
		july:isEmpty测试：标签验证！！
	</c:if>
	<p>
	
	july:toJson测试：${july:toJson(user)}
	<p>
	
	july:collSize集合测试：${july:collSize(null)} - ${july:collSize(userList)}
	<p>
	
	july:arrLen集合测试：${july:arrLen(null)}
	
	
	
	<script type="text/javascript" src="/static/js/tags/index.js"></script>
</body>
</html>