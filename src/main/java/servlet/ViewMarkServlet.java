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

import assessment3.Assess;

//import assessment3.Assessment;


@WebServlet("/ViewMarkServlet")
public class ViewMarkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static final String DB_URL = "jdbc:mysql://localhost/assignment3";
	static final String USER = "root";
	static final String PASS = "GYY3170317019";
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//retrieve userId from session
		HttpSession session = request.getSession();
		String userId = (String)session.getAttribute("userId");
		//accept value of student.jsp's form 
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
			
			String sql ="select * from assessment where userId = ? and courseId = ?";
			PreparedStatement prepareStatement = connection.prepareStatement(sql);
			prepareStatement.setString(1, userId);
			prepareStatement.setString(2, courseId);
		    resultSet = prepareStatement.executeQuery(); 
		    
			//Assessment assessment = null;
		    Assess assessment = null;
		    //resultSet.next(); //写这样或下面加上if
		    if(resultSet.next()) { //如果不写resultSet.next(), error: java.sql.SQLException: Before start of result set
				int assessmentId = resultSet.getInt("assessmentId");
				String quizeMark = resultSet.getString("quizeMark");
				String assignmentMark = resultSet.getString("assignmentMark");
				String finalExamMark = resultSet.getString("finalExamMark");
				
				
//				Assessment assessment = new Assessment(assessmentId,userId,courseId,quizeMark,assignmentMark,finalExameMark);
				assessment = new Assess();
				assessment.setQuizeMark(quizeMark);
				assessment.setAssignmentMark(assignmentMark);
				assessment.setFinalExamMark(finalExamMark);
				assessment.setAssessmentId(assessmentId);	
			}
			//assessment.setUserId();
			//将查询的结果放入assessment对象中，然后将对象存到session中
			//store the assessment object in the session
			request.getSession().setAttribute("assessment", assessment);
			request.getRequestDispatcher("/markResult.jsp").forward(request,response);
		    
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
