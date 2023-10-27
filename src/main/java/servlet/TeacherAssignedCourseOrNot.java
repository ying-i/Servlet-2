package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * //judge the teacher is assigned course or have not be assigned course, if is assigned course then go TeacherAssignedCoursesServlet(in this servlet, will go teacher.jsp);
   //if have not be assigned course then go to TeacherGetAllCoursesServlet(in this servlet will go teacher.jsp)
 */
@WebServlet("/TeacherAssignedCourseOrNot")
public class TeacherAssignedCourseOrNot extends HttpServlet {
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
		boolean assigned=false;
		
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
			if(resultSet.next()) {
				assigned = true;
				//courseName = resultSet.getString("courseName");
			}
			
			if(assigned) {
				response.sendRedirect(request.getContextPath()+"/TeacherAssignedCoursesServlet");
				//After login, teacher will see the list of assigned courses.
			}else {
				response.sendRedirect(request.getContextPath()+"/TeacherGetAllCoursesServlet");
				//If no course is assigned, there should be an option where they can see a list of all courses 
				//and register to teach a particular course.
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
