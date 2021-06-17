<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Confirm Booking</title>
</head>
<style>
.details{
font-size:28px;
}
 .btn {
        background-color: white;
        color: #052a49;
        margin-left: 600px;
        padding: 4px 4px;
        border: 2px solid #052a49;
        border-radius: 7px;
        font-size: 20px;
        cursor: pointer;
    }
    .btn:hover{
        background-color: #afd3f1;
    }
    .tab{
    	margin-left:auto;
       	margin-right:auto;
        display: block;
        background-color: #052a49;
        padding: 52px 52px;
        width:50%;
        border-radius: 8px;
        font-size:28px;
        text-align:right;
        }
        label {
        font-size: 24px;
        color: white;
    }
    input[type=text] 
    {
        font-size: 14px;
        padding: 12px 20px;
        border: 1px solid white;
        border-radius: 4px;
        width:100%
    }
    .info{
    text-align:center;
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
	<%if (mySession.getAttribute("exception") != null) {%>
	<div
		style="color: red; text-align: center; border: 1px outset red; background-color: lightblue;">
		<%=mySession.getAttribute("exception")%>
	</div>
	<%}%>
	<%mySession.setAttribute("exception", null);%>
	<div class="getDetails">
	<form action="SearchServlet" method="post">
	 <table class="tab">	
		<tr>
		<td><label for="source">Enter Name &nbsp</label></td>
    	 <td><input type="text" name="custName" placeholder="as shown in Passport"></td>
    	 </tr>
    	 <tr>
		<td><label for="source">Enter email &nbsp</label></td>
    	 <td><input type="text" name="custEmail" placeholder="to send ticket"></td>
    	 </tr>
    	 <tr>
		<td><label for="source">Enter Contact Number &nbsp</label></td>
    	 <td><input type="text" name="custNum" placeholder="for further contact"></td>
    	 </tr>
	</table> 
	<h3 class="info">By clicking on Confirm and Pay below your details will be saved for further contact</h3>
	<hr>
	<h1 class="head">Confirm Flight Details and proceed to pay</h1>
	<%
	out.println("<h3>By clicking on Confirm you will be booked ticket from <strong>"+session.getAttribute("userSource")+
			"</strong> to <strong>"+session.getAttribute("userDest")+
			"</strong>, flying with <strong>"+session.getAttribute("userAirline")+
			"</strong> airlines and the total price will <strong>"+session.getAttribute("price")+
			"</strong> for <strong>"+session.getAttribute("nop")+"</strong> passengers on date <strong>"+session.getAttribute("doj")+"</strong></h3>");
    %>
	<br>
	<input type="submit" value="Confirm and Proceed to Pay" class="btn">
	</form>
	</div>
</body>
</html>