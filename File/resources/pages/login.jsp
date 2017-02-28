<%@include file="header.jsp" %>
<div class="ContainerDiv">

        
	 <div class="headICon">
        LOGIN
        <br/>
        <div class="">
        <%
       if(request.getAttribute("msg")!=null){ %>
       <div style="color: <% out.println(request.getAttribute("colour")); %>;"><% out.println(request.getAttribute("msg")); %> </div>
    <%   }
	%>
    </div>
    <form action="<%=request.getContextPath()%>/dologin" method="post">
        <div class="margin">
            Username<br/>
            <input type="text" name="username" required placeholder="username"/>
        </div>
        <div class="margin">
            Password<br/>
            <input type="password" name="password" required placeholder="password"/>
        </div>
        <br/>
        <input type="submit" value="Login" name="login" class="btn"/>
        <br/>
        <span style="float:left"> <a href="<%=request.getContextPath()%>/resources/pages/register.jsp" > Create New Account </a></span>
    </form>
</div>
<%@include file="footer.jsp" %>
