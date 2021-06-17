<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome to Flyaway</title>
</head>
<style>
    body {
        background-image: url("<%=request.getContextPath()%>/IMAGES/homeBG.jpg");
        background-size: cover;
    }
    .container {
       	margin-left:auto;
       	margin-right:auto;
        display: block;
        background-color: #052a49;
        padding: 17px 52px;
        width: 30%;
        border-radius: 8px
    }
    label {
        font-size: 24px;
        color: white;
    }
    input[type=text],
    input[type=date] 
    {
        font-size: 14px;
        padding: 12px 20px;
        border: 1px solid white;
        border-radius: 4px;
        width:80%
    }
    select{
    	font-size: 14px;
        padding: 12px 20px;
        border: 1px solid white;
        border-radius: 4px;
        width:100%
    }
    .button {
        background-color: white;
        color: #052a49;
        margin-left: 299px;
        padding: 4px 4px;
        border: 2px solid #052a49;
        border-radius: 7px;
        font-size: 20px;
        cursor: pointer;
    }
    .button:hover{
        background-color: #afd3f1;
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
	<div style="background-color: white; position: fixed; top: 0; right: 0; left: 0">
		<div style="margin-left:20px;" class="logo">
        <h1 style="color:#052a49;font-size: 50px; font-family:cursive;display: inline; ">FlyAway</h1>
        <img src="IMAGES/logoflyway.png" alt="logoflyway">
    </div>
    <div style="position: absolute; right: 20px;top:40px; display: inline;" class="admin">
        <form action="Login.jsp">
            <input class="button" type="submit" value="Admin Login">
        </form>
    </div>
	</div>
	<br>
	<div style="width:100%;">
        <h1 style="color: #052a49; font-size: 80px; text-align:center">Let the journey
            begin</h1>
    </div>
    <%if (mySession.getAttribute("action") != null) {%>
	<span
		style="color: #052a49; text-align: center; background-color: #afd3f1;margin-left:40%; font-size:30px;">
		<%=mySession.getAttribute("action")%>
	</span>
	<%}	%>
	<%mySession.setAttribute("action", null);%>
	<%if (mySession.getAttribute("noFlights") != null) {%>
	<span
		style="color: #052a49; text-align: center; background-color: #afd3f1;margin-left:30%; font-size:30px;">
		<%=mySession.getAttribute("noFlights")%>
	</span>
	<%}	%>
	<%mySession.setAttribute("noFlights", null);%>
    <div class="container">
        <form action="SearchServlet" method="get">
            <table>
                <tr>
                    <td><label for="source">Source</label></td>
                    <td><input type="text" name="source" placeholder="From" required></td>
                </tr>
                <tr>
                    <td><label for="destination">Destination</label></td>
                    <td><input type="text" name="destination" placeholder="To" required></td>
                </tr>
                <tr>
                    <td><label for="nop">No. of Passengers</label></td>
                    <td><select id="nop" name="nop">
                    <option value="" Disabled Selected>Passengers</option>
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>
                    <option value="5">5</option>
                    <option value="6">6</option>
                    <option value="7">7</option>
                    <option value="8">8</option>
                    <option value="9">9</option>
                    <option value="10">10</option>
                    </select></td>
                    <!-- <td><input type="text" name="passengers" placeholder="No. of passengers"></td> -->
                </tr>
                <tr>
                    <td><label for="dateOfT">Date of Travel</label></td>
                    <td><input type="date" name="dateOfT"></td>
                </tr>
                <tr>
                    <td><label for="classType">Class</label></td>
                    <td><select id="classType" name="classType">
                    		<option value="" Disabled Selected>Select class</option>
                            <option value="Economy">Economy Class</option>
                            <option value="First">First Class</option>
                            <option value="Business">Business Class</option>
                        </select></td>
                </tr>
            </table>
            <br>
            <input style="text-align:right" type="submit" value="Search Flights" class="button">
        </form>
    </div>
    <br>
    <div>
   <%@include file="footer.html" %>
    </div>
</body>
</html>