<%@page import="javax.servlet.http.HttpSession"%>
<%@page import="support.DBConnection"%>
<%@page import="control.AESCryp"%>

            <%AESCryp aESCryp=new AESCryp();
            try {
				String EncriptFilename= aESCryp.decrypt(session.getAttribute("ssgetfile").toString());
				out.println(EncriptFilename);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}%>  
     		
               
            
       
        
           
        	 