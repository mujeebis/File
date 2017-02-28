 <%@include file="header.jsp" %>
 <script>
function myFunction() {
	document.getElementById("pasmsg").innerHTML = "";
	
    var pass1 = document.getElementById("password").value;
    var pass2 = document.getElementById("password2").value;
    var ok = true;
    if (pass1 != pass2) {
        document.getElementById("pasmsg").innerHTML = "Passwords Do not match !";
        ok = false;
    }
    return ok;
}
</script>

 <div class="body">
            <div class="container">
                <div class="ContainerDiv">
                    <div class="headICon">
                       NEW REGISTRATION 
                       <br/>
        <div class="">
        <%
       if(request.getAttribute("msg")!=null){ %>
       <div style="color:<% out.println(request.getAttribute("colour")); %>;"><% out.println(request.getAttribute("msg")); %> </div>
    <%   }
	%>
	
                    </div>
                    <form action="<%=request.getContextPath()%>/doregister" method="post" id="registration" onsubmit="return myFunction()">
                        <div class="margin">
                            Username<span class="require">*</span><br/>
                            <input  type="text" required placeholder="username" name="username"/>
                        </div>
                        <div class="margin">
                            Password<span class="require">*</span><br/>
                            <input  type="password" required placeholder="password" id="password"  name="password"/>
                        </div>
                        
                          <div class="margin">
                            Confirm Password<span class="require">*</span><br/>
                            <input onfocusout="myFunction()"  type="password" required placeholder="password" id="password2"  name="password2"/>
                             <div id="pasmsg" style="color: red;"></div>
                        </div>

                        <div class="margin">
                            Email<span class="require">*</span><br/>
                            <input  type="email" required placeholder="email.com" name="email"/>
                        </div>
                        <br/>
                        <input type="submit" value="Sign Up" id="submit" name="createUser" class="btn"/>
                        <br/>
<br/>
        <span style="float:left"> <a href="<%=request.getContextPath()%>/resources/pages/login.jsp" >Back to Login  </a></span>
        
                    </form>
                </div>
            </div>
        </div>
<%@include file="footer.jsp" %>