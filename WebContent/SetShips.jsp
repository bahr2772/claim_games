<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>

<jsp:useBean id="gameBean" type="bsmodel.BsGame" scope="session"/>

<html>
<head>
<link rel="stylesheet" href="BattleshipStyle.css"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Set Your Ships</title>
</head>
<body id="bsSetShipsBody">
<div id="bsSetShipsTitle">
<h1>You have placed <c:out value="${gameBean.getUser().getDefBoard().getShipsOnBoard() }"/> of your 5 ships</h1>
</div>
<div id="bsSetShipsInstructions">
<h3> Select the row and column where your ship will begin</h3>

	<!-- Display warnings if ship failed to be placed -->
	<c:choose>
		<c:when test="${gameBean.getUser().getDefBoard().isOffOfBoard()}"><h4 style="color:red;">Your ship won't fit on the board!</h4></c:when>
		<c:when test="${gameBean.getUser().getDefBoard().isOverlap()}"><h4 style="color:red;">Your ship would overlap with another ship!</h4></c:when>
	</c:choose>
</div>
	 <!-- posting to ShipSetServlet -->

<form action="shipSetting" method="post">
<div id="bsSetShipsTable">	

<TABLE id="page" border="0" cellspacing="0" cellpadding="0">
<c:forEach begin="0" end="10" var="row">
	<tr>
	<c:forEach begin="0" end="10" var="column">
		<td id ="bsShipSetCell">
							<!-- Display the Placement grid -->
			 
			 <c:choose>
			 
			 	<c:when test="${column==0 && row==1}">A<input type="radio" name="row" class="bsSSCol" value="A" checked="checked"><br></c:when>
			 	<c:when test="${column==0 && row==2}">B<input type="radio" name="row" class="bsSSCol" value="B"></c:when>
			 	<c:when test="${column==0 && row==3}">C<input type="radio" name="row" class="bsSSCol" value="C"></c:when>
			 	<c:when test="${column==0 && row==4}">D<input type="radio" name="row" class="bsSSCol" value="D"></c:when>
			 	<c:when test="${column==0 && row==5}">E<input type="radio" name="row" class="bsSSCol" value="E"></c:when>
			 	<c:when test="${column==0 && row==6}">F<input type="radio" name="row" class="bsSSCol" value="F"></c:when>
			 	<c:when test="${column==0 && row==7}">G<input type="radio" name="row" class="bsSSCol" value="G"></c:when>
			 	<c:when test="${column==0 && row==8}">H<input type="radio" name="row" class="bsSSCol" value="H"></c:when>
			 	<c:when test="${column==0 && row==9}">I<input type="radio" name="row" class="bsSSCol" value="I"></c:when>
			 	<c:when test="${column==0 && row==10}">J<input type="radio" name="row" class="bsSSCol" value="J"></c:when>
			 
			 	<c:when test="${column==1 && row==0}">01 <input type="radio" name="column" value="1" checked="checked"></c:when>
			 	<c:when test="${column==2 && row==0}">02 <input type="radio" name="column" value="2"></c:when>
			 	<c:when test="${column==3 && row==0}">03 <input type="radio" name="column" value="3"></c:when>
			 	<c:when test="${column==4 && row==0}">04 <input type="radio" name="column" value="4"></c:when>
			 	<c:when test="${column==5 && row==0}">05 <input type="radio" name="column" value="5"></c:when>
			 	<c:when test="${column==6 && row==0}">06 <input type="radio" name="column" value="6"></c:when>
			 	<c:when test="${column==7 && row==0}">07 <input type="radio" name="column" value="7"></c:when>
			 	<c:when test="${column==8 && row==0}">08 <input type="radio" name="column" value="8"></c:when>
			 	<c:when test="${column==9 && row==0}">09 <input type="radio" name="column" value="9"></c:when>
			 	<c:when test="${column==10 && row==0}">10<input type="radio" name="column" value="10"></c:when>
			 	
		        <c:when test="${gameBean.getUser().getJSPInfoSpace(row, column) == 20}"><img src="BsImages/water.jpg" alt="water" width="30" height="30"></c:when>
		        <c:when test="${gameBean.getUser().getJSPInfoSpace(row, column) == 21}"><img src="BsImages/shipEndTop.jpg" alt="N" width="30" height="30"></c:when>
		        <c:when test="${gameBean.getUser().getJSPInfoSpace(row, column) == 22}"><img src="BsImages/shipEndRight.jpg" alt="E" width="30" height="30"></c:when>
		        <c:when test="${gameBean.getUser().getJSPInfoSpace(row, column) == 23}"><img src="BsImages/shipEndBottom.jpg" alt="S" width="30" height="30"></c:when>
		        <c:when test="${gameBean.getUser().getJSPInfoSpace(row, column) == 24}"><img src="BsImages/shipEndLeft.jpg" alt="W" width="30" height="30"></c:when>
		        <c:when test="${gameBean.getUser().getJSPInfoSpace(row, column) == 25}"><img src="BsImages/shipCenterVert.jpg" alt="V" width="30" height="30"></c:when>
		        <c:when test="${gameBean.getUser().getJSPInfoSpace(row, column) == 26}"><img src="BsImages/shipCenterHoriz.jpg" alt="H" width="30" height="30"></c:when>
       		</c:choose>
		</td>
	</c:forEach>
	</tr>
</c:forEach>
</TABLE>
</div>
<!-- Display options for orientation of ship to be placed -->
<div id="bsSetShipsOrientation">
		Select the Orientation<br><br>
		<input type="radio" name="orientation" value="vertical" checked="checked">Vertical<br><br>
		<input type="radio" name="orientation" value="horizontal">Horizontal<br> <br> <br>
		<input type="submit" value="Place Ship"><br>
</div>
	</form>	
	
</body>
</html>