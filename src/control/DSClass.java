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


public class DSClass extends HttpServlet {
	
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        try
        {   
        	 String folderpath="resources/pages/admin";
        	 Connection connection=DBConnection.DBConnect();
        	 String type=request.getParameter("typ");
        	 String value=request.getParameter("val");
        	 
        	 System.out.println("type:"+type+"-Value"+value);
        	
        	 if(type.equals("status")){
        		 
        		String user_id=request.getParameter("user_id");
        		 
        		String sql="update user_table set 	status='"+value+"'  WHERE user_id="+user_id;
     		    PreparedStatement ps2=connection.prepareStatement(sql);
     		    
     		    int i=ps2.executeUpdate();
     		    String msg=(i>0)?"User Status Changed!":"";

     		    
     		    //System.out.println(sql);
     		    
     		    request.setAttribute("msg",msg);
     		    request.setAttribute("colour","green");
     		    
     		    String sql2="SELECT * FROM user_table ORDER BY status ASC;";
    	         	PreparedStatement ps=connection.prepareStatement(sql2);
    	         	ResultSet rs=ps.executeQuery();
    	         
    	        
    	         	request.setAttribute("UserData",rs);
    	         //	System.out.println(folderpath+"/usermanagement.jsp");
          	   	RequestDispatcher dispatcher = request.getRequestDispatcher(folderpath+"/usermanagement.jsp");
          	    dispatcher.forward(request, response);
        	 }
        	 if(type.equals("dlt")){
        		 
        		// System.out.println(type);
        		        		            	           	 
        		    String sql="DELETE FROM user_table WHERE user_id="+value;
        		    PreparedStatement ps2=connection.prepareStatement(sql);
        		    
        		    int i=ps2.executeUpdate();
        		    String msg=(i>0)?"User Account Deteleted!":"Failed!! Can't Delete This User !";

        		    
        		    System.out.println(sql);
        		    
        		    request.setAttribute("msg",msg);
        		    request.setAttribute("colour","green");
        		    
        		    String sql2="SELECT * FROM user_table ORDER BY status ASC;";
       	         	PreparedStatement ps=connection.prepareStatement(sql2);
       	         	ResultSet rs=ps.executeQuery();
       	         	request.setAttribute("UserData",rs);
       	         	System.out.println(folderpath+"/usermanagement.jsp");
             	   	RequestDispatcher dispatcher = request.getRequestDispatcher(folderpath+"/usermanagement.jsp");
             	    dispatcher.forward(request, response);
        		 
        		 
        	 }
        	 
        	 	if(type.equals("key")){
        	 		String sq="";
        	 		//System.out.println(type);
        		        		            	           	 
        	 		String user_id=request.getParameter("user_id");
           		    
        	 		if(value.equals("msg")){
        	 			
        	 			 sq="update user_msg_table set 	admin_key_status='Y'  WHERE m_id="+user_id;
             		  
        	 			
        	 		}
        	 		
					if(value.equals("file")){
					        	 			
						 sq="update user_file_table set 	admin_key_status='Y'  WHERE f_id="+user_id;
	         		     			
					        	 		}
        	 		
            		
					PreparedStatement ps6=connection.prepareStatement(sq);	
         		    int i=ps6.executeUpdate();
         		    String msg=(i>0)?"Key Send!":"";


        		    request.setAttribute("msg",msg);
        		    request.setAttribute("colour","green");
        		    
        		 String sql="SELECT * FROM user_file_table where admin_key_status!='Y' and status_msg='puzzle solved' ORDER BY status_msg  ASC";
       	    	 System.out.println(sql);
       	         PreparedStatement ps=connection.prepareStatement(sql);
       	         ResultSet fs=ps.executeQuery();
       	         
       	         System.out.println(sql);
       	         String sql2="SELECT * FROM user_msg_table where admin_key_status!='Y' and status_msg='puzzle solved' ORDER BY status_msg  ASC";
       	         PreparedStatement ps2=connection.prepareStatement(sql2);
       	         ResultSet ms=ps2.executeQuery();
       	         
       	        
       	         request.setAttribute("FileData",fs);
       	         request.setAttribute("MsgData",ms);
       	         
       	         RequestDispatcher dispatcher = request.getRequestDispatcher(folderpath+"/adminkey.jsp");
             	 dispatcher.forward(request, response);
        		 
        		 
        	 }
        	 	
        	 	 if(type.equals("clear_chart")){
            		 
             		// System.out.println(type);
             		        		            	           	 
             		    String sql3="truncate table intruder_table";
             		   System.out.println(sql3);
             		    PreparedStatement ps3=connection.prepareStatement(sql3);
             		    
             		    int i=ps3.executeUpdate();
             		    String msg=(i>0)?"Data Clear now...":"";

             		    
             		   
             		    
	         		    request.setAttribute("msg",msg);
	         		    request.setAttribute("colour","green");
             		 
             		 //Restoring Old Data
	         		// System.out.println("Intruder Chart");
	           		 String sql="SELECT rec_id, count(rec_id) as attack FROM intruder_table  GROUP by rec_id";
	       	         PreparedStatement ps=connection.prepareStatement(sql);
	       	         ResultSet rs=ps.executeQuery();
	       	         
	       	         request.setAttribute("ChartData",rs);
	             	 RequestDispatcher dispatcher = request.getRequestDispatcher(folderpath+"/chart.jsp");
	             	 dispatcher.forward(request, response);
             		 
             	 }
        	 	 
        	 	 if(type.equals("dlfile")){
            		 
        	 		 String sql3="DELETE FROM user_file_table WHERE f_id="+value;
        	 		 System.out.println(sql3);
          		    PreparedStatement ps3=connection.prepareStatement(sql3);
          		    
          		    int i=ps3.executeUpdate();
          		    String msg=(i>0)?"File Deteleted!":"";

          		    
          		   
          		    
	         		    request.setAttribute("msg",msg);
	         		    request.setAttribute("colour","green");
          		 
          		 //Restoring Old Data
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
        	 	 
 if(type.equals("dlfile")){
            		 
        	 		 String sql3="DELETE FROM user_file_table WHERE f_id="+value;
        	 		 System.out.println(sql3);
          		    PreparedStatement ps3=connection.prepareStatement(sql3);
          		    
          		    int i=ps3.executeUpdate();
          		    String msg=(i>0)?"File Deteleted!":"";

          		    
          		   
          		    
	         		    request.setAttribute("msg",msg);
	         		    request.setAttribute("colour","green");
          		 
          		 //Restoring Old Data
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
        	 	
        	 	
        	 	
        
 
        	
        	   
	        
}catch(Exception err)
{
    err.printStackTrace();
}
}

}
