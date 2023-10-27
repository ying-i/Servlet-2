package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
//judge the student enrolled or have not enrolled, if enrolled then go EnrolledCoursesServlet(in this servlet, will go student.jsp);
//if have not enrolled go to GetAllCoursesServlet(in this servlet will go student.jsp)
@WebServlet("/EnrolledOrNot")
public class EnrolledOrNot extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static final String DB_URL = "jdbc:mysql://localhost/assignment3";
	static final String USER = "root";
	static final String PASS = "GYY3170317019";
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//retrieve userId from session
		HttpSession session = request.getSession();
		String userId = (String)session.getAttribute("userId");
		
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;		
		boolean enrolled=false;
		//String courseName="";
		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		try {
		connection = DriverManager.getConnection(DB_URL, USER, PASS);
		statement = connection.createStatement();
		//List<Course> allCourses = new ArrayList<>();
		String sql = "select * from course inner join user_course on course.courseId = user_course.courseId where user_course.userId= ?";
		PreparedStatement prepareStatement = connection.prepareStatement(sql);
		prepareStatement.setString(1, userId);
		resultSet = prepareStatement.executeQuery();
		//String sql = "select * from course";
	
		if(resultSet.next()) {
			enrolled = true;
			//courseName = resultSet.getString("courseName");
		}
		
		if(enrolled) {
			response.sendRedirect(request.getContextPath()+"/EnrolledCoursesServlet");
		}else {
			response.sendRedirect(request.getContextPath()+"/GetAllCoursesServlet");
		}
	
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
