package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import assessment3.Course;


@WebServlet("/EnrolledCoursesServlet")
public class EnrolledCoursesServlet extends HttpServlet {
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
			
			//get the list of courses that student enrolled in 
			List<Course> enrolledCourses = new ArrayList<>();
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
				enrolledCourses.add(course);
			}//After login, students will see the courses the re enrolled in
			
			request.getSession().setAttribute("enrolledCourses", enrolledCourses);
			request.getSession().setAttribute("course",course );
			request.getRequestDispatcher("student.jsp").forward(request,response);
			
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
