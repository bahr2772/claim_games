<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<jsp:useBean id="battleshipBean" type="bsmodel.BsGame" scope="session"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Game Over</title>
<link rel="stylesheet" href="BattleshipStyle.css"/>
</head>
<body id="bsGameOverBody">
<!-- I copied the tables from DisplayBattleship.jsp
If you make changes to those tables, you might need to change these as well -->
<c:choose>

	<c:when test="${battleshipBean.getCPU().getWinStatus() }">
	<div id="bsEndStatement">
		<h1>The computer beat you</h1>
	</div>
		<TABLE id="bsYourBoardTable">
<c:forEach begin="0" end="10" var="row">
	<tr>
	<c:forEach begin="0" end="10" var="column">
		<td id="bsYourBoardColumn">
			 <c:choose>
		        <c:when test="${battleshipBean.getUser().getJSPInfoSpace(row, column) == 20}"><img src="BsImages/water.jpg" alt="water" id="bsYourBoard"></c:when>
       			<c:when test="${battleshipBean.getUser().getJSPInfoSpace(row, column) == 51}"><img src="BsImages/hit.png" alt="!" id="bsYourBoard"></c:when>
		        <c:when test="${battleshipBean.getUser().getJSPInfoSpace(row, column) == 52}"><img src="BsImages/miss.jpg" alt="O" id="bsYourBoard"></c:when>
       		</c:choose>
		</td>
	</c:forEach>
	</tr>
</c:forEach>
</TABLE>
	</c:when>
	<c:when test="${battleshipBean.getUser().getWinStatus() }">
	<div id="bsEndStatement">
		<h1>You win!</h1>
	</div>
		<TABLE id="bsNotYourTurn">
		<c:forEach begin="0" end="10" var="row">
			<tr id="bsNotYourTurnRow">
			<c:forEach begin="0" end="10" var="column">
				<td>
					<c:choose>
						<c:when test="${battleshipBean.getCPU().getJSPInfoSpace(row, column) == 50 }">
							<img src="BsImages/blue.png" alt="h" id="bsImageNotYourTurn">
						</c:when>
						<c:when test="${battleshipBean.getCPU().getJSPInfoSpace(row, column) == 51 }">
							<img src="BsImages/hit.png" alt="h" id="bsImageNotYourTurn">
						</c:when>
						<c:when test="${battleshipBean.getCPU().getJSPInfoSpace(row, column) == 52 }">
							<img src="BsImages/miss.jpg" alt="m" id="bsImageNotYourTurn">
						</c:when>
					</c:choose>
				</td>
			</c:forEach>
			</tr>
		</c:forEach>
		</TABLE>
	</c:when>
</c:choose>
	
	<div id="playBsAgain">
		 <a href="WelcomeToBattleship.jsp">Play Again</a>
	</div>	
	<div id="quitBs">
		 <a href="index.jsp">Quit</a>
	</div>	
</body>
</html>