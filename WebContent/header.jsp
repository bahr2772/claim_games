<html>

<head>
<link rel="stylesheet" href="css/style.css">
</head>

<body>
	<div id="headerFrame">
		<div>
			<a href="index.jsp"><img
				src="./images/siteImages/claimgamesLogo.png" id="HeaderLogo"></a>
		</div>
		<div id="links">
			<a href="index.jsp">Home</a> <a href="members.jsp">Members</a> <a
				href="games.jsp">Games</a> <a href="profile.jsp">Profile</a>

		</div>

		<%
			if (session.getAttribute("username") == null || session.getAttribute("username") == "") {
		%>

		<div id="login">
			<form action="LoginServlet" method="post">
				<div class="inputgroup">
					<div id=submitUser>
						<button type="submit" class="btn btn-login" id="submit">Login</button>
					</div>
					<div class="input-group input-group-sm">
						<input type="text" class="form-control" name="username"
							placeholder="Username:" aria-describedby="sizing-addon3">
					</div>

					<div class="input-group input-group-sm">
						<input type="password" class="form-control" name="password"
							placeholder="Password:" aria-describedby="sizing-addon3"
							id="loginbtn"> <br> <a href="register.jsp">
							Register Here</a>
					</div>

				</div>

			</form>
			<%
				} else {
			%>

			<div class="inputgroup">
				<div id="loggedIn">
					Welcome :
					<%=session.getAttribute("username")%><br> <a
						href="signout.jsp" id=signout> Sign Out</a>
					<%
						}
					%>
				</div>
			</div>

		</div>

	</div>


</body>

