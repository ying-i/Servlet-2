package servlet;

import java.io.IOException;
import java.io.PrintWriter;
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

import assessment3.User;


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	/*
	 * LoginServlet接收前端传过来的值:String userId = request.getParameter(“userId”);、
	 * String password = request.getParameter(“password”)。拿着传过来的值去访问数据库。
	 */
	private static final long serialVersionUID = 1L;
	static final String DB_URL = "jdbc:mysql://localhost/assignment3";
	static final String USER = "root";
	static final String PASS = "GYY3170317019";
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//accept the value of login.jsp
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
//		User u = new User();
//		u.setUserId(Integer.valueOf(userId));//The method setUserId(int) in the type User is not applicable for the arguments (String)
//		u.setPassword(password);
		
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
				
		String role;
		//依次展示each role 和页面演示一起
		try {
			connection = DriverManager.getConnection(DB_URL, USER, PASS);
			statement = connection.createStatement();
			String sql = "select * from user where userId=" + userId;
			resultSet = statement.executeQuery(sql);
			
			while(resultSet.next()) {
				role = resultSet.getString("role");
				if(userId.equals(resultSet.getString("userId")) && password.equals(resultSet.getString("password")) && role.equals("student")) {
					HttpSession session = request.getSession();
					session.setAttribute("userId", userId);//set userId and password in session
					session.setAttribute("password", password);
					//request.getRequestDispatcher("EnrolledOrNot").forward(request, response);	
					//need to go a servlet rather than a student.jsp
					//response.sendRedirect(request.getContextPath()+"/Success,jsp");
					response.sendRedirect(request.getContextPath()+"/EnrolledOrNot");
					
				}else if(userId.equals(resultSet.getString("userId")) && password.equals(resultSet.getString("password")) && role.equals("admin")) {
					HttpSession session = request.getSession();
					session.setAttribute("userId", userId);
					session.setAttribute("password", password);
					response.setContentType("text/html");
					PrintWriter out = response.getWriter();
					out.println("<html><body>");
					out.println("<h3>xxxxxxxxxxxxxx</h3>");
					out.println("</body></html>");
					
					request.getRequestDispatcher("/admin.jsp").include(request, response);
					//using include rather than forward,only use include can show the sentence of xxxxxxxxxxxx
					
					
				}else if(userId.equals(resultSet.getString("userId")) && password.equals(resultSet.getString("password")) && role.equals("teacher")) {
					HttpSession session = request.getSession();
					session.setAttribute("userId", userId);
					session.setAttribute("password", password);
					//request.getRequestDispatcher("/teacher.jsp").forward(request, response);
					//can not go to a teacher.jsp, need to go a servelt
					response.sendRedirect(request.getContextPath()+"/TeacherAssignedCourseOrNot");
				}else {
					request.setAttribute("error", "wrong userId or password"); 
					request.getRequestDispatcher("/login.jsp").forward(request, response);
				}
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
