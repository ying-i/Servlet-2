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

import assessment3.User;


@WebServlet("/ViewEnrolledStudentServlet")
public class ViewEnrolledStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static final String DB_URL = "jdbc:mysql://localhost/assignment3";
	static final String USER = "root";
	static final String PASS = "GYY3170317019";  
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//retrieve userId from session
		HttpSession session = request.getSession();
		String userId = (String)session.getAttribute("userId");
		//accept value of teacher.jsp's form 
		String courseId = request.getParameter("courseId");
		Connection connection = null;
		ResultSet resultSet = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			connection = DriverManager.getConnection(DB_URL, USER, PASS);
			//get the list of students  
			List<User> enrolledStudents = new ArrayList<>();
			String sql ="select * from user as u join user_course as uc on u.userId = uc.userId where role='student' and uc.courseId = ?";
			PreparedStatement prepareStatement = connection.prepareStatement(sql);
			prepareStatement.setString(1, courseId);			
		    resultSet = prepareStatement.executeQuery(); 
		    User student = null;
		    while(resultSet.next()) {
				int studentId = resultSet.getInt("userId") ;
				String password = resultSet.getString("password");
				String firstName = resultSet.getString("firstName");
				String lastName = resultSet.getString("lastName");
				String phone = resultSet.getString("phone");
				String role = resultSet.getString("role");
				student = new User(studentId,password,firstName,lastName,phone,role);
				enrolledStudents.add(student);				
			 }
	    
		    request.getSession().setAttribute("enrolledStudents", enrolledStudents);
			request.getSession().setAttribute("student",student );
			request.getRequestDispatcher("enrolledStudentResult.jsp").forward(request,response);
			//When the instructor selects a course, the system displays a list of students enrolled in that course.
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
