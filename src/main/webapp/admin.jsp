<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>admin dashboard</title>
</head> 
<body>
<h1>welcome to the admin dashboard</h1>
<h2>create new user</h2>
<form method="post" action="CreateUserServlet">
    <lable for="userId">userId</lable>
    <input type="text" id="userId" name="userId"><br>
    <lable for="password">password</lable>
    <input type="password" id="password" name="password"><br>
    <lable for="firstName">firstName</lable>
    <input type="text" id="firstName" name="firstName"><br>
	<label for="lastName">Last Name:</label>
	<input type="text" id="lastName" name="lastName"><br>
	<label for="phone">Phone:</label>
	<input type="text" id="phone" name="phone"><br>
	<label for="role">Role:</label>
	<select id="role" name="role">
		<option value="student">Student</option>
		<option value="teacher">Teacher</option>
	</select><br>
	<input type="submit" value="Create User">
	</form>
	
<h2>Create New Course</h2>
	<form method="post" action="CreateCourseServlet">
		<label for="courseId">Course ID:</label>
		<input type="text" id="courseId" name="courseId"><br>
		<label for="courseName">Course Name:</label>
		<input type="text" id="courseName" name="courseName"><br>
		<label for="semester">Semester:</label>
		<input type="text" id="semester" name="semester"><br>
		<input type="submit" value="Create Course">
	</form>

<form method="post" action="LogoutServlet">
    <input type="submit" value="Logout">
</form>

</body>
</html>