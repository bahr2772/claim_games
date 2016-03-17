<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="BattleshipStyle.css"/>
<title>Welcome to Battleship</title>
</head>
<body id="bsWelcome">
	
	<div id="bsWelcomeTitle">
	Welcome to Battleship
	</div>
	<div id="bsWelcomeOptions">
		<form action="startBattle" method="post">
			Would you like to fire missiles before the computer? Or after?<br><br>
			<input type="radio" name="turn" value="before" checked="checked">Before<br>
			<input type="radio" name="turn" value="after">After<br><br>
			<div id="bsWelcomeSubmit">
			<input type="submit" value="Set Ships">
			</div>
		</form>
	</div>
</body>
</html>