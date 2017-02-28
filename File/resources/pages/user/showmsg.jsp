<%@include file="../header.jsp" %>
 <%@include file="../signout.jsp" %>
 <%@ page import="java.sql.ResultSet" %>
 
            <div class="fileMainDiv">
              
             <%@include file="../titlemsg.jsp" %>
             
             <div class="logme" style="float: right">
		    				 <a href="<%=request.getContextPath()%>/resources/pages/userpage.jsp">
		    				 <img width="90" height="40" src="<%=request.getContextPath()%>/resources/images/back-button.png" alt="Update Acount"/>
		    				  </a>
		    			</div>   
		    			 	
				
                <div style="padding: 1%;">
                   
                    <div style="border: solid 1px #767676">
                   <%
                   
                   ResultSet Msgresults 	= (ResultSet)request.getAttribute("MsgData");                    
                   Msgresults.next();
                   String msgTitle			= Msgresults.getString("msttitle");
                   String msg				= Msgresults.getString("message");
                   int user_id 			= 	 Msgresults.getInt("user_id");
                   session.setAttribute("display_username",user_id);
       			
                   %>
                       
                        <ul class="form-style-1">
                              Message From :<%@include file="getuser.jsp" %>
                               <li>
                                 <label>Message Title <span class="required"></span></label>
                                <input type="text" value="<%=msgTitle%>" readonly="readonly"/>
                            </li>
                            
                            <li>
                                <label>Message: <span class="required"></span></label>
                                <textarea rows="10" cols="20" readonly="readonly">
                                <%=msg%>
                                
                                </textarea>
                               
                            </li>
                           
                        </ul>
                   
                    </div>
                </div>
            </div>
            
<%@include file="../footer.jsp" %>