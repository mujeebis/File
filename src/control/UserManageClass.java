package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import support.DBConnection;

public class UserManageClass extends HttpServlet {
	
@Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
    try
    {
    	
    	 Connection connection=DBConnection.DBConnect();
    	 String folderpath="resources/pages/admin";
    	 if(request.getParameter("btnname").equals("User Management")){
    		 
    		 
	    	 String sql="SELECT * FROM user_table ORDER BY status ASC;";
	         PreparedStatement ps=connection.prepareStatement(sql);
	         ResultSet rs=ps.executeQuery();
	         
	        
	         request.setAttribute("UserData",rs);
	         System.out.println(folderpath+"/usermanagement.jsp");
      	   	 RequestDispatcher dispatcher = request.getRequestDispatcher(folderpath+"/usermanagement.jsp");
      	     dispatcher.forward(request, response);
    	 }
    	 
    	 if(request.getParameter("btnname").equals("Transaction Logs")){
    		 
    		 //System.out.println("Transaction Logs");
    		 String sql="SELECT * FROM user_file_table ORDER BY date  DESC";
	    	 System.out.println(sql);
	         PreparedStatement ps=connection.prepareStatement(sql);
	         ResultSet fs=ps.executeQuery();
	         
	         System.out.println(sql);
	         String sql2="SELECT * FROM user_msg_table ORDER BY date  DESC";
	         PreparedStatement ps2=connection.prepareStatement(sql2);
	         ResultSet ms=ps2.executeQuery();
	         
	        
	         request.setAttribute("FileData",fs);
	         request.setAttribute("MsgData",ms);
	         
	         RequestDispatcher dispatcher = request.getRequestDispatcher(folderpath+"/adminlog.jsp");
      	     dispatcher.forward(request, response);
      	     
    		
    	 }
    	 
    	 if(request.getParameter("btnname").equals("Keys Request")){
    		// System.out.println("Keys Request");
    		 
    		//System.out.println("Transaction Logs");
    		 String sql="SELECT * FROM user_file_table where admin_key_status!='Y' and status_msg='puzzle solved' ORDER BY status_msg  ASC";
	    	 System.out.println(sql);
	         PreparedStatement ps=connection.prepareStatement(sql);
	         ResultSet fs=ps.executeQuery();
	         
	         
	         String sql2="SELECT * FROM user_msg_table where admin_key_status!='Y' and status_msg='puzzle solved' ORDER BY status_msg  ASC";
	         PreparedStatement ps2=connection.prepareStatement(sql2);
	         ResultSet ms=ps2.executeQuery();
	         
	         System.out.println(sql2);
	         request.setAttribute("FileData",fs);
	         request.setAttribute("MsgData",ms);
	         
	         RequestDispatcher dispatcher = request.getRequestDispatcher(folderpath+"/adminkey.jsp");
      	     dispatcher.forward(request, response);
      	     
      	     
    	 }
    	 
    	 if(request.getParameter("btnname").equals("Intruder Chart")){
    		 
    		// System.out.println("Intruder Chart");
    		 String sql="SELECT rec_id, count(rec_id) as attack FROM intruder_table  GROUP by rec_id";
	         PreparedStatement ps=connection.prepareStatement(sql);
	         ResultSet rs=ps.executeQuery();
	         
	         request.setAttribute("ChartData",rs);
      	   	 RequestDispatcher dispatcher = request.getRequestDispatcher(folderpath+"/chart.jsp");
      	     dispatcher.forward(request, response);
      	     
    	 }
    	 
    	 
    	 
    
}catch(Exception err){ err.printStackTrace(); }
}
}

