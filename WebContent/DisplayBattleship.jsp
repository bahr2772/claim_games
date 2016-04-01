<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<jsp:useBean id="battleshipBean" type="bsmodel.BsGame" scope="session"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Play Battleship</title>
<link rel="stylesheet" href="css/BattleshipStyle.css"/>

<script type="text/javascript">
function initialize() {
        setTimeout('submitForm()', 750);
    }
    function submitForm() {
        document.forms["bsSubmit"].submit();
    }
</script>
</head>
<body id="bsFiringBody" <c:if test="${battleshipBean.getCPU().isTurnToShoot()}"> onload="initialize()"</c:if>>
<%@include file="header.jsp"%>

<!-- Will show a defense board of 100 image buttons and an offense board of 100 images (similar to setShips.jsp) -->
	<div id=bsUserData>
	Your number of shots fired: <c:out value="${battleshipBean.getUser().getNumberOfShots() }"/> <br>
	Your number of hits: <c:out value="${17-battleshipBean.getUser().getShipSpacesLeftToGet() }"/> /17
	</div>
	
	<div id="bsComputerData">
	Computer's number of shots fired: <c:out value="${battleshipBean.getCPU().getNumberOfShots() }"/> <br>
	Computer's number of hits: <c:out value="${17-battleshipBean.getCPU().getShipSpacesLeftToGet() }"/> /17
	<div id="bsKeyGuideHit">
		<img src="images/BsImages/hit.png" alt="h" id="bsKeyImage"> = Hit<br>
		<img src="images/BsImages/explosion.jpg" alt="x" id="bsKeyImage"> = New Hit<br>
	</div>
	<div id="bsKeyGuideMiss">
		<img src="images/BsImages/miss.jpg" alt="m" id="bsKeyImage"> = Miss<br>
		<img src="images/BsImages/splash.jpg" alt="z" id="bsKeyImage"> = New Miss<br>
	</div>
	</div>	
<c:choose>
	<c:when test="${battleshipBean.getCPU().isTurnToShoot() }">
		<div id="bsPrepare"><h1>Prepare of the computer's shot</h1></div>
	</c:when>
	<c:when test="${battleshipBean.getUser().isTurnToShoot() }">
		<div id="bsPleaseClick"><h1>Click on a space to fire your missile</h1></div>
	</c:when>
</c:choose>
								<!-- OFFENSE BOARD IS YOUR TURN -->
<c:choose>
	<c:when test="${battleshipBean.getUser().isTurnToShoot() }">
	
		<TABLE id="bsYourTurn">
		<c:forEach begin="0" end="10" var="row">
			<tr id="bsYourTurnRow">
			<c:forEach begin="0" end="10" var="column">
				<td>
					<c:choose>
						<c:when test="${battleshipBean.getCPU().getJSPInfoSpace(row, column) == 50 }">
							<form action="FireMissileServlet" method="post" id="test">
								<input type="hidden" name="clickedRow" value="${row}">
								<input type="hidden" name="clickedColumn" value="${column}">
								<input type="image" src="images/BsImages/blue.png" alt="?" id="bsButtonYourTurn">
							</form>	
						</c:when>
						<c:when test="${battleshipBean.getCPU().getJSPInfoSpace(row, column) == 51 }">
							<img src="images/BsImages/hit.png" alt="h" id="bsImageYourTurn">
						</c:when>
						<c:when test="${battleshipBean.getCPU().getJSPInfoSpace(row, column) == 52 }">
							<img src="images/BsImages/miss.jpg" alt="m" id="bsImageYourTurn">
						</c:when>
						<c:when test="${battleshipBean.getCPU().getJSPInfoSpace(row, column) == 53 }">
							<img src="images/BsImages/explosion.jpg" alt="x" id="bsImageYourTurn">
						</c:when>
						<c:when test="${battleshipBean.getCPU().getJSPInfoSpace(row, column) == 54 }">
							<img src="images/BsImages/splash.jpg" alt="z" id="bsImageYourTurn">
						</c:when>
					</c:choose>
				</td>
			</c:forEach>
			</tr>
		</c:forEach>
		</TABLE>
	</c:when>
