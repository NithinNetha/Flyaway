<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Sorry for inconvenience</title>
</head>
<style>
.searchPageButton {
	background-color: white;
	color: #052a49;
	margin: 0px 9px;
	padding: 4px 4px;
	border: 2px solid #052a49;
	border-radius: 7px;
	font-size: 20px;
	cursor: pointer;
	
}
.searchPageButton:hover {
	background-color: #afd3f1;
}
.placeTable{
	position: absolute;
    left: 58px;
    top: 100px;
    display: inline-block;
	border: 2px solid black;
    padding: 17px 52px;
    border-radius: 8px;	
    width:20%;
    background-color:#052a49;
    color:white;
    font-size:28px;
}
.airlineTable{
	position: absolute;
    right: 34px;
    top:100px;
    display: inline-block;
	border: 2px solid black;
    padding: 17px 52px;
    border-radius: 8px;	
    width:20%;
    background-color:#052a49;
    color:white;
    font-size:28px;
}
.buttonDiv{
	display: block;
        width: 37%;
        margin: 60px 580px;}
</style>
<body>
<h1 style="text-align:center; color:#052a49;">Sorry, Currently we are not operating any flights for the places you searched.
	<br> Check the list below for places we operate.</h1>
<div class="placeTable">
<h2 style="font-size:32px;">Places we operate</h2>
	<table>
		<tbody>
		<c:forEach var="lp" items="${lop}">
		<tr><td> <c:out value="${lp}"></c:out></td></tr>
		</c:forEach> </tbody>
	</table>
</div>
<div class="airlineTable">
<h2 style="font-size:32px;">Airlines we operate </h2>
	<table >
		<tbody>
		<c:forEach var="la" items="${loa}">
		<tr><td> <c:out value="${la}"></c:out></td></tr>
		</c:forEach> </tbody>
	</table>
</div>
<div class="buttonDiv">
	<form action="<%=request.getContextPath()%>/Home.jsp">
		<input type="submit" value="Back to Search Page" class="searchPageButton">
	</form>
</div>
</body>
</html>