<!DOCTYPE html>
<html>

<head>
    <meta charset="ISO-8859-1">
    <title>Admin Login</title>
</head>
<style>
    .homeButton {
        background-color: white;
        color: #052a49;
        margin: 0px 9px;
        padding: 4px 4px;
        border: 2px solid #052a49;
        border-radius: 7px;
        font-size: 20px;
        cursor: pointer;
    }
    .homeButton:hover{
        background-color: #afd3f1;
    }
</style>
<body>
    <div style="margin-left:20px;" class="logo">
        <h1 style="color:#052a49;font-size: 50px; font-family:cursive;display: inline; ">Flyway</h1>
        <img src="IMAGES/logoflyway.png" alt="logoflyway">
    </div>
    <div style="position: absolute; right: 20px;top:40px; display: inline;" class="admin">
        <form action="Home.jsp">
            <input class="homeButton" type="submit" value="Home Page">
        </form>
    </div>
    <br>
    <br>
    <h2 style="text-align:center; color: brown; ">Welcome to Flyway admin portal</h2>
    <hr />
    <div style="text-align: center;">
        <form action="LoginServlet" method="post">
            <table style="border: black 2px solid; text-align: center; margin-left: auto;margin-right: auto;">
                <tr >
                    <td>Enter Username</td>
                    <td><input type="text" name="username" placeholder="username"></td>
                </tr>
                <tr>
                    <td>Enter Password</td>
                    <td><input type="password" name="password" placeholder="password"></td>
                </tr>
            </table>
            <br>
            <input type="submit" value="Login">
        </form>
    </div>
    <div style="position:fixed; bottom:0;right:0;left:0; width:100%;">
   <%@include file="footer.html" %>
    </div>
</body>
</html>