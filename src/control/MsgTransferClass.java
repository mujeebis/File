package control;


import java.io.IOException;

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

public class MsgTransferClass  extends HttpServlet {
	
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
         
    	
    		 AESCryp aESCryp=new AESCryp();
    		
        	 String folderpath="resources/pages/";
        	 HttpSession session=request.getSession();
        	 
        	 String messagetitle	=request.getParameter("msgtitle");
           	 String message	=request.getParameter("message");
           	 
        	 String EncriptFilename=null;
        	 String myfilename=null;
        	            	 
              
                try {
					 EncriptFilename= aESCryp.encrypt(messagetitle);
					System.out.println(EncriptFilename);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                
             
             // date and time 
             Date dNow = new Date();
             SimpleDateFormat ft = 
             new SimpleDateFormat ("E yyyy.MM.dd 'at' hh:mm:ss a");
             
             //Save operation
             String sen_id		=	(String) session.getAttribute("seuser_id");
             String rec_id		=	request.getParameter("recieverCl");
             String msg_key 	=	EncriptFilename;
           
             String nowDate 	=   ft.format(dNow);
             String status_msg	=   "New Message";
             String admin_key_status="N";
             
             String sql="INSERT INTO user_msg_table(user_id,receiver_id,msg_key,msttitle,message,date,status_msg,admin_key_status) VALUES(?,?,?,?,?,?,?,?)";
             PreparedStatement ps2;
			try {
				Connection connection=DBConnection.DBConnect();
				ps2 = connection.prepareStatement(sql);
			
             ps2.setString(1,sen_id); 
             ps2.setString(2,rec_id);
             ps2.setString(3,msg_key);
             ps2.setString(4,messagetitle);
             
             ps2.setString(5,message);
             ps2.setString(6,nowDate);
             ps2.setString(7,status_msg);
             ps2.setString(8,admin_key_status);
             
             int i=ps2.executeUpdate();
             String msg=(i>0)?"Message Added!...!":"Failed!!Message can't Add.";

             request.setAttribute("msg",msg);
             request.setAttribute("colour","green");
             RequestDispatcher dispatcher = request.getRequestDispatcher(folderpath+"userpage.jsp");
             connection.close();
             dispatcher.forward(request, response);
           
            
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

         }

        
         
        


}