<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>Get Lost Password</title>

<!-- Bootstrap Core CSS -->
<link href="css/bootstrap.min.css" rel="stylesheet">

<!-- Custom CSS -->
<link href="css/style.css" rel="stylesheet">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>


<body>

	<%@include file="header.jsp"%>



	<!-- Put your page content here! -->

<div class=gamesContainer>
	<form action="GetPassServlet" method="post">
		<div class="inputgroup1">
			<div id=submitUser>
				<div>
					<div>
						<div id="inputText">
							<h5>Please Enter Your </h5><h4>Username or Email:</h4><br> Username: <br><br>
							<input type="text" name="username" id="usernameIn"
								aria-describedby="sizing-addon3"><br> Email: <br>
							<input type="text" name="email" id="passIn"
								aria-describedby="sizing-addon3"> 
						</div>
						<button type="submit" class="logBtn" id="submit">Get Password</button>
						<br><br>Not Registered?  <a href="register.jsp" id="reg"> Register Here</a><br><br><br>
						
						<div id="pass">	<c:out value="${sessionScope.getPass}" />
						</div>
					</div>

				</div>
			</div>
		</div>
	</form>
<%  session.removeAttribute("getPass"); %>
</div>

	<!-- jQuery -->
	<script src="js/jquery.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="js/bootstrap.min.js"></script>
</body>

</html>
