/*	************ Author: Patsianotakis Charalampos     ************
*
* 	************ This is a simple dynamic website,     ************
* 	************ connected with a database to register ************
* 	************ and login.                            ************
*
*/

package dataBase;

import java.util.Scanner;
import java.io.*;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Login extends HttpServlet {
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			  throws IOException, ServletException{
		String user=req.getParameter("uname");
		String pass=req.getParameter("upassword");
		
		PrintWriter pwriter = res.getWriter();
		
		String htmlUntilBody = "<!DOCTYPE html><html><head><title>My dataBase - Login</title><link rel=\"stylesheet\" href=\"styles.css\"></head>\" ";
		String htmlUntilTable = "<body><div class=\"background_cover\">\"<table align=\"right\"> <tr size=\"5\" style=\"color:white; text-align:right;\"><th><a style=\"color:red\" href=\"register.html\">Register</a></th><th>Login</th></tr></table><br><hr><div style=\"margin-left:0%;padding:1px 16px;height:1000px;\">";
		String htmlUntilResponse = "<table class =\"b01\" align=\"center\"><tr class =\"b01\"><th class =\"b01\"><h3>Login</h3></th></tr><tr><td class =\"b01\"><font size=\"5\" align=\"right\">" ;

		pwriter.println( htmlUntilBody + htmlUntilTable + htmlUntilResponse );
		
		//  Connect to DataBase //
		String dBurl = "jdbc:mysql://localhost:3306/myDB";
		String dataBase_user = "root";
		String dataBase_password = "12345678";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection myConn = DriverManager.getConnection(dBurl, dataBase_user, dataBase_password); 
			Statement myStmt = myConn.createStatement();
			
			ResultSet dataBase_response = myStmt.executeQuery("select * from users " + " where username='" + user + "';"); 

			if(dataBase_response.next()) {
				// Get user's password and compare it to the one the user gave.
				System.out.println(dataBase_response.getString("username") + ", " + dataBase_response.getString("password") + ", " + pass);
				if(dataBase_response.getString("password").equals(pass)) {
					pwriter.println("<h2>Successfully logged in as " + user + ".</h2>");
				}
				else {pwriter.println("<h2>Wrong password for user  " + user + ".</h2>");}
			}
			else {pwriter.println("<h2>No user " + user + " found.</h2>");}
		}catch(Exception e) {
			pwriter.println("<p>Error occured: " + e.toString() + "</p>");
			System.out.println("Error occurred while connecting the database");
		}
		
		pwriter.println("<font size=\"3\" >Not Registered yet?<br>Login Register <a style=\"color:red\" href=\"register.html\" >here</a></font></font></td></tr></table></div></div></body></html>");

		pwriter.close();		
	}


	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			  throws IOException, ServletException{

		PrintWriter pwriter = res.getWriter();

		String htmlUntilBody = "<!DOCTYPE html><html><head><title>My dataBase - Login</title><link rel=\"stylesheet\" href=\"styles.css\"></head>\" ";
		String htmlUntilTable = "<body><div class=\"background_cover\">\"<table align=\"right\"> <tr size=\"5\" style=\"color:white; text-align:right;\"><th><a style=\"color:red\" href=\"register.html\">Register</a></th><th>Login</th></tr></table><br><hr><div style=\"margin-left:0%;padding:1px 16px;height:1000px;\">";
		String htmlUntilResponse = "<table class =\"b01\" align=\"center\"><tr class =\"b01\"><th class =\"b01\"><h3>Login</h3></th></tr><tr><td class =\"b01\"><font size=\"5\" align=\"right\">" ;

		pwriter.println( htmlUntilBody + htmlUntilTable + htmlUntilResponse );
		
		pwriter.println("<html><body><h2>Page Not Found. <br> Return to <a href=\"index.html\" style=\"color: #f21111\">home</a></h2>");
		
		pwriter.println("<font size=\"3\" >Not Registered yet?<br>Login Register <a style=\"color:red\" href=\"register.html\" >here</a></font></font></td></tr></table></div></div></body></html>");

		pwriter.close();
	}
}