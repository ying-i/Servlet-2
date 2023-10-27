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

@WebServlet("/CreateUserServlet")
public class CreateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static final String DB_URL = "jdbc:mysql://localhost/assignment3";
	static final String USER = "root";
	static final String PASS = "GYY3170317019";
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//accept the value of admin.jsp
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String phone = request.getParameter("phone");
		String role = request.getParameter("role");
		
		Connection connection = null;
//		Statement statement = null;
//		
		
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
			String sql = "INSERT INTO assignment3.user (userId,password,firstName,lastName,phone,role) VALUES(?,?,?,?,?,?)" ;
			PreparedStatement prepareStatement = connection.prepareStatement(sql);
			prepareStatement.setString(1, userId);
			prepareStatement.setString(2, password);
			prepareStatement.setString(3, firstName);
			prepareStatement.setString(4, lastName);
			prepareStatement.setString(5, phone);
			prepareStatement.setString(6, role);
			
			int numRowsAffect = prepareStatement.executeUpdate();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
//		String sql = "INSERT INTO assignment3.user (userId,password,firstName,lastName,phone,role)"+ "VALUES" 
//		+ "(" + userId + ","+ password +","+ firstName +","+lastName +","+ phone + "," + role +")";
		
		request.getRequestDispatcher("/admin.jsp").forward(request,response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
