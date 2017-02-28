<%
       if(request.getAttribute("msg")!=null){ %>
       <div style="color:<% out.println(request.getAttribute("colour")); %>;"><% out.println(request.getAttribute("msg")); %> </div>
    <%   }
	%>
               <div class="TitleBox">
	    				<div style="float: left">
	    					Welcome :<%=session.getAttribute("seusername") %>
	    					<br/>
	    				Last Login:<%= session.getAttribute("selast_login") %>
	   					</div>
		    			 
		    			<div style="float: right">
		    				<a href="<%=request.getContextPath()%>/logout">
		    				<img style="margin-left: 20px;" height="40" width="200" src="<%=request.getContextPath()%>/resources/images/logout.png" /></a>
              
		    			</div>      
				</div>