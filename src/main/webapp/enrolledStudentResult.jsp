<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>enrolled students</title>
</head>
<body>
<table>
    <tr>  
        <th>studentId</th>
        <th>firstName</th>
        <th>lastName</th>
        <th>enter marks</th>
    </tr>
    <c:forEach var="student" items="${ enrolledStudents }">
        <tr>
          <%--  <td>${student.studentId}</td> 不能写成这样，在类型 [assessment3.User] 上未找到属性 [studentId]
            因为var=student,但是student本质上是个usser object
            它只有userId 没有studentId
             --%> 
            <td>${student.userId}</td>       
            <td>${student.firstName}</td>
            <td>${student.lastName}</td>            
            <td>
                <form action="teacherEnterMark.jsp" method="post">                     
                     <!-- 提交form的时候，会把courseId也提交上去，只是是hidden的 -->
                     <input type="hidden" name="courseId" value="${ course.courseId }">
                     <input type="hidden" name="studentId" value="${ student.userId }">
                     <input type="submit" value="Enter Mark">     
                 </form>
            </td>
        </tr>
    </c:forEach>
</table>

</body>
</html>