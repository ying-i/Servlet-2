<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login</title>
</head>

<body>
<h1>welcome to student assessment system</h1>
 <form method="post" action="LoginServlet">
        <label for="userId">UserID:</label>
        <input type="text" id="userId" name="userId"><br>
        <label for="password">Password:</label>
        <input type="password" id="password" name="password"><br>
        <input type="submit" value="Login">
        <input type="reset" value="reset">
    </form>
</body>
</html>