<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Manage Airlines</title>
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

	<h2 style="text-align: center; color: orange">Add New Airlines</h2>
	<form action="ADAirlineServlet" method="post">
		<table border="1" cellpadding="5" cellspacing="2"
			style="text-align: center; margin-left: auto; margin-right: auto;">
			<tr>
				<td>Airlines Name</td>
				<td><input type="text" name="airlineName"
					placeholder="Airline Name"></td>
			</tr>
			<tr>
				<td>Contact Name</td>
				<td><input type="text" name="contactName"
					placeholder="Contact Name"></td>
			</tr>
			<tr>
				<td>Contact Number</td>
				<td><input type="text" name="contactNumber"
					placeholder="Contact Number"></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" name="add"
					value="Add Airlines" class="btn"></td>
			</tr>
		</table>
	</form>
	<br>
	<hr>
	<h2 style="color: orange; text-align: center">List Of Airlines</h2>

	<table border="1" cellpadding="5" cellspacing="2"
		style="margin-left: auto; margin-right: auto;">
		<thead>
			<tr>
				<th>Airlines Id</th>
				<th>Airlines Name</th>
				<th>Contact Name</th>
				<th>Contact Number</th>
				<th>Actions</th>
				<th>Actions</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="la" items="${listAirline}">
				<tr>
					<td><c:out value="${la.airlineId}" /></td>
					<td><c:out value="${la.airlineName}" /></td>
					<td><c:out value="${la.contactName}" /></td>
					<td><c:out value="${la.contactNumber}" /></td>
					<td><form action="ADAirlineServlet" method="get">
							<input type="hidden" name="airlineId_delete" value="${la.airlineId}">
							<input type='submit' value='Delete' class="btn" onclick="return confirm('Are you sure you want to delete?<br>All related flights will be deleted')"/>
						</form></td>
					<td><form action="MAirlineServlet" method="post">
							<input type="hidden" name="airlineId_update" value="${la.airlineId}">
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