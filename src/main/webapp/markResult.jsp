<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>mark result</title>
</head>
<body>
<table>
    <tr>
        <th>assessmentId</th>
        <th>userId</th>
        <th>quizeMark</th>
        <th>assignmentMark</th>
        <th>finalExamMark</th>
    </tr>
    <tr>
        <td>${ assessment.assessmentId}</td>
        <td>${ userId}</td>
        <td>${ assessment.quizeMark}</td>
        <td>${ assessment.assignmentMark}</td>
        <td>${ assessment.finalExamMark}</td>       
    </tr>
</table>



</body>
</html>