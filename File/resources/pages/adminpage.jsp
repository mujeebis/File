<%@include file="header.jsp" %>
 <%@include file="signout.jsp" %>
<style type="text/css">
.btn1{
padding:10px 100px 10px 39px;
}
</style>
 <div class="ContainerDiv" style="padding: 10px; !important;width:70%;">
               
                <%@include file="titlemsg.jsp" %>
               <div style="padding: 1%;margin-top: 3%">
               <form action="<%=request.getContextPath()%>/usermanagment" method="post">
                   <img height="20" width="20" src="<%=request.getContextPath()%>/resources/images/users.png" /><input class="btn1" type="submit" value="User Management" name="btnname">
                   <img height="20" width="20" src="<%=request.getContextPath()%>/resources/images/logs.png" /><input class="btn1" type="submit" value="Transaction Logs" name="btnname">
                   <img height="20" width="20" src="<%=request.getContextPath()%>/resources/images/key.png" /><input class="btn1" type="submit" value="Keys Request" name="btnname">
                   <img height="20" width="20" src="<%=request.getContextPath()%>/resources/images/chart-search-icon.png" /><input class="btn1" type="submit" value="Intruder Chart" name="btnname">
             		
               </form>   
</div>
            </div>

<%@include file="footer.jsp" %>