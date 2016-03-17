<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>Register</title>

<!-- Bootstrap Core CSS -->
<link href="css/bootstrap.min.css" rel="stylesheet">

<!-- Custom CSS -->
<link href="css/style.css" rel="stylesheet">
<link rel="stylesheet" href="css/HangmanStyle.css">

</head>
<body>
<body>

	<%@include file="header.jsp"%>


	<div class="regForm">
<c:if test="${sessionScope.username != null}">
<% response.sendRedirect("index.jsp"); %>
</c:if>

<c:if test="${sessionScope.username == null || sessionScope.username ==''}">
		<!-- Put your page content here! -->

		<form action="RegisterServlet" method="post" id="regText">
			Username:<br> <input type="text" name="username" id="regNameIn"
				required><br> Email:<br> <input type="email"
				name="email" id="regEmailIn" required><br> Password:<br>
			<input type="password" name="password1" id="regPass1In" required>
			<br> Re-enter Password:<br> <input type="password"
				name="password2" id="regPass2In" required>

			<!--         user in Db -->
			<c:if test="${sessionScope.user == 'userInDb'}">
				<div id="fail">Username Unavailable</div>
			</c:if>
			<!-- 	 user to short -->
			<c:if test="${sessionScope.user == 'userTooSmall'}">
				<div id="fail">Username less then 4 Characters</div>
			</c:if>
			<!-- 	 	password to short -->
			<c:if test="${sessionScope.user == 'passTooSmall'}">
				<div id="fail">Password less then 2 Characters or contains
					invalid characters</div>
			</c:if>
			<!-- 	 password didn't match -->
			<c:if test="${sessionScope.user == 'passfail'}">
				<div id="fail">Password mismatched or not entered</div>
			</c:if>
			<!-- 	 nothing entered -->
			<c:if test="${sessionScope.user == 'nothingEntered'}">
				<div id="fail">
					You didn't enter anything. <br>
				</div>
			</c:if>
			<!-- 	 error -->
			<c:if test="${sessionScope.user == 'error'}">
				<div id="fail">
					Something went wrong, contact Admin. <br>
				</div>
			</c:if>
			<!-- 	 user registered -->
			<c:if test="${sessionScope.user == 'registered'}">
				<div id="fail">
					User Registered<a href="login.jsp"> Login In </a>
				</div>
			</c:if>
			<!-- 			email invalid -->
			<c:if test="${sessionScope.user == 'emailFail'}">
				<div id="fail">Invalid Email</div>
			</c:if>
			<!-- 			user name fail -->
			<c:if test="${sessionScope.user == 'userFail'}">
				<div id="fail">User may not contain spaces, commas, quotes, or
					slashes.</div>
			</c:if>
			<!-- 			fill blank space -->
			<c:if test="${sessionScope.user == null || sessionScope.user == ''}">
				<div id="fail">
					<br>
				</div>
			</c:if>
			<br> <input type="submit" name="submit" value="Sign-up"
				id="regBtn">
		</form>
		</c:if>
	</div>
	
	<!-- jQuery -->
	<script src="js/jquery.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="js/bootstrap.min.js"></script>

</body>
</body>
</html>