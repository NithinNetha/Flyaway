<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Flights for you</title>
</head>
<style>
body {
        background-image: url("<%=request.getContextPath()%>/IMAGES/homeBG.jpg");
        background-size: cover;
    }
    .logo{
    margin-left:20px;
    margin-top:20px
    position:absolute;
    width:28%;
    background-color:white; 
    border: 2px solid #052a49;
    border-radius: 7px;
    }
    .tab{
    margin-left: auto;
    margin-right: auto;
    margin-top:30px;
    background-color:#052a49;
    color:white;
        }
    table td,th{
    text-align:center;
    font-size:26px;
   	padding:10px;
    
    }
    .bookBtn{
    background-color: white;
        color: #052a49;
        padding: 4px 15px;
        border: 2px solid #052a49;
        border-radius: 7px;
        font-size: 20px;
        cursor: pointer;
    }
</style>
<body>
	<%	HttpSession mySession = request.getSession(false);
		if (mySession == null) {
			out.print("<center><h3>Your session has expired.. Navigating you to home page.....</h3></center>");
			response.setHeader("refresh", "5;url='/Flyway'");
	}%>
	<div class="logo">
		<h1
			style="color: #052a49; font-size: 50px; font-family: cursive; display: inline;">&nbspFlyaway</h1>
		<img src="IMAGES/logoflyway.png" alt="logoflyway">
	</div>
	<div style="position: absolute; right: 0px; top: 40px; display: inline;"	class="admin">
		<form action="Home.jsp">
			<input class="bookBtn" type="submit" value="Home Page">
		</form>
	</div>
	<table border="1" cellpadding="5" cellspacing="2" class="tab">
		<thead>
			<tr>
				<th>Source</th>
				<th>Destination</th>
				<th>Airline</th>
				<th>Price</th>
				<th>Actions</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="fs" items="${flightSearch}">
				<tr>
					<td><c:out value="${fs.source}" /></td>
					<td><c:out value="${fs.destination}" /></td>
					<td><c:out value="${fs.airline}" /></td>
					<td><c:if test="${fs.ecPrice ne '0.0'}"><c:out value="${fs.ecPrice}" /></c:if>
					<c:if test="${fs.fcPrice != '0.0'}"><c:out value="${fs.fcPrice}" /></c:if>
					<c:if test="${fs.bcPrice != 0.0}"><c:out value="${fs.bcPrice}" /></c:if></td>
					
					<td><form action="BookingServlet" method="get">
							<input type="hidden" name="flightId_book" value="${fs.flightId}">
							<input type='submit' value='Book' class="bookBtn"/>
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