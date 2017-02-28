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


public class LoginClass extends HttpServlet {
	
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        try
        {   
        	 String folderpath="resources/pages/";
        	 
        	 String username=request.getParameter("username");
        	 String password=request.getParameter("password");
        	//Receving User name and password
        	 
        	 System.out.println("usernmae:"+username);
        	 
        	// HttpSession session=request.getSession();
        	 Connection connection=DBConnection.DBConnect();
        	 HttpSession session=request.getSession();
        	 //Collecting objects of session and db connect
        	 
	    	 String sql="SELECT * FROM user_table WHERE username=? AND password=?";
	         PreparedStatement ps=connection.prepareStatement(sql);
	         ps.setString(1,username);
	         ps.setString(2,password);
	         ResultSet rs=ps.executeQuery();
        	
	         if (!rs.next()){
	        	   request.setAttribute("msg", "This is not valid user...");
	        	   request.setAttribute("colour","red");
	        	   RequestDispatcher dispatcher = request.getRequestDispatcher(folderpath+"login.jsp");
	        	   connection.close();
	        	   dispatcher.forward(request, response); 
	         }
	        		 
	         do
	           {
	        	   String dbusername	=rs.getString("username");
	               String dbpassword	=rs.getString("password");
	               String dbstatsu		=rs.getString("status");
	               String dbuser_id		=rs.getString("user_id");
	               String dblast_login	=rs.getString("last_login");
	               
	            // date and time 
	               Date dNow = new Date();
	               SimpleDateFormat ft = 
	               new SimpleDateFormat ("E yyyy.MM.dd 'at' hh:mm:ss a");
	               
	               
	               String sql3="update user_table set last_login='"+ft.format(dNow)+"'  WHERE user_id="+dbuser_id;
	     		   PreparedStatement ps2=connection.prepareStatement(sql3);
	     		   ps2.executeUpdate();
	     		    
	               session.setAttribute("seuser_id",dbuser_id);
	               session.setAttribute("seusername",dbusername);
	               session.setAttribute("selast_login",ft.format(dNow));
	               
	               
	               
	               System.out.println("<br/>DBUSRNAME:"+dbusername+" usernmae:"+username);
	               
	        	if(dbusername.equals(username) && dbpassword.equals(password))
	               {
	        		
	        		if(dbstatsu.equals("N")){
	        			   request.setAttribute("msg", "Please wait for admin permission...");
	        			   request.setAttribute("colour","green");
	        			   RequestDispatcher dispatcher = request.getRequestDispatcher(folderpath+"login.jsp");
	        			   connection.close();
	        			   dispatcher.forward(request, response);
	        		}
		               
	            	   if(dbusername.equals("admin")){
	            		   connection.close();
	            		   response.sendRedirect(folderpath+"adminpage.jsp");
	            	   }
	            	   
	            	      connection.close();
	            	   	  response.sendRedirect(folderpath+"userpage.jsp");
	                  
	               }
	               
	           }while(rs.next());
	         
	        
	           
	              
	        
}catch(Exception err)
{
    err.printStackTrace();
}
}

}
