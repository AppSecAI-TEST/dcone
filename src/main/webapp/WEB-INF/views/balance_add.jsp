<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
在这里显示充值信息
<form name="form1" action="balance_adding">
${msg}
<table>
<tr><td>ITcode</td><td><input name="itcode"></td></tr>
<tr><td>姓名</td><td><input name="username"></td></tr>
<tr><td>充值金额</td><td><input name="amount"></td></tr>
<tr><td><input type="submit"></td></tr>
</table>
</form>
</body>
</html>