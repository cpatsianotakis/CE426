package dataBase;

import java.util.Scanner;
import java.io.*;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Register extends HttpServlet {
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			  throws IOException, ServletException{
		String user     = req.getParameter("uname");
		String pass     = req.getParameter("upassword");
		String pass_rep = req.getParameter("upassword_rep");
		
		PrintWriter pwriter = res.getWriter();
		
		String htmlUntilBody = "<!DOCTYPE html><html><head><title>My dataBase - Register</title><link rel=\"stylesheet\" href=\"styles.css\"></head>";
		String htmlUntilTable = "<body><div class=\"background_cover\">\"table align=\"right\"> <tr size=\"5\" style=\"color:white; text-align:right;\"><th>Register</th><th><a style=\"color:red\" href=\"index.html\">Login</a></th></tr></table><br><hr><div style=\"margin-left:0%;padding:1px 16px;height:1000px;\">";
		String htmlUntilResponse = "<table class =\"b01\" align=\"left\"><tr class =\"b01\"><th class =\"b01\"><h3>Register</h3></th></tr><tr><td class =\"b01\"><font size=\"5\" align=\"right\">" ;

		pwriter.println( htmlUntilBody + htmlUntilTable + htmlUntilResponse );
		// Check if user's passwords matches //
		if( pass.equals(pass_rep) )
		{
			
			//  Connect to DataBase //
			String dBurl = "jdbc:mysql://localhost:3306/myDB";
			String dataBase_user = "root";
			String dataBase_password = "12345678";
			
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection myConn = DriverManager.getConnection(dBurl, dataBase_user, dataBase_password); 
				Statement myStmt = myConn.createStatement();
				
				ResultSet dataBase_response = myStmt.executeQuery("select * from users " + " where username='" + user + "';"); 

				if(!dataBase_response.next()) {
					// Execute Insert SQL Query 
					myStmt.executeUpdate("insert into users " + " (username, password) " + " values('" + user + "', '" + pass + "');");
					pwriter.println ("<p>Registration Successful!<br>");
					pwriter.println ("Added " + user + "</p>");
				}
				else {
					pwriter.println ("<p>Registration failed!<br>");
					pwriter.println ("User " + user + " already exists in DB.</p>");
					do
					{
						user = user + "123";
						dataBase_response = myStmt.executeQuery("select * from users " + " where username='" + user + "';"); 
						if (!dataBase_response.next())
							pwriter.println ("Available username: " + user +"<br>" );
					}
					while ( dataBase_response.next() );
				}

			}catch(Exception e) {
				pwriter.println("<p>Error occured: " + e.toString() + "</p>");
				System.out.println("Error occurred while connecting the database");
			}
		}
		else
		{
			pwriter.println("<p>Passwords do not match!</p>");
		}
		pwriter.println("<font size=\"3\" >Have already an account?<br>Login <a style=\"color:red\" href=\"index.html\">here</a></font></font></td></tr></table></div></div></body></html>");
		
		pwriter.close();
		
	}
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			  throws IOException, ServletException{

		PrintWriter pwriter = res.getWriter();

		String htmlUntilBody = "<!DOCTYPE html><html><head><title>My dataBase - Register</title><link rel=\"stylesheet\" href=\"styles.css\"></head>";
		String htmlUntilTable = "<body><div class=\"background_cover\">\"table align=\"right\"> <tr size=\"5\" style=\"color:white; text-align:right;\"><th>Register</th><th><a style=\"color:red\" href=\"index.html\">Login</a></th></tr></table><br><hr><div style=\"margin-left:0%;padding:1px 16px;height:1000px;\">";
		String htmlUntilResponse = "<table class =\"b01\" align=\"left\"><tr class =\"b01\"><th class =\"b01\"><h3>Register</h3></th></tr><tr><td class =\"b01\"><font size=\"5\" align=\"right\">" ;

		pwriter.println( htmlUntilBody + htmlUntilTable + htmlUntilResponse );
		
		pwriter.println("<html><body><h2>Page Not Found. <br> Return to <a href=\"index.html\" style=\"color: #f21111\">home</a></h2>");
		


		pwriter.println("<font size=\"3\" >Have already an account?<br>Login <a style=\"color:red\" href=\"index.html\">here</a></font></font></td></tr></table></div></div></body></html>");

		pwriter.close();
	}
}