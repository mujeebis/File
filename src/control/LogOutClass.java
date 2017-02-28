package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import support.DBConnection;


public class LogOutClass extends HttpServlet {
	
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        try
        {   
        	 
       String folderpath="resources/pages/";
	   Connection connection=DBConnection.DBConnect();
	   HttpSession session=request.getSession();
	   request.setAttribute("msg", "Successfully Logout...");
	   request.setAttribute("colour","green");
	   RequestDispatcher dispatcher = request.getRequestDispatcher(folderpath+"login.jsp");
	   connection.close();
	   session.invalidate(); 
	   dispatcher.forward(request, response);
	        		 
}catch(Exception err)
{
    err.printStackTrace();
}
}

}
