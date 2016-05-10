<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width" />
<meta content="240" name="MobileOptimized">
<meta
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0"
	name="viewport">
<meta name="format-detection" content="telephone=no" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Model And List</title>
</head>
<body>
	<h3>Welcome To SpringMVC! This is OAuth2 UserInfo</h3>
	<h3>获取到的userInfo 如下:</h3>

	avatar:
	<img width=30px; src=${userInfo.avatar } border="1">
	<br> userid: ${userInfo.userid}
	<br> name:${userInfo.name}
	<br> position:${userInfo.position}
	<br> mobile:${userInfo.mobile}
	<br> gender:${userInfo.gender}
	<br> email:${userInfo.email}
	<br> weixinid:${userInfo.weixinid}
	<br> status:${userInfo.status}
	<br>


</body>
</html>