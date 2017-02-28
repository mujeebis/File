    <%@include file="../header.jsp" %>
     <%@include file="../signout.jsp" %>
    <%@ page import="java.sql.ResultSet" %>
    
    <style type="text/css">

</style>
 <div class="fileMainDiv">
 <%@include file="../titlemsg.jsp" %> 

 

	    				
		    			<div class="logme" style="float: right">
		    				 <a href="<%=request.getContextPath()%>/resources/pages/adminpage.jsp">
		    				 <img width="90" height="40" src="<%=request.getContextPath()%>/resources/images/back-button.png" alt="Update Acount"/>
		    				  </a>
		    			</div>      

				
				
                <div style="padding: 1%;">
               
                    <div class="formBody">
                    
                        <form action="UserAction" method="post">
                            <table class="dataTable">
                                <tr>
                                    <th>Sl no</th>
                                    <th>Username</th>
                                    <th>email</th>
                                     <th>Last Login</th>
                                    <th>Permission</th>
                                    <th>Delete User</th>
                                   
                                </tr>
                                
			<%
			ResultSet results = (ResultSet)request.getAttribute("UserData");
			
			if(!results.next())
			{
				out.println("<tr>No Data for display...</tr>");
			}
			else{
			int slno=0;
			try{
			do{
			String username = results.getString("username");
			String email = results.getString("email");
			String status = results.getString("status");
			String last_login = results.getString("last_login");
			int user_id = results.getInt("user_id");
			
			
			
			slno++;
			%>
						 <%if(username.equals("admin")){ %>
						 <tr style="display:none;">
						 <%}else{ %> <tr>
							  <%} %>	
								
                                    <td><%=slno%></td>
                                    <td><%=username%></td>
                                    <td><%=email%></td>
                                    <td><%=last_login%></td>
                                    
                                    <td>
                                 
                                    
                                   <%if(status.equals("Y")){ %>
              
                                    YES--	<a href="<%=request.getContextPath()%>/dl?typ=status&val=N&user_id=<%=user_id%>"><img width="10" height="20" src="<%=request.getContextPath()%>/resources/images/cross-mark-th.png" alt="Update Acount"/></a>
                               
                                    <%}else{ %>
                                    
                                    NO--  <a href="<%=request.getContextPath()%>/dl?typ=status&val=Y&user_id=<%=user_id%>"><img width="20" height="20" src="<%=request.getContextPath()%>/resources/images/tick.png" alt="Update Acount"/></a>
                                 <%} %>
                                    </td>
                                    
                                    
                                    <td>
                                   <%if(!username.equals("admin")){ %>
                                    <a href="<%=request.getContextPath()%>/dl?typ=dlt&val=<%=user_id%>">
                                    	<img width="10" height="20" src="<%=request.getContextPath()%>/resources/images/cross-mark-th.png" alt="Update Acount"/>
                                     </a>
                                    <%} %>
                                    </td>
                                
                                </tr>
			
			<% }while(results.next());
			
			}catch(Exception ie){
			out.println(ie);
			}}
			%>


                                
                               
                            </table>
                        </form>
                    </div>
                </div>
            </div>
        
            </div>
        
    <%@include file="../footer.jsp" %>