</c:choose>
											<!-- OFFENSE BOARD NOT YOUR TURN -->
<c:choose>
	<c:when test="${battleshipBean.getCPU().isTurnToShoot() }">
		<TABLE id="bsNotYourTurn">
		<c:forEach begin="0" end="10" var="row">
			<tr id="bsNotYourTurnRow">
			<c:forEach begin="0" end="10" var="column">
				<td>
					<c:choose>
						<c:when test="${battleshipBean.getCPU().getJSPInfoSpace(row, column) == 50 }">
							<img src="images/BsImages/blue.png" alt="w" id="bsImageNotYourTurnWater">
						</c:when>
						<c:when test="${battleshipBean.getCPU().getJSPInfoSpace(row, column) == 51 }">
							<img src="images/BsImages/hit.png" alt="h" id="bsImageNotYourTurn">
						</c:when>
						<c:when test="${battleshipBean.getCPU().getJSPInfoSpace(row, column) == 52 }">
							<img src="images/BsImages/miss.jpg" alt="m" id="bsImageNotYourTurn">
						</c:when>
						<c:when test="${battleshipBean.getCPU().getJSPInfoSpace(row, column) == 53 }">
							<img src="images/BsImages/explosion.jpg" alt="x" id="bsImageNotYourTurn">
						</c:when>
						<c:when test="${battleshipBean.getCPU().getJSPInfoSpace(row, column) == 54 }">
							<img src="images/BsImages/splash.jpg" alt="z" id="bsImageNotYourTurn">
						</c:when>
					</c:choose>
				</td>
			</c:forEach>
			</tr>
		</c:forEach>
		</TABLE>
	</c:when>
</c:choose>
							<!-- Display the User's defense board -->
<c:choose>
<c:when test="${battleshipBean.getCPU().isTurnToShoot() }">
<TABLE id="bsYourBoardTableComputerShoots">
<c:forEach begin="0" end="10" var="row">
	<tr>
	<c:forEach begin="0" end="10" var="column">
		<td id="bsYourBoardColumn">
			 <c:choose>
		        <c:when test="${battleshipBean.getUser().getJSPInfoSpace(row, column) == 20}"><img src="images/BsImages/water.jpg" alt="water" id="bsYourBoard"></c:when>
		        <c:when test="${battleshipBean.getUser().getJSPInfoSpace(row, column) == 21}"><img src="images/BsImages/shipEndTop.jpg" alt="N" id="bsYourBoard"></c:when>
		        <c:when test="${battleshipBean.getUser().getJSPInfoSpace(row, column) == 22}"><img src="images/BsImages/shipEndRight.jpg" alt="E" id="bsYourBoard"></c:when>
		        <c:when test="${battleshipBean.getUser().getJSPInfoSpace(row, column) == 23}"><img src="images/BsImages/shipEndBottom.jpg" alt="S" id="bsYourBoard"></c:when>
		        <c:when test="${battleshipBean.getUser().getJSPInfoSpace(row, column) == 24}"><img src="images/BsImages/shipEndLeft.jpg" alt="W" id="bsYourBoard"></c:when>
		        <c:when test="${battleshipBean.getUser().getJSPInfoSpace(row, column) == 25}"><img src="images/BsImages/shipCenterVert.jpg" alt="V" id="bsYourBoard"></c:when>
		        <c:when test="${battleshipBean.getUser().getJSPInfoSpace(row, column) == 26}"><img src="images/BsImages/shipCenterHoriz.jpg" alt="H" id="bsYourBoard"></c:when>
       			<c:when test="${battleshipBean.getUser().getJSPInfoSpace(row, column) == 51}"><img src="images/BsImages/hit.png" alt="!" id="bsYourBoard"></c:when>
		        <c:when test="${battleshipBean.getUser().getJSPInfoSpace(row, column) == 52}"><img src="images/BsImages/miss.jpg" alt="O" id="bsYourBoard"></c:when>
       			<c:when test="${battleshipBean.getUser().getJSPInfoSpace(row, column) == 53}"><img src="images/BsImages/explosion.jpg" alt="x" id="bsYourBoard"></c:when>
       			<c:when test="${battleshipBean.getUser().getJSPInfoSpace(row, column) == 54}"><img src="images/BsImages/splash.jpg" alt="z" id="bsYourBoard"></c:when>
       		</c:choose>
		</td>
	</c:forEach>
	</tr>
