<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@include file="header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="BJStartingSheet.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Play BlackJack</title>
</head>
<body>

	<c:if test="${username==null || username==''}">
		<script type="text/javascript">
		window.location = "index.jsp"
	</script>
	</c:if>


	<h1>Welcome to Online BlackJack</h1>
	<form method="post" action="StartingBJServlet">
		<input type="submit" name="play" value="Let's Play" /> <input
			type="submit" name="play" value="Maybe Later" />
	</form>
</body>
</html>