<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%HttpSession mySession = request.getSession(false);
	if (mySession == null) {
		out.print("<center><h3>Your session has expired.. Navigating you to home page.....</h3></center>");
		response.setHeader("refresh", "5;url='/Flyway'");
	}%>
	<br><br>
	<h2 style="text-align: center; color: orange">Update Airline</h2>
<form action="<%=request.getContextPath()%>/AUServlet" method="get">
	<table border="1" cellpadding="5" cellspacing="2"
		style="margin-left: auto; margin-right: auto;">
		<thead>
			<tr>
				<th>Airlines Id</th>
				<th>Airlines Name</th>
				<th>Contact Name</th>
				<th>Contact Number</th>
				<th>Actions</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="sa" items="${singleAirline}">
				<tr>
					<td><c:out value="${sa.airlineId}" /></td>
					<td><input type="text" name="airlineName_update" placeholder="${sa.airlineName}"></td>
					<td><input type="text" name="contactName_update" placeholder="${sa.contactName}"></td>
					<td><input type="text" name="contactNumber_update" placeholder="${sa.contactNumber}"></td>
					<td><input type="hidden" name="airlineId_update" value="${sa.airlineId}"><input type='submit' value='Update'/></td>
				</tr>
			</c:forEach>

		</tbody>
	</table>
	</form>
</body>
</html>