</c:forEach>
</TABLE>
</c:when>
<c:when test="${battleshipBean.getUser().isTurnToShoot() }">
<TABLE id="bsYourBoardTable">
<c:forEach begin="0" end="10" var="row">
	<tr>
	<c:forEach begin="0" end="10" var="column">
		<td id="bsYourBoardColumn">
			 <c:choose>
		        <c:when test="${battleshipBean.getUser().getJSPInfoSpace(row, column) == 20}"><img src="images/BsImages/water.jpg" alt="water" id="bsYourBoard"></c:when>
		        <c:when test="${battleshipBean.getUser().getJSPInfoSpace(row, column) == 21}"><img src="images/BsImages/shipEndTop.jpg" alt="N" id="bsYourBoard"></c:when>
		        <c:when test="${battleshipBean.getUser().getJSPInfoSpace(row, column) == 22}"><img src="images/BsImages/shipEndRight.jpg" alt="E" id="bsYourBoard"></c:when>
		        <c:when test="${battleshipBean.getUser().getJSPInfoSpace(row, column) == 23}"><img src="images/BsImages/shipEndBottom.jpg" alt="S" id="bsYourBoard"></c:when>
		        <c:when test="${battleshipBean.getUser().getJSPInfoSpace(row, column) == 24}"><img src="images/BsImages/shipEndLeft.jpg" alt="W" id="bsYourBoard"></c:when>
		        <c:when test="${battleshipBean.getUser().getJSPInfoSpace(row, column) == 25}"><img src="images/BsImages/shipCenterVert.jpg" alt="V" id="bsYourBoard"></c:when>
		        <c:when test="${battleshipBean.getUser().getJSPInfoSpace(row, column) == 26}"><img src="images/BsImages/shipCenterHoriz.jpg" alt="H" id="bsYourBoard"></c:when>
       			<c:when test="${battleshipBean.getUser().getJSPInfoSpace(row, column) == 51}"><img src="images/BsImages/hit.png" alt="!" id="bsYourBoard"></c:when>
		        <c:when test="${battleshipBean.getUser().getJSPInfoSpace(row, column) == 52}"><img src="images/BsImages/miss.jpg" alt="O" id="bsYourBoard"></c:when>
       			<c:when test="${battleshipBean.getUser().getJSPInfoSpace(row, column) == 53}"><img src="images/BsImages/explosion.jpg" alt="x" id="bsYourBoard"></c:when>
       			<c:when test="${battleshipBean.getUser().getJSPInfoSpace(row, column) == 54}"><img src="images/BsImages/splash.jpg" alt="z" id="bsYourBoard"></c:when>
       		</c:choose>
		</td>
	</c:forEach>
	</tr>
</c:forEach>
</TABLE>
</c:when>
</c:choose>
<c:choose>
	<c:when test="${battleshipBean.getCPU().isTurnToShoot() }">
	<div id="bsCpuSubmit">
		 <form action="FireMissileServlet" name="bsSubmit" method="post">
		 	<input type="hidden" name="clickedRow" value="0">
			<input type="hidden" name="clickedColumn" value="0">
		</form>
	</div>	
	</c:when>
</c:choose>
	
</body>
</html>