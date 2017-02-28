<%
   // New location to be redirected
   if(session.getAttribute("seusername")==null)
   {
   String site = request.getContextPath().concat("/resources/pages/login.jsp");
   response.setStatus(response.SC_MOVED_TEMPORARILY);
   response.setHeader("Location", site); 
   }
%>