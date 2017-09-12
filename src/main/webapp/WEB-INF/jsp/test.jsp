<%@ page language="java" import="java.util.*" isELIgnored="false" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>test</title>
	<meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
</head>
<body>
<h2>Test page</h2>
<div>
	data from controller: ${test}
</div>
<!-- <form action="/httpserver/test/set.do" method="post" enctype="multipart/form-data"> -->
<form action="/httpserver/test/set.do" method="post">
	name:<input type="text" name="name"/><br/>
        age:<input type="text" name="age"><br/>
        <!-- file:<input type="file" name="file"/><br/> -->
        <input type="submit" value="提交"/>
</form>
<p>name: ${name }</p>
<p>age: ${age }</p>
<form action="/httpserver/test/get.do" method="post">
        <input type="submit" value="提交"/>
</form>
</body>
</html>
