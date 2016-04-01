<jsp:useBean id="BJBean" class="BJmodel.BlackJackGame" scope="session" />
<%@include file="header.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="StyleSheet.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>BlackJack</title>
</head>

<body>
	<c:if test="${username==null || username==''}">
		<script type="text/javascript">
		window.location = "index.jsp"
	</script>
	</c:if>


	<form method="post" action="BlackJackServlet">

		<%if(BJBean.isBet() == 0) {%>
		<div id="enterAmount">
			<form method="post" action="StartingBJServlet">
				Enter how many chips your starting with<br> <input type="text"
					name="chipCount" placeholder="Starting chip amount" />
			</form>
		</div>

		<%}
	
		else if(BJBean.isBet() == 1){ %>
		<div id="betPlace">
			Enter bet here:<br> <input type="text" name="betArea"
				placeholder="place bet here." />
		</div>
		<%}
		else if(BJBean.isBet() == 2 && !BJBean.getP1().bust() ) { %>
		<table id="dealerCards">

			<%for(int i = 0; i < BJBean.getDealer().getPlayerHand().size(); i++) { %>
			<td>
				<%Thread.sleep(750); %> <% if(i == 0){%> <img
				src="BJimages/card/facedown.bmp"> <%}
					else{%> <img
				src=<%=BJBean.getDealer().getPlayerHand().get(i).getFileName()%>>
				<%} %>
			</td>
			<%} %>

		</table>

		<table id="playerCards">
			<%for(int i = 0; i < BJBean.getP1().getPlayerHand().size(); i++) { %>
			<td><img
				src=<%=BJBean.getP1().getPlayerHand().get(i).getFileName()%>>
			</td>
			<%} %>
		</table>

		<div id="userInfo">
			<input type="submit" name="hitOrStay" value="Hit" /> <input
				type="submit" name="hitOrStay" value="Stay" /><br> Your hand
			count:<br> <input type="text" name="cardCount"
				value="${BJBean.getP1().getCurrentHandCount()}" readonly id="handCount" /><br>
			Your Chips:<br> <input type="text" name="chipArea"
				value="${BJBean.getP1().getChipCount()}" readonly />
		</div>
		<%}
		
			else {
			%>
		<div id="anthrRound">
			<%if(!BJBean.getP1().bust() && (BJBean.getDealer().bust() || BJBean.getP1().getCurrentHandCount() > BJBean.getDealer().getCurrentHandCount())){%>
			<h1>YOU WIN!</h1>
			<%}
				else if((!BJBean.getP1().bust() && !BJBean.getDealer().bust()) && (BJBean.getP1().getCurrentHandCount() == BJBean.getDealer().getCurrentHandCount())) {%>
			<h1>It's a Draw!</h1>
			<%}
				else{%>
			<h1>DEALER WINS!</h1>
			<%} BJBean.setBet(3); session.setAttribute("BJBean", BJBean);%>
			<h1>Do you want to play again?</h1>
			<input type="submit" name="playAgain" value="Yes" /> <input
				type="submit" name="playAgain" value="No" />

		</div>
		<table id="dealerCards">

			<%for(int i = 0; i < BJBean.getDealer().getPlayerHand().size(); i++) { %>
			<td><img
				src=<%=BJBean.getDealer().getPlayerHand().get(i).getFileName()%>
				<%Thread.sleep(375); %>></td>
			<%} %>

		</table>
		<div id="showCardCount">
			Dealer hand count:<br> <input type="text" name="cardCount"
				value="${BJBean.getDealer().getCurrentHandCount()}" readonly id="handCount"/><br>
		</div>

		<table id="playerCards">
			<%for(int i = 0; i < BJBean.getP1().getPlayerHand().size(); i++) { %>
			<td><img
				src=<%=BJBean.getP1().getPlayerHand().get(i).getFileName()%>>

			</td>
			<%} %>

		</table>
		<div id="showCardCount">
			Your hand count:<br> <input type="text" name="cardCount"
				value="${BJBean.getP1().getCurrentHandCount()}" readonly id="handCount"/><br>
		</div>

		<%}%>

	</form>
</body>
</html>