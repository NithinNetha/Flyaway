<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Update Flight</title>
</head>
<body>
	<c:forEach var="sf" items="${singleFlight}">
		<h2 style="text-align: center; color: orange">Update Flight</h2>
	<form action="<%=request.getContextPath()%>/FUServlet" method="get">
		<table border="1" cellpadding="5" cellspacing="2"
			style="text-align: center; margin-left: auto; margin-right: auto;">
			<tr>
				<td>Flight Id</td>
				<td><c:out value="${sf.flightId}"/></td>
			</tr>
			<tr>
				<td>Source</td>
				<td><select name="source_update">
				<option value="" disabled selected>${sf.source}</option>
    				<c:forEach var="lp" items="${lop}">
        			<option value="${lp}"><c:out value="${lp}" /></option>
    				</c:forEach>
					</select></td>
			</tr>
			<tr>
				<td>Destination</td>
				<td><select name="destination_update">
					<option value="" disabled selected>${sf.destination}</option>
    				<c:forEach var="lp" items="${lop}">
        			<option value="${lp}"><c:out value="${lp}" /></option>
    				</c:forEach>
					</select></td>
			</tr>
			<tr>
				<td>Airline</td>
				<td><select name="airline_update">
				<option value="" disabled selected>${sf.airline}</option>
    				<c:forEach var="la" items="${loa}">
        			<option value="${la}"><c:out value="${la}" /></option>
    				</c:forEach>
					</select></td>
			</tr>
			<tr>
				<td>Economy Class Price</td>
				<td><input type="text" name="ecPrice_update"
					placeholder="${sf.ecPrice}"></td>
			</tr>
			<tr>
				<td>First Class Price</td>
				<td><input type="text" name="fcPrice_update"
					placeholder="${sf.fcPrice}"></td>
			</tr>
			<tr>
				<td>Business Class Price</td>
				<td><input type="text" name="bcPrice_update"
					placeholder="${sf.bcPrice}"></td>
			</tr>
			<tr>
				<td colspan="2"><input type="hidden" name="flightId_update" value="${sf.flightId}"><input type="submit" name="add"
					value="Update Flight"></td>
			</tr>
		</table>
	</form>
	</c:forEach>
	<p style="text-align:center">Note: Enter <strong>"0.1"</strong> value to disable/remove any specific class</p>
 </body>
</html>