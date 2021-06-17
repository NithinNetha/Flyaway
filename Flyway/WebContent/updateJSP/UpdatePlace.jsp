<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.flyaway.model.*" %>
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
	<h2 style="text-align:center; color: orange">Update Place</h2>
<form action="<%=request.getContextPath()%>/PUServlet" method="get" >
		<table border="1" cellpadding = "5" cellspacing = "2" style="margin-left: auto; margin-right: auto;">
		<thead>
			<tr>
				<th>Place Id</th>
				<th>Place Name</th>
				<th>Place Type</th>
				<th>Action</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="sp" items="${singlePlace}">
				<tr>
					<td><c:out value="${sp.placeId}"/></td>
					<td><input type="text" name="placeName_update" placeholder="${sp.placeName}"></td>
					<td><select name="placeType_update">
					<option value="Choose Type">Choose Type</option>
						<option value="Beach">Beach</option>
						<option value="HillStation">Hill Station</option>
						<option value="Piligrim">Piligrim</option>
						<option value="Educational">Educational</option>
						<option value="Town&City">Town and Cities</option>
						<option value="Miscellaneous">Miscellaneous</option>
						</select></td>
						<td><input type="hidden" name="placeId_update" value="${sp.placeId}"><input type='submit' value='Update'/></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	</form>
</body>
</html>