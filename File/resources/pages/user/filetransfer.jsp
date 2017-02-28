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
                    <form action="<%=request.getContextPath()%>/filetransfer" method="post" enctype="multipart/form-data">
                        <ul class="form-style-1">
                            File Transfer
                            <li>
                                <label>Choose File <span class="required">*</span></label>
                                <input type="file" name="uploadfile" required/>
                            </li>
                          
                             <%@include file="selectClients.jsp" %>
                            <li>
                                <input type="submit" value="encrypt File and Send" />
                            </li>
                        </ul>
                    </form>
                    </div>
                </div>
            </div>
            
<%@include file="../footer.jsp" %>