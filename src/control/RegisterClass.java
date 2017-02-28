package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import support.DBConnection;
import java.util.*;
import java.util.Date;


public class RegisterClass extends HttpServlet {
	
@Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
    try
    {
    	
    	 Connection connection=DBConnection.DBConnect();
    	 //Collecting objects of session and db connect
    	 String folderpath="resources/pages/";
    	 
     if(request.getParameter("createUser").equals("Sign Up")){
   	 
   	 String username	=request.getParameter("username");
   	 String password	=request.getParameter("password");
   	 String email		=request.getParameter("email");
   	 
   	 // date and time 
   	 Date dNow = new Date();
     SimpleDateFormat ft = 
     new SimpleDateFormat ("E yyyy.MM.dd 'at' hh:mm:ss a");
     
   	 
	 String checksql="SELECT * FROM user_table WHERE username=?";
     PreparedStatement ps=connection.prepareStatement(checksql);
     ps.setString(1,username);
     ResultSet rs=ps.executeQuery();
     if (rs.next()){
    	 
  	   request.setAttribute("msg","This 'Username' is already taken, Please try another...");
  	   request.setAttribute("colour","red");
  	   RequestDispatcher dispatcher = request.getRequestDispatcher(folderpath+"register.jsp");
  	   connection.close();
  	   dispatcher.forward(request, response);
   }
    
     System.out.println("hai");
    String sql="INSERT INTO user_table(username,password,email,last_login,status) VALUES(?,?,?,?,?)";
    PreparedStatement ps2=connection.prepareStatement(sql);
    ps2.setString(1, username); 
    ps2.setString(2, password);
    ps2.setString(3, email);
    ps2.setString(4, ft.format(dNow));
    ps2.setString(5, "N");
    
    int i=ps2.executeUpdate();
    String msg=(i>0)?"User Added! Account pending for verification. !":"Failed!! please tyr after some time";

    request.setAttribute("msg",msg);
    request.setAttribute("colour","green");
    RequestDispatcher dispatcher = request.getRequestDispatcher(folderpath+"login.jsp");
    connection.close();
    dispatcher.forward(request, response);
	   
     }	
    }catch(Exception err){ err.printStackTrace(); }
  }
}
