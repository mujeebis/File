<%@include file="header.jsp" %>

 <%@include file="signout.jsp" %>
 <div class="ContainerDiv" style="padding: 0 !important;width:70%;">
 <%@include file="titlemsg.jsp" %>
 
               <div style="padding: 1%;margin-top:2%">
                   <a href="<%=request.getContextPath()%>/user_notifications" class="btn1"><img src="<%=request.getContextPath()%>/resources/images/notification.png" /> Notifications</a>
                   <a href="<%=request.getContextPath()%>/resources/pages/user/filetransfer.jsp" class="btn1"><img src="<%=request.getContextPath()%>/resources/images/fTrans.png" />File Transfer</a>
                   <a href="<%=request.getContextPath()%>/resources/pages/user/sendmessage.jsp" class="btn1"><img src="<%=request.getContextPath()%>/resources/images/sendMsg.png" />Send Message</a>
                   </div>
            </div>


<%@include file="footer.jsp" %>