<%@include file="../header.jsp" %>
 <%@include file="../signout.jsp" %>
            <div class="fileMainDiv">
              
             <%@include file="../titlemsg.jsp" %>
				
				
				<div class="logme" style="float: right">
		    				 <a href="<%=request.getContextPath()%>/resources/pages/userpage.jsp">
		    				 <img width="90" height="40" src="<%=request.getContextPath()%>/resources/images/back-button.png" alt="Update Acount"/>
		    				  </a>
		    			</div>      
				
				
                <div style="padding: 1%;">
                   
                    <div style="border: solid 1px #767676">
                    <form action="<%=request.getContextPath()%>/msgtransfer" method="post">
                       
                        <ul class="form-style-1">
                              Send Message
                               <li>
                                 <label>Message Title <span class="required">*</span></label>
                                <input type="text" name="msgtitle" required/>
                            </li>
                            
                            <li>
                                <label>Enter Message <span class="required">*</span></label>
                                <input type="text" name="message" required/>
                               
                            </li>
                           
                             </li>                           
                             <%@include file="selectClients.jsp" %>
                            <li>
                       
                            <li>
                                <input type="submit" value="Send File" />
                            </li>
                        </ul>
                    </form>
                    </div>
                </div>
            </div>
            
<%@include file="../footer.jsp" %>