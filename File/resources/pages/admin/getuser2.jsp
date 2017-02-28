<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="support.DBConnection"%>

       
            <%
            Connection con10=DBConnection.DBConnect();
        
            int recID10=(Integer)session.getAttribute("display_username2");
            String sql10="SELECT username FROM  user_table where username!='admin' and user_id="+recID10;  
            PreparedStatement ps10 =con10.prepareStatement(sql10);
            ResultSet clients10=ps10.executeQuery();
            clients10.next();
            String username10=clients10.getString("username");
       		out.println(username10);     
     		%>
               
            
       
        
           
        	 