    <%@include file="../header.jsp" %>
    <%@include file="../signout.jsp" %>
    <%@ page import="java.sql.ResultSet" %>
    
    <script src="<%=request.getContextPath()%>/resources/js/canvasjs.min.js" type="text/javascript"></script>
    <script type="text/javascript">

window.onload = function () {
	var chart = new CanvasJS.Chart("chartContainer", {
		theme: "theme2",//theme1
		title:{
			text: "Intruder Chart"              
		},
		animationEnabled: false,   // change to true
		data: [              
		{
			// Change type to "bar", "area", "spline", "pie",etc.
			type: "column",
			dataPoints: [
				<%
	 			ResultSet Chart 	= (ResultSet)request.getAttribute("ChartData");  
				if(!Chart.next())
				{
					out.println("{ label: '0',  y: 0  }");
				}
				else{
				
				
				do{
									
					String  username			= Chart.getString("rec_id");
					int attack_count 			= Chart.getInt("attack");
					out.println("{ label: '"+username+"',  y: "+attack_count+"  },");
				%>
				<% }while(Chart.next());
				}%>
			]
		}
		]
	});
	chart.render();
}
</script>

     
    
    <style type="text/css">

</style>
 <div class="fileMainDiv">
 <%@include file="../titlemsg.jsp" %> 

 

	    				
		    			<div class="logme" style="float: right">
		    				 <a href="<%=request.getContextPath()%>/resources/pages/adminpage.jsp">
		    				 <img width="90" height="40" src="<%=request.getContextPath()%>/resources/images/back-button.png" alt="Update Acount"/>
		    				  </a><br/>
		    			</div> 
		    			
		    			<div class="logme" style="float: right">
		    				 <a href="<%=request.getContextPath()%>/dl?typ=clear_chart&val=N">
		    				 <img width="50" height="40" src="<%=request.getContextPath()%>/resources/images/clear.png" alt="Update Acount"/>
		    				  </a><br/>
		    			</div>      

				
				
                <div style="padding: 1%;">
               
                    <div class="formBody">
                    
<div id="chartContainer" style="height: 300px; padding-top:25px; width: 100%;"></div>





<%@include file="../footer.jsp" %>

