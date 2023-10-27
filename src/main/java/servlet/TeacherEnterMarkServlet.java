package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/TeacherEnterMarkServlet")
public class TeacherEnterMarkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static final String DB_URL = "jdbc:mysql://localhost/assignment3";
	static final String USER = "root";
	static final String PASS = "GYY3170317019";   
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//accept the value of teacherEnterMark.jsp
		String studentId = request.getParameter("studentId");
		String courseId = request.getParameter("courseId");
		String quizmark =request.getParameter("quizmark");
		String assignmentMark =request.getParameter("assignmentMark");
		String finalExamMark =request.getParameter("finalExamMark");
				
		Connection connection = null;
		ResultSet resultSet = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		try {
			connection = DriverManager.getConnection(DB_URL, USER, PASS);
			String sql = "INSERT INTO assignment3.assessment (studentId,courseId,quizmark,assignmentMark,finalExamMark) VALUES(?,?,?,?,?)";
			PreparedStatement prepareStatement = connection.prepareStatement(sql);
			prepareStatement.setString(1, studentId);
			prepareStatement.setString(2, courseId);
			prepareStatement.setString(3, quizmark);
			prepareStatement.setString(4, assignmentMark);
			prepareStatement.setString(5, finalExamMark);
			int numRowsAffect = prepareStatement.executeUpdate();
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		request.getRequestDispatcher("teacherEnterMark.jsp").forward(request,response);	
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
