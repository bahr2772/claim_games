<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:useBean id="HangModelBean" class="hangman.HangmanModel" scope="session" />
<jsp:useBean id="HangControlBean" class="hangman.HangmanController" scope="session" />


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="css/HangmanStyle.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Hangman Game</title>
</head>
<body>
	<%@include file="header.jsp"%>


		<div class=gamesContainer>
		<table id="gamesTable">
<tr>
<td><a href="c4start.jsp"> <img src="./images/siteImages/connect-4.jpg" width=150px> </a></td>
<td><a href="hangman.jsp"> <img src="./images/siteImages/hangmanicon.png" width="150px"> </a></td>
<td><a href="BJStartingPoint.jsp"> <img src="./images/siteImages/blackjack-logo-300x300.png" width="150px"> </a></td>
<td><a href="WelcomeToBattleship.jsp"> <img src="./images/siteImages/battleship_icon.png" width="150px"> </a></td>
</tr>
<tr>
<td> Connect 4</td><td> Hangman </td><td> BlackJack </td><td> Battleship </td></tr>
</table>
	</div>


	<!-- jQuery -->
	<script src="js/jquery.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="js/bootstrap.min.js"></script>
</body>
</html>