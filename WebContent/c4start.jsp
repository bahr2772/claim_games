<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="true" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<c:if test="${username==null || username==''}">
	<script type="text/javascript">
		window.location = "index.jsp"
	</script>
</c:if>

<html>
<head>
<link href="css/connect4.css" rel="stylesheet">
<title>Connect 4</title>
</head>

<body id="c4introBody">
<%@include file="header.jsp"%>


<div id="c4introDiv">
<form method="post" action="C4ServletStart">
<TABLE id="c4OptionTable">
<tr><td colspan=3><h1>Connect 4</h1></td></tr>
<tr><td colspan=3><h3>Options:</h3></td></tr>
<TR>
<TD>Choose your color:</TD>  
<TD><input type="radio" name="color" value="R" checked> red</TD>
<TD><input type="radio" name="color" value="Y"> yellow</TD>
</TR>
<TR>
<TD>Choose 1st or 2nd move:</TD>
<TD><input type="radio" name="turn" value="1" checked> 1st</TD>
<TD><input type="radio" name="turn" value="2"> 2nd</TD>
</TR>
<TR>
<TD>Choose difficulty level:</TD>
<TD><input type="radio" name="level" value="1"> easy</TD>
<TD><input type="radio" name="level" value="2" checked> hard</TD>
</TR>
<TR>
<TD>Where do you want to play?</TD>
<TD colspan=2><select name="where" class="c4where">
 <option value="1" selected>at home</option>
 <option value="2">in the park</option>
 <option value="3">at the beach</option>
 </select>
</TD></TR>
<TR><TD colspan=3><input type="submit" value="Play" class="c4ColumnButton"></TD>
</TABLE></form></div></body></html>