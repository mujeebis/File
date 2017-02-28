<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="support.DBConnection"%>

       
            <%
            Connection con=DBConnection.DBConnect();
        
            int recID=(Integer)session.getAttribute("display_username");
            String sql="SELECT username FROM  user_table where username!='admin' and user_id="+recID;  
            PreparedStatement ps =con.prepareStatement(sql);
            ResultSet clients=ps.executeQuery();
            clients.next();
            String username=clients.getString("username");
       		out.println(username);     
     		%>
               
            
       
        
           
        	 