package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/CreateCourseServlet")
public class CreateCourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static final String DB_URL = "jdbc:mysql://localhost/assignment3";
	static final String USER = "root";
	static final String PASS = "GYY3170317019";
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//accept the value of admin.jsp
		String courseId =request.getParameter("courseId");
		String courseName =request.getParameter("courseName");
		String semester =request.getParameter("semester");
		
		Connection connection = null;
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
			String sql = "INSERT INTO assignment3.course (courseId,courseName,semester) VALUES(?,?,?)";
			PreparedStatement prepareStatement = connection.prepareStatement(sql);
			prepareStatement.setString(1, courseId);
			prepareStatement.setString(2, courseName);
			prepareStatement.setString(3, semester);
			
			int numRowsAffect = prepareStatement.executeUpdate();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		request.getRequestDispatcher("/admin.jsp").forward(request,response);	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
