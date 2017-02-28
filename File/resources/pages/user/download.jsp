
<%@include file="../header.jsp" %>
<link href="<%=request.getContextPath()%>/resources/css/puzzle.css" rel="stylesheet" type="text/css"/>
<script src="<%=request.getContextPath()%>/resources/js/puzzle.js" type="text/javascript"></script>

<script>

 function startTimer(duration, display) {
    var start = Date.now(),
        diff,
        minutes,
        seconds;
    function timer() {
        // get the number of seconds that have elapsed since 
        // startTimer() was called
        diff = duration - (((Date.now() - start) / 1000) | 0);

        // does the same job as parseInt truncates the float
        minutes = (diff / 60) | 0;
        seconds = (diff % 60) | 0;

        minutes = minutes < 10 ? "0" + minutes : minutes;
        seconds = seconds < 10 ? "0" + seconds : seconds;

        display.textContent = minutes + ":" + seconds; 

        if (diff <= 0) {
            // add one second so that the count down starts at the full duration
            // example 05:00 not 04:59
            start = Date.now() + 1000;
        }
    };
    // we don't want to wait a full second before the timer starts
   // timer();
    setInterval(timer, 1000);
} 

window.onload = function () {
	Reset();
	
    var fiveMinutes = 30 * 1,
        display = document.querySelector('#time');
    startTimer(fiveMinutes, display);
    
    
    window.setTimeout(function() { 
    	 document.getElementById("submit_me").submit();}, 30000);
};

</script>

 <%@include file="../signout.jsp" %>
    <%@ page import="java.sql.ResultSet" %>
            <div class="fileMainDiv">
              
             <%@include file="../titlemsg.jsp" %>
				
                <div style="padding: 1%;">
                   
                     <div style="border: solid 1px #767676">
                    <form action="<%=request.getContextPath()%>/puzzle" id="submit_me" method="post">
                       
                              
                       
                        <ul class="form-style-1">
                              
                              <div>Solve This puzzle with in :<span id="time"></span> minutes!</div>
                               <li>
                               
                                 <li>


                                
                            </li>
                              <center>
       
        <table cellpadding="0" cellspacing="4" align="center" id="tbl">
        </table>
        <br />
        <br />
        <br />
        <input type="hidden" name="rows" id="rows" value="2" size="3" style="text-align: center;" />
        
        <input type="hidden" name="cols" id="cols" value="2" size="3" style="text-align: center;" />
        <input type="button" value="Reload" onclick="Reset()" />
        <br />
        <br />
        Moves: <span id="moves">0</span>


    </center>
                            <li id="now_show_submit">
                              
                            </li> <br/>
                                              
                                                  
                            
                        </ul>
                        <!--Hidden Area  -->
                        <%
                        int id=0;
                        ResultSet Fileresults 	= (ResultSet)request.getAttribute("Data");
                        String type=request.getAttribute("type").toString();
                       
                        
                        Fileresults.next();
                        
                        String rec_id	=Fileresults.getString("user_id");
                        if(type.equals("msg")){
                        id 				= Fileresults.getInt("m_id");
                        }else
                        {
                        id 				= Fileresults.getInt("f_id");
                        }
                        %>
                        <input type="hidden" value="<%=id%>" name="id" />
                        <input type="hidden" value="<%=rec_id%>" name="rec_id" />
                        <input type="hidden" value="<%=type%>" name="type" />
                        <input type="hidden" value="<%=session.getAttribute("seuser_id")%>" name="current_user" />
                        <input type="hidden" value="puzzle not solved" name="status_msg" id="puzzle_status"/>
            			
                       
                        
                         <!--Hidden Area  -->
                    </form>
                    </div>
                </div>
            </div>
            
<%@include file="../footer.jsp" %>

			
                