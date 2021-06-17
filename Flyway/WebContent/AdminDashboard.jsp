<%@page import="javax.servlet.http.HttpSession"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Dashboard</title>
</head>
<style>
.button {
	background-color: white;
	color: #052a49;
	margin: 0px 9px;
	padding: 4px 4px;
	border: 2px solid #052a49;
	border-radius: 7px;
	font-size: 20px;
	cursor: pointer;
}

.button:hover {
	background-color: #afd3f1;
}

.icon {
	width: 400px;
	height: 200px;
}

ul li {
	display: inline;
}
</style>
<body>
	<%
		HttpSession mySession = request.getSession(false);
		if (mySession == null) {
			out.print("<center><h3>Your session has expired.. Navigating you to home page.....</h3></center>");
			response.setHeader("refresh", "5;url='/Flyway'");
		} else {
			Date createTime = new Date(mySession.getCreationTime());
	%>
	<%@ include file="header.html" %>
	<div class="header">
		<h2 style="text-align: center; font-size: 35px; margin-bottom:0;">Welcome to Dashboard</h2>
		<form style="position: absolute; right: 0px; top:160px;" action="PasswordChange.jsp">
			<input class="button" type="submit" value="Change Password">
		</form>
		<br> <b style="Color: #325068;">You Logged in at <%=createTime%></b>
		<br />
	</div>
	<%
		if (mySession.getAttribute("exception") != null) {
	%>
	<div
		style="color: red; text-align: center; border: 1px outset red; background-color: lightblue;">
		<%=mySession.getAttribute("exception")%>
	</div>
	<%
		}
	%>
	<%
		mySession.setAttribute("exception", null);
	%>
	<%if(mySession.getAttribute("action") != null) {%>
	<div style="color: #052a49; text-align: center; background-color:#afd3f1;">
	<%=mySession.getAttribute("action")%>
	</div>
	<%}%>
	<%mySession.setAttribute("action",null); %>
	<hr>
	<ul style="list-style-type: none; text-align: center;">
		<li><a href="MPlaceServlet"><img src="IMAGES/place.png" alt="" class="icon"></a></li>
		<li><a href="MAirlineServlet"><img src="IMAGES/airline.png" alt="" class="icon"></a></li>
		<li><a href="MFlightServlet"><img src="IMAGES/flight.png" alt="" class="icon"></a></li>
	</ul>
	<%
		}
	%>
	<div style="position:fixed; bottom:0;right:0;left:0; width:100%;">
	<%@ include file="footer.html" %>
	</div>
</body>
</html>