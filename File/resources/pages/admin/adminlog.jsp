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
                   Transaction Logs
                    <div class="formBody">
                        <form action="UserAction" method="post">
                        Received/Send List
                            <table class="dataTable">
                            
                                <tr>
                                    <th>Sl.no</th>
                                    <th>Sender</th>
                                    <th>Receiver</th>
                                    <th>File/Msg</th>
                                    <th>Date</th>
                                    <th>Status</th>
                                    <th>Key Status</th>
                                    <th>Delete File</th>
                                    
                                    
                                </tr>
                                <%  int slno=1;
                                try{
            ResultSet Fileresults 	= (ResultSet)request.getAttribute("FileData");
            ResultSet Msgresults 	= (ResultSet)request.getAttribute("MsgData");                    
			
			if(!Fileresults.next())
			{
				out.println("<tr>:No Files ..</tr>");
			}
			else{
			
			
			do{
								
				int f_id 				= Fileresults.getInt("f_id");
				int user_id 			= Fileresults.getInt("user_id");
				int receiver_id 		= Fileresults.getInt("receiver_id");
				String date 			= Fileresults.getString("date");
				String status_msg 		= Fileresults.getString("status_msg");
				String admin_key_status = Fileresults.getString("admin_key_status");
				session.setAttribute("display_username",user_id);
				session.setAttribute("display_username2",receiver_id);

			
			%>
										 <tr>				
                                    <td><%=slno++%></td>
                                    <td> <%@include file="../user/getuser.jsp" %></td>
                                    <td> <%@include file="getuser2.jsp" %></td>
                                     <td>File</td>
                                    <td><%=date%></td>
                                    
                                    <td style="background-color:<%if(status_msg.equals("puzzle solved")){out.println("#98e698");} %>">
                                    
                                    
                                    <%=status_msg%>
                                    
                                    </td>
                                    <td><%
                                    if(admin_key_status.equals("Y")){ %>
                                    	<img width="20" height="20" src="<%=request.getContextPath()%>/resources/images/tick.png" />
                                    <% }
                                    else{ %>
                                    	<img width="10" height="20" src="<%=request.getContextPath()%>/resources/images/cross-mark-th.png"/>
                                    <% }
                                    
                                    %></td>
                                    <td>
                                  
                                    <a href="<%=request.getContextPath()%>/dl?typ=dlfile&val=<%=f_id%>">
                                    	<img width="10" height="20" src="<%=request.getContextPath()%>/resources/images/cross-mark-th.png" alt="Update Acount"/>
                                     </a>
                                    
                                    </td>
                                                                   
                                </tr>
			
			<% }while(Fileresults.next());
			}
			
			if(!Msgresults.next())
			{
				out.println("<tr>:No Message ..</tr>");
			}
			else{
			//int slno=0;
			
			do{
								
				int m_id 				= Msgresults.getInt("m_id");
				int user_id 			= Msgresults.getInt("user_id");
				int receiver_id 		= Msgresults.getInt("receiver_id");
				String date 			= Msgresults.getString("date");
				String status_msg 		= Msgresults.getString("status_msg");
				String admin_key_status = Msgresults.getString("admin_key_status");
				session.setAttribute("display_username",user_id);
				session.setAttribute("display_username2",receiver_id);

			slno++;
			%>
										 <tr>				
                                    <td><%=slno++%></td>
                                    <td> <%@include file="../user/getuser.jsp" %></td>
                                     <td> <%@include file="getuser2.jsp" %></td>
                                     <td>Message</td>
                                    <td><%=date%></td>
                                    <td style="background-color:<%if(status_msg.equals("puzzle solved")){out.println("#98e698");} %>">
                                    <%=status_msg%></td>
                                    
                                    <td><%
                                    if(admin_key_status.equals("Y")){ %>
                                    	<img width="20" height="20" src="<%=request.getContextPath()%>/resources/images/tick.png" />
                                    <% }
                                    else{ %>
                                    	<img width="10" height="20" src="<%=request.getContextPath()%>/resources/images/cross-mark-th.png"/>
                                    <% }
                                    
                                    %></td>
                                         
                                         <td>
                                  
                                    <a href="<%=request.getContextPath()%>/dl?typ=dlmsg&val=<%=m_id%>">
                                    	<img width="10" height="20" src="<%=request.getContextPath()%>/resources/images/cross-mark-th.png" alt="Update Acount"/>
                                     </a>
                                    
                                    </td>                        
                                </tr>
			
			<% }while(Msgresults.next());
			
			}
			
			
			
			}catch(Exception ie){
			out.println(ie);
			}
			
			%>
                                
                                
                            </table>
                            
                        </form>
                    </div>
                </div>
            </div>
        
            </div>
        
    <%@include file="../footer.jsp" %>