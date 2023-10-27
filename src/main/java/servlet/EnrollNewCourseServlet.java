package servlet;

import java.io.IOException;
import java.io.PrintWriter;
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
//sql insert 
@WebServlet("/EnrollNewCourseServlet")
public class EnrollNewCourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static final String DB_URL = "jdbc:mysql://localhost/assignment3";
	static final String USER = "root";
	static final String PASS = "GYY3170317019";
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//retrieve userId from session
		HttpSession session = request.getSession();
		String userId = (String)session.getAttribute("userId");
		//accept value of student.jsp
		String courseId = request.getParameter("courseId");
		
		Connection connection = null;
		//boolean notEnrolled = true;
		//Statement statement = null;
		//ResultSet resultSet = null;		
		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		try {
			connection = DriverManager.getConnection(DB_URL, USER, PASS);
			
			//check if the student has already enrolled in this course
			String sql ="select * from user_course where userId = ? and courseId = ?";
			PreparedStatement prepareStatement = connection.prepareStatement(sql);
			prepareStatement.setString(1, userId);
			prepareStatement.setString(2, courseId);
			ResultSet resultSet = prepareStatement.executeQuery(); 
			
//			boolean text = resultSet.next();
//			System.out.println(text);//false
			
			if(resultSet.next()) {
				//the student has already enrolled in this course
				//notEnrolled = false;
				response.setContentType("text/html");
				PrintWriter out = response.getWriter();
				out.println("<html><body>");
				out.println("<h3>you are already enrolled in this course</h3>");
				out.println("</body></html>");
				//request.setAttribute("Error", "you have already enrolled in this course");
				
				//using include rather than forward, <h3>you are already enrolled in this course</h3> will be printed
				request.getRequestDispatcher("student.jsp").include(request,response);
			}else {
			//get the list of courses that student enrolled in 
		    String sql2 = "INSERT INTO assignment3.user_course(courseId,userId) VALUES(?,?)";
			PreparedStatement prepareStatement2 = connection.prepareStatement(sql2);
			prepareStatement2.setString(1, courseId);
			prepareStatement2.setString(2, userId);
			//resultSet = prepareStatement.executeQuery(); should use executeUpdate()
			int numRowsAffect = prepareStatement2.executeUpdate();
			//request.getSession().setAttribute("course",course );
			request.getRequestDispatcher("student.jsp").forward(request,response);
			
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
