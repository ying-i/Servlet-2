package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import assessment3.Course;


@WebServlet("/GetAllCoursesServlet")
public class GetAllCoursesServlet extends HttpServlet {
	static final String DB_URL = "jdbc:mysql://localhost/assignment3";
	static final String USER = "root";
	static final String PASS = "GYY3170317019";
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		//String userId = (String)session.getAttribute("userId");

		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		try {
			connection = DriverManager.getConnection(DB_URL, USER, PASS);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			statement = connection.createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//get the list of all courses
		List<Course> allCourses = new ArrayList<>();
		String sql = "select * from course";
		try {
			resultSet = statement.executeQuery(sql);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			Course course = null;
			while(resultSet.next()) {
				int courseId = resultSet.getInt("courseId");
				String courseName = resultSet.getString("courseName"); 
				int semester = resultSet.getInt("semester");
				course = new Course(courseId,courseName,semester);
				allCourses.add(course);
			}
			request.getSession().setAttribute("allCourses",allCourses );
			request.getSession().setAttribute("course",course );
			request.getRequestDispatcher("student.jsp").forward(request,response);
			//If the student is not enrolled in any course, there should be an option where they can 
			//see a list of all courses
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//return allCourses;
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
