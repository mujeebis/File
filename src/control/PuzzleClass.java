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


public class PuzzleClass extends HttpServlet {
	
@Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
    try
    {
    	HttpSession session=request.getSession();
    	 Connection connection=DBConnection.DBConnect();
    	 //Collecting objects of session and db connect
    	 String folderpath="resources/pages/";
    	 
    	//status_msg =puzzle not solved	//puzzle solved   	//New Message   	//New File    	//Request send
    	 
    	 //type =msg/file
    	
   	 
   	 String type				=request.getParameter("type");
   	 String id					=request.getParameter("id");
   	 String current_user		=request.getParameter("current_user");
   	 String status_msg			=request.getParameter("status_msg");
   	 String rec_id				=request.getParameter("rec_id");
   	 
  // date and time 
   	 Date dNow = new Date();
     SimpleDateFormat ft = 
     new SimpleDateFormat ("E yyyy.MM.dd 'at' hh:mm:ss a");
     
   	
   	 if(status_msg.equals("puzzle solved")){
   	 String sql="";
    
    if(type.equals("file")){
    	 sql="update user_file_table set status_msg='"+status_msg+"'  WHERE f_id="+id;
    }
    		
	 if(type.equals("msg")){
	     sql="update user_msg_table set status_msg='"+status_msg+"'  WHERE m_id="+id;
	 }
	  PreparedStatement ps2=connection.prepareStatement(sql);
	  int i=ps2.executeUpdate();
	  String msg=(i>0)?"Download Request Send...":"";
	  System.out.println(sql);
	    
      request.setAttribute("msg",msg);
      request.setAttribute("colour","green");
      RequestDispatcher dispatcher = request.getRequestDispatcher(folderpath+"userpage.jsp");
      connection.close();
      dispatcher.forward(request, response);
   	 }
   	 else
   	 {
   		String serverIP = request.getLocalAddr();
   		
   		String sql2="SELECT username FROM  user_table where username!='admin' and user_id="+current_user;  
        PreparedStatement ps3 =connection.prepareStatement(sql2);
        ResultSet clients=ps3.executeQuery();
        clients.next();
        current_user=clients.getString("username");
        
        String sql3="SELECT username FROM  user_table where username!='admin' and user_id="+rec_id;  
        PreparedStatement ps4 =connection.prepareStatement(sql3);
        ResultSet clients2=ps4.executeQuery();
        clients2.next();
        rec_id=clients2.getString("username");
        
        
   		String sql="INSERT INTO intruder_table(user_id,rec_id,file_id,status_msg,time,ipaddress) VALUES(?,?,?,?,?,?)";
   	    PreparedStatement ps2=connection.prepareStatement(sql);
   	    ps2.setString(1, rec_id); 
   	    ps2.setString(2, current_user);
   	    ps2.setString(3, id);
   	    ps2.setString(4, status_msg);
   	    ps2.setString(5, ft.format(dNow));
   	    ps2.setString(6,serverIP);
   	    
   	    int i=ps2.executeUpdate();
   	    String msg=(i>0)?"Time Out... !":"";

   	    request.setAttribute("msg",msg);
   	    request.setAttribute("colour","red");
   	    RequestDispatcher dispatcher = request.getRequestDispatcher(folderpath+"login.jsp");
   	    connection.close();
   	    session.invalidate(); 
   	    dispatcher.forward(request, response);
   	    
   	 }
	    	  	
    }catch(Exception err){ err.printStackTrace(); }
  }
}
