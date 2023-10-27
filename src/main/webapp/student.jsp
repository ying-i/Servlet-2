
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>     
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>student dashboard</title>
</head>
<body>
  <h1>welcome to the student dashboard</h1>
  
 <c:if test="${ not empty sessionScope.enrolledCourses }">
  <table>
          <tr>
              <th>Course Id</th>
              <th>Course Name</th>
              <th>Semester</th>
              <th>View Mark</th>
          </tr>   
         
          <c:forEach var="course" items="${ enrolledCourses }">
              <tr>
                  <td>${ course.courseId}</td>
                  <td>${ course.courseName }</td>
                  <td>${ course.semester }</td>
                  <td>
                      <form action="ViewMarkServlet" method="post">
                          <input type="hidden" name="courseId" value="${ course.courseId }"> 
                          <!-- 提交form的时候，会把courseId也提交上去，只是是hidden的 -->
                          <input type="submit" value="view Mark">
                      </form>
                  <!--  <a href="viewMarkServlet?courseId=${ course.getCourseId() }">View Mark</a></td> -->
                  </td><!-- After successful enrollment, when the students click on a course, 
                            they should be able to see their assessment marks -->
              </tr>
          </c:forEach>
          
  </table>
  </c:if>
  
  <c:if test="${ empty sessionScope.enrolledCourses }">
      <h3>you need enroll your new course</h3>
      <table>
          <tr>
              <th>Course Id</th>
              <th>Course Name</th>
              <th>Semester</th>
              <th>Enroll Course</th>
          </tr>
          <c:forEach var="course" items="${allCourses}">
              <tr>
                  <td>${course.courseId}</td>
                  <td>${course.courseName}</td>
                  <td>${course.semester}</td>
                  <td>
                      <form action="EnrollNewCourseServlet" method="post">
                          <input type="hidden" name="courseId" value="${course.courseId}">
                          <input type="submit" value="enroll Course">
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