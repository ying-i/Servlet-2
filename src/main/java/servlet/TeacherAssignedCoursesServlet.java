package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import assessment3.Course;


@WebServlet("/TeacherAssignedCoursesServlet")
public class TeacherAssignedCoursesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static final String DB_URL = "jdbc:mysql://localhost/assignment3";
	static final String USER = "root";
	static final String PASS = "GYY3170317019";
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		//int userId = (int)session.getAttribute("userId");
		String userId = (String)session.getAttribute("userId");
		Connection connection = null;
		//Statement statement = null;
		ResultSet resultSet = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		try {
			connection = DriverManager.getConnection(DB_URL, USER, PASS);
			
			//get the list of courses that teacher is assigned  
			List<Course> assignedCourses = new ArrayList<>();
			String sql = "select * from course inner join user_course on course.courseId = user_course.courseId where user_course.userId = ?";
			
			PreparedStatement prepareStatement = connection.prepareStatement(sql);
			prepareStatement.setString(1, userId);
			resultSet = prepareStatement.executeQuery();
			Course course = null;
			while(resultSet.next()) {
				int courseId = resultSet.getInt("courseId");
				String courseName = resultSet.getString("courseName");
				int semester = resultSet.getInt("semester");
			    course = new Course(courseId,courseName,semester);
			    assignedCourses.add(course);
			}
			
			request.getSession().setAttribute("assignedCourses", assignedCourses);
			request.getSession().setAttribute("course",course );
			request.getRequestDispatcher("teacher.jsp").forward(request,response);
			
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
