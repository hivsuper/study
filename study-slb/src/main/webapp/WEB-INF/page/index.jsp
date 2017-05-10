<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>主页</title>
</head>
<body>
这是主页 去<a href="<c:url value='/login'/>">登录</a><br/>
remoteAddr: ${remoteAddr}<br/>
localAddr: ${localAddr}<br/>
localName: ${localName}<br/>
localPort: ${localPort}<br/>
<c:if test="${not empty user}">
Account: ${user.account}<br/>
Password: ${user.password}<br/>
</c:if>
</body>
</html>