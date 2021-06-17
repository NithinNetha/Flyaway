<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<style>
	.button {
        background-color: white;
        color: #052a49;
        margin-left: 530px;
        padding: 4px 4px;
        border: 2px solid #052a49;
        border-radius: 7px;
        font-size: 20px;
        cursor: pointer;
    }
    .button:hover{
        background-color: #afd3f1;
    }
.flyway{
text-align:center;}
</style>
<body>
<div class="flyway">
<%
out.println("<h2> Thank you "+session.getAttribute("custName")+" for booking flight ticket with FLYAWAY.<h2>");
out.println("<h1> Your flight ticket booking for "+session.getAttribute("flightId")+" is successfull<h1>");	
out.println("<h2> Ticket will be sent to registered email "+session.getAttribute("custEmail")+"<h2>");
%>
</div>
<div>
<form action="<%=request.getContextPath()%>/Home.jsp">
	<input type="submit" value="Redirect to Home Page" class="button">
</form>
</div>
</body>
</html>