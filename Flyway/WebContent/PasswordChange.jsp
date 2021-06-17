<%@page import="javax.servlet.http.HttpSession"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
		HttpSession mySession = request.getSession(false);
		if (mySession == null) {
			out.print("<center><h3>Your session has expired.. Navigating you to home page.....</h3></center>");
			response.setHeader("refresh", "5;url='/Flyway'");
		} else {%>
		<h2>Enter details for password change</h2>
		<form action="ChangePassword" method="post">
		 <table border="1">
		 	<tr>
		 		<td><label>Username</label></td>
                <td><%=(String)session.getAttribute("uname")%></td>
		 	</tr>
		 	<tr>
                    <td>Enter Old Password</td>
                    <td><input type="password" name="oldPassword"></td>
                </tr>
                <tr>
                    <td>Enter New Password</td>
                    <td><input type="password" name="newPassword"></td>
                </tr>
		 </table>
		 <input type="submit" value="Change Password">
		</form>
	<%} %>
</body>
</html>