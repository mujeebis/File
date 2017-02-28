package control;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import support.DBConnection;


public class DownloadFromClass extends HttpServlet {
	
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        try
        {   
        	 String folderpath		="resources/pages/user/";
        	 Connection connection	=DBConnection.DBConnect();
        	 HttpSession session	=request.getSession();
        	 
        	 ServletContext servletContext = getServletContext();
             String contextPath = servletContext.getRealPath(File.separator);
             
        	
        	 int id					=Integer.parseInt(request.getParameter("id"));
        	 String type			=request.getParameter("type");
        	//Receving User name and password
        	 
        	 if(type.equals("msg")){

        		 String sql1="SELECT * FROM user_msg_table where m_id="+id;
    	         PreparedStatement ps1=connection.prepareStatement(sql1);
    	         ResultSet ms1=ps1.executeQuery();
    	         request.setAttribute("Data",ms1);
    	         request.setAttribute("type","msg");
    	         RequestDispatcher dispatcher = request.getRequestDispatcher(folderpath+"download.jsp");
    	         dispatcher.forward(request, response);
        	 }
        	 if(type.equals("file")){
        		 
        		 String sql1="SELECT * FROM user_file_table where f_id="+id;
    	         PreparedStatement ps1=connection.prepareStatement(sql1);
    	         ResultSet fi1=ps1.executeQuery();
    	         request.setAttribute("Data",fi1);
    	         request.setAttribute("type","file");
    	         RequestDispatcher dispatcher = request.getRequestDispatcher(folderpath+"download.jsp");
    	         dispatcher.forward(request, response);
        	 }
        	 
        	 if(type.equals("filedownload")){
 	 			
    	 		 String sql="SELECT * FROM user_file_table where admin_key_status='Y' and f_id="+id;
       	    	
       	         PreparedStatement ps=connection.prepareStatement(sql);
       	         ResultSet fs=ps.executeQuery();
       	         String path = contextPath + "\\uploads\\" + session.getAttribute("seusername")+"\\";
       	         request.setAttribute("Data",fs);
       	         request.setAttribute("path",path);
       	         
       	      fs.next();
       	     String myfilename= fs.getString("filename");
       	     
       	   response.setContentType("text/html");  
       	   PrintWriter out = response.getWriter();  
       	   String filename = myfilename;   
       	   String filepath = path;
       	   response.setContentType("APPLICATION/OCTET-STREAM");   
       	   response.setHeader("Content-Disposition","attachment; filename=\"" + filename + "\"");   
       	   
	       	FileInputStream fileInputStream = new FileInputStream(filepath + filename);  
	       	int i;   
	       	while ((i=fileInputStream.read()) != -1) {  
	       	out.write(i);   
	       	}   
	       	fileInputStream.close();   
	       	out.close();   
	       	
       	  // System.out.println(path+myfilename);
       	   
       	   /*FileInputStream fileInputStream = new FileInputStream(filepath + filename);  
       	               
       	   int i;   
       	   while ((i=fileInputStream.read()) != -1) {  
       	   out.write(i);   
       	   }   
       	   fileInputStream.close();   
       	   out.close(); 
       	   */
        	
       	     
    	 	}
    	 	
    	 	if(type.equals("msgdownload")){
	 			
    	 		 String sql="SELECT * FROM user_msg_table where admin_key_status='Y' and m_id="+id;
    	 		 PreparedStatement ps=connection.prepareStatement(sql);
       	         ResultSet ms=ps.executeQuery();
       	    	 
	       	     request.setAttribute("MsgData",ms);
	       	   	 RequestDispatcher dispatcher = request.getRequestDispatcher(folderpath+"showmsg.jsp");
	       	     dispatcher.forward(request, response); 
	    	   		 
    	   		 
    	 	}
      	   	 
      	    
        	              
	        
}catch(Exception err)
{
    err.printStackTrace();
}
}

}
