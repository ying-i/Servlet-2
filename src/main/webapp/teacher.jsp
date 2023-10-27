<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>teacher dashboard</title>
</head>
<body>
<h1>welcome to the teacher dashboard</h1>
<c:if test="${ not empty sessionScope.assignedCourses }">
<h3>your assigned course:</h3>
  <table>
          <tr>
              <th>Course Id</th>
              <th>Course Name</th>
              <th>Semester</th>
              <th>View Enrolled Students</th>
          </tr>   
         
          <c:forEach var="course" items="${ assignedCourses }">
              <tr>
                  <td>${ course.courseId}</td>
                  <td>${ course.courseName }</td>
                  <td>${ course.semester }</td>
                  <td>
                      <form action="ViewEnrolledStudentServlet" method="post">
                          <input type="hidden" name="courseId" value="${ course.courseId }"> 
                          <!-- 提交form的时候，会把courseId也提交上去，只是是hidden的 -->
                          <input type="submit" value="View Enrolled Students">
                      </form>
                  <!--  <a href="viewMarkServlet?courseId=${ course.getCourseId() }">View Mark</a></td> -->
                  </td>
              </tr>
          </c:forEach>
          
  </table>
  </c:if>
  
  <c:if test="${ empty sessionScope.assignedCourses }">
      <h3>register to teach a particular course</h3>
      <table>
          <tr>
              <th>Course Id</th>
              <th>Course Name</th>
              <th>Semester</th>
              <th>register Course</th>
          </tr>
          <c:forEach var="course" items="${teacherallCourses}">
              <tr>
                  <td>${course.courseId}</td>
                  <td>${course.courseName}</td>
                  <td>${course.semester}</td>
                  <td>
                      <form action="TeacherRegisterNewCourseServlet" method="post">
                          <input type="hidden" name="courseId" value="${course.courseId}">
                          <input type="submit" value="Register Course">
                      </form>
                   </td>
                  <!--  <a href="viewMarkServlet?courseId=${ course.getCourseId() }">View Mark</a></td> -->
              </tr>
          </c:forEach>
      </table>
  </c:if>

<form method="post" action="LogoutServlet">
    <input type="submit" value="Logout">
</form>



</body>
</html>