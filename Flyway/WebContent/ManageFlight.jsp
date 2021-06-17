<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Manage Flight</title>
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
	<h2 style="text-align: center; color: orange">Add New Flight</h2>
	<form action="ADFlightServlet" method="post">
		<table border="1" cellpadding="5" cellspacing="2"
			style="text-align: center; margin-left: auto; margin-right: auto;">
			<tr>
				<td>Source*</td>
				<td><select name="source">
					<option value="" disabled selected>Choose Source</option>
    				<c:forEach var="lp" items="${lop}">
        			<option value="${lp}"><c:out value="${lp}" /></option>
    				</c:forEach>
					</select></td>
			</tr>
			<tr>
				<td>Destination*</td>
				<td><select name="destination">
					<option value="" disabled selected>Choose Destination</option>
    				<c:forEach var="lp" items="${lop}">
        			<option value="${lp}"><c:out value="${lp}" /></option>
    				</c:forEach>
					</select></td>
			</tr>
			<tr>
				<td>Airline*</td>
				<td><select name="airline">
				<option value="" disabled selected>Select Airline</option>
    				<c:forEach var="la" items="${loa}">
        			<option value="${la}"><c:out value="${la}" /></option>
    				</c:forEach>
					</select></td>
			</tr>
			<tr>
				<td>Economy Class Price</td>
				<td><input type="text" name="ecPrice"
					placeholder="Economy CLass"></td>
			</tr>
			<tr>
				<td>First Class Price</td>
				<td><input type="text" name="fcPrice"
					placeholder="First Class"></td>
			</tr>
			<tr>
				<td>Business Class Price</td>
				<td><input type="text" name="bcPrice"
					placeholder="Business Class"></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" name="add"
					value="Add Flight" class="btn"></td>
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
				<th>Flight Id</th>
				<th>Source</th>
				<th>Destination</th>
				<th>Airline</th>
				<th>Economy Class Price</th>
				<th>First Class Price</th>
				<th>Business Class Price</th>
				<th>Actions</th>
				<th>Actions</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="lf" items="${listFlight}">
				<tr>
					<td><c:out value="${lf.flightId}"/></td>
					<td><c:out value="${lf.source}" /></td>
					<td><c:out value="${lf.destination}" /></td>
					<td><c:out value="${lf.airline}" /></td>
					<td><c:if test="${lf.ecPrice ne 0.1}"><c:out value="${lf.ecPrice}" /></c:if></td>
					<td><c:if test="${lf.fcPrice ne 0.1}"><c:out value="${lf.fcPrice}" /></c:if></td>
					<td><c:if test="${lf.bcPrice ne 0.1}"><c:out value="${lf.bcPrice}" /></c:if></td>
					<td><form action="ADFlightServlet" method="get">
							<input type="hidden" name="flightId_delete" value="${lf.flightId}">
							<input type='submit' value='Delete' onclick="return confirm('Are you sure you want to delete?')" class="btn"/>
						</form></td>
					<td><form action="MFlightServlet" method="post">
							<input type="hidden" name="flightId_update" value="${lf.flightId}">
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