package control;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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

import javax.servlet.ServletContext;
import javax.servlet.http.Part;


public class FileTransferClass  extends HttpServlet {
	
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
         
    	
    		AESCryp aESCryp=new AESCryp();
    		
        	 String folderpath="resources/pages/";
        	
			
        	 HttpSession session=request.getSession();
        	 String EncriptFilename=null;
        	 String myfilename=null;
        	 
        	 ServletContext servletContext = getServletContext();
             String contextPath = servletContext.getRealPath(File.separator);
             
             String path = contextPath + "\\uploads\\" + session.getAttribute("seusername");
             System.out.println(path);
              
             File file=new File(path);
             if(!file.exists())
                 file.mkdirs();
             Part filePart = request.getPart("uploadfile");
             //return content type of the file
             String type = filePart.getHeader("content-type");
            
             //checking content type of file. 
             if (!type.equals("application/x-msdownload")) {
            	 
                 final String fileName = getFileName(filePart);
                 myfilename=fileName;
                try {
					 EncriptFilename= aESCryp.encrypt(fileName);
				//	System.out.println(EncriptFilename);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                
               
                 OutputStream fout = null;
                 InputStream filecontent = null;

                 try {
                     fout = new FileOutputStream(new File(path + File.separator + fileName));
                     filecontent = filePart.getInputStream();
                     int read = 0;
                     final byte[] bytes = new byte[32 * 1024];

                     while ((read = filecontent.read(bytes)) != -1) {
                         fout.write(bytes, 0, read);
                     }

                     fout.flush();
                     fout.close();
                 } catch (Exception er) {
                     System.out.println("error:"+er.getMessage());
                 }
             }
             
             // date and time 
             Date dNow = new Date();
             SimpleDateFormat ft = 
             new SimpleDateFormat ("E yyyy.MM.dd 'at' hh:mm:ss a");
             
             //Save operation
             String sen_id		=	(String) session.getAttribute("seuser_id");
             String rec_id		=	request.getParameter("recieverCl");
             String Filekey 	=	EncriptFilename;
             String Filename	=   myfilename;
             String nowDate 	=   ft.format(dNow);
             String status_msg	=   "New File";
             String admin_key_status="N";
             
             String sql="INSERT INTO user_file_table(user_id,receiver_id,file_key,filename,date,status_msg,admin_key_status) VALUES(?,?,?,?,?,?,?)";
             PreparedStatement ps2;
			try {
				Connection connection=DBConnection.DBConnect();
				ps2 = connection.prepareStatement(sql);
			
             ps2.setString(1,sen_id); 
             ps2.setString(2,rec_id);
             ps2.setString(3,Filekey);
             ps2.setString(4,Filename);
             ps2.setString(5,nowDate);
             ps2.setString(6,status_msg);
             ps2.setString(7,admin_key_status);
             
             int i=ps2.executeUpdate();
             String msg=(i>0)?"File Added!...!":"Failed!!File can't Add.";

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

         public String getFileName(final Part part) {
             for (String content : part.getHeader("content-disposition").split(";")) {
                 if (content.trim().startsWith("filename")) {
                     return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");

                 }
             }
             return null;
             
             
         }
         
         
        


}