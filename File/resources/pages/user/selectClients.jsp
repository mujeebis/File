<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="support.DBConnection"%>

<li>
    <label>Select Reciever <span class="required">*</span></label>
    <select name="recieverCl" class="field-select" required>
        <option value="">--Select--</option>
        <%
           
        Connection con=DBConnection.DBConnect();
        
           
            String sql="SELECT user_id,username FROM  user_table where username!='admin' and user_id!="+session.getAttribute("seuser_id");  
            
            PreparedStatement ps =con.prepareStatement(sql);
            ResultSet clients=ps.executeQuery();
            
            while(clients.next())
            {
                int userId=clients.getInt("user_id");
                String name=clients.getString("username");
            
        %>
        <option value="<%=userId%>"><%=name%></option>
        <%}%>
       
    </select>
</li>