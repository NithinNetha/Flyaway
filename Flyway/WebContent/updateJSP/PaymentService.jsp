<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Payment Process</title>
</head>
<style>
body { margin:50px auto; width:250px;}
.full{
background-color:#bba84b;
text-align:center;
font-size:20px;
color:#0a164e
}
.btn{
background-color: white;
        color: #052a49;
        padding: 2px 4px;
        border: 2px solid #052a49;
        border-radius: 7px;
        font-size: 20px;
        cursor: pointer;
}
input[type=text]
    {
        font-size: 14px;
        padding: 12px 20px;
        border: 1px solid white;
        border-radius: 4px;
        width:80%
    }
</style>
<body>
	<div>
		<div class="full">
			<h3>Payment Details</h3>
			<label>CARD NUMBER</label><br>
			<input type="text" class="cardText" placeholder="Valid Card Number"><br>
			<div class="code">
			<label>EXPIRATION DATE</label><br>
			<input type="text" class="expDate" placeholder="MM/YY"><br>
			<label>CVC CODE</label><br>
			<input type="text" class="cvc" placeholder="CVC"><br>
			</div><br>
			<form action="EndPage.jsp"><input type="submit" value="Pay" class="btn"></form>
		</div>
		<br>
	</div>
</body>
</html>