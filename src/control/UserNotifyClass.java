package control;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import support.DBConnection;

public class UserNotifyClass extends HttpServlet {
	
@Override
protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
    try
    { 
    	
    	
    	 Connection connection=DBConnection.DBConnect();
    	 HttpSession session=request.getSession();
    	 session.getAttribute("seusername");
    	 int recID=Integer.parseInt((String) session.getAttribute("seuser_id"));
    	 
    	// String sql="SELECT * FROM user_file_table where receiver_id="+recID+" ORDER BY status_msg  ASC;";
    	
    	 
    	 	String folderpath="resources/pages/user/";
    	
    	 	 ServletContext servletContext = getServletContext();
             String contextPath = servletContext.getRealPath(File.separator);
             String path = contextPath + "\\uploads\\" + session.getAttribute("seusername");
    		 
	    	 String sql="SELECT * FROM user_file_table where receiver_id="+recID+" ORDER BY status_msg  ASC";
	    	 System.out.println(sql);
	         PreparedStatement ps=connection.prepareStatement(sql);
	         ResultSet fs=ps.executeQuery();
	         
	         System.out.println(sql);
	         String sql2="SELECT * FROM user_msg_table where receiver_id="+recID+" ORDER BY status_msg  ASC;";
	         PreparedStatement ps2=connection.prepareStatement(sql2);
	         ResultSet ms=ps2.executeQuery();
	         
	        
	         request.setAttribute("FileData",fs);
	         request.setAttribute("MsgData",ms);
	         
	         
	         String sql3="SELECT * FROM user_file_table where user_id="+recID+" ORDER BY status_msg  ASC";
	         PreparedStatement ps3=connection.prepareStatement(sql3);
	         ResultSet fs1=ps3.executeQuery();
	         
	        
	         String sql4="SELECT * FROM user_msg_table where user_id="+recID+" ORDER BY status_msg  ASC;";
	         PreparedStatement ps4=connection.prepareStatement(sql4);
	         ResultSet ms2=ps4.executeQuery();
	         
	         
	         request.setAttribute("FileData",fs);
	         request.setAttribute("MsgData",ms);
	         
	         request.setAttribute("senFileData",fs1);
	         request.setAttribute("senMsgData",ms2);
	         
	         request.setAttribute("path",path);
      	   	 RequestDispatcher dispatcher = request.getRequestDispatcher(folderpath+"unotification.jsp");
      	     dispatcher.forward(request, response); 
      	     
      	     
    	
    
}catch(Exception err){ err.printStackTrace(); }
}
}

