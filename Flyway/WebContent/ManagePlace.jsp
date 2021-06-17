<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Manage Places</title>
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
table{
font-weight:bold;
color:white;
background-color:#052a49;
}
input [type=submit]{
font-weight:bold;
}
table thead {
  position: -webkit-sticky;
  position: sticky;
  top: 0;
}
input[type=text] 
    {
        font-size: 14px;
        border: 1px solid white;
        border-radius: 4px;
        width:80%
    }
    select{
        border: 1px solid white;
        border-radius: 4px;
        width:100%
    }
    .btn{
    background-color: white;
        color: #052a49;
        border: 2px solid #052a49;
        border-radius: 7px;
        cursor: pointer;
    }
</style>
<body>
	<%
		HttpSession mySession = request.getSession(false);
		if (mySession == null) {
			out.print("<center><h3>Your session has expired.. Navigating you to home page.....</h3></center>");
			response.setHeader("refresh", "5;url='/Flyway'");
		}
	%>
	<%@include file="header.html"%>
	<form style="position: absolute; right: 0px; top:150px;" action="AdminDashboard.jsp">
			<input class="button" type="submit" value="Admin Dashboard">
		</form>
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

	<%
		if (mySession.getAttribute("action") != null) {
	%>
	<div
		style="color: #052a49; text-align: center; background-color: #afd3f1;">
		<%=mySession.getAttribute("action")%>
	</div>
	<%
		}
	%>
	<%
		mySession.setAttribute("action", null);
	%>

	<h2 style="text-align: center; color: orange">Add New Place</h2>
	<form action="ADPlaceServlet" method="post">
		<table border="1" cellpadding="5" cellspacing="2"
			style="text-align: center; margin-left: auto; margin-right: auto;">
			<tr>
				<td>Place Name</td>
				<td><input type="text" name="placeName"
					placeholder="Place Name"></td>
			</tr>
			<tr>
				<td>Place Type</td>
				<td><select name="placeType">
						<option value="" disabled selected>Choose Type</option>
						<option value="Beach">Beach</option>
						<option value="HillStation">Hill Station</option>
						<option value="Piligrim">Piligrim</option>
						<option value="Educational">Educational</option>
						<option value="Town&City">Town and Cities</option>
						<option value="Miscellaneous">Miscellaneous</option>
				</select></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" name="add"
					value="Add Place" class="btn"></td>
			</tr>
		</table>
	</form>
	<br>
	<hr>
	<h2 style="color: orange; text-align: center">List Of Places</h2>

	<table border="1" cellpadding="5" cellspacing="2"
		style="margin-left: auto; margin-right: auto;">
		<thead>
			<tr>
				<th>Place Id</th>
				<th>Place Name</th>
				<th>Place Type</th>
				<th>Actions</th>
				<th>Actions</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="lp" items="${listPlace}">
				<tr>
					<td><c:out value="${lp.placeId}" /></td>
					<td><c:out value="${lp.placeName}" /></td>
					<td><c:out value="${lp.placeType}" /></td>
					<td><form action="ADPlaceServlet" method="get">
							<input type="hidden" name="placeId_delete" value="${lp.placeId}">
							<input type='submit' value='Delete' class="btn" onclick="return confirm('Are you sure you want to delete? **All related flights will be deleted**')"/>
						</form></td>
					<td><form action="MPlaceServlet" method="post">
							<input type="hidden" name="placeId_update" value="${lp.placeId}">
							<input type='submit' value='Update' class="btn"/>
						</form></td>
				</tr>
			</c:forEach>

		</tbody>
	</table>
	<br>
	<br>
	<br>
	<br>
	<div
		style="position: fixed; bottom: 0; right: 0; left: 0; width: 100%;">
		<%@include file='/footer.html'%>
	</div>
</body>
</html>