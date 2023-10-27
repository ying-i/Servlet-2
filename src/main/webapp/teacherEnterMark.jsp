<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>enter marks</title>
</head>
<body>
<h2>enter marks:</h2>

<form method="post" action="TeacherEnterMarkServlet">
  <label for="studentId">studentId:</label>
  <input type="number" name="studentId" id="studentId"><br>
   
  <label for="courseId">courseId:</label>
  <input type="number" name="courseId" id="courseId"><br> 

  <label for="quizmark">quizMark:</label>
  <input type="number" name ="quizMark" id="quizMark"><br>
  
  <label for="assignmentMark">assignmentMark:</label>
  <input type="number" id="assignmentMark" name="assignmentMark" ><br>
  
  <label for="finalExamMark">finalExamMark:</label>
  <input type="number" id="finalExamMark" name="finalExamMark" ><br>
  <input type="submit" value="save mark">  
</form>   
        
<form method="post" action="LogoutServlet">
    <input type="submit" value="Logout">
</form>

</body>
</html>