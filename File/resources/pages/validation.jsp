<script src="<%=request.getContextPath()%>/resources/validation/jquery.validationEngine-en.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=request.getContextPath()%>/resources/validation/jquery.validationEngine.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=request.getContextPath()%>/resources/validation/jquery.js" type="text/javascript" charset="utf-8"></script>
<link href="<%=request.getContextPath()%>/resources/validation/validationEngine.jquery.css" rel="stylesheet" type="text/css"/>
 
 <script>
	$(document).ready(function(){

	$("#submit").click(function() {
	
var chkvalidate= $("#registration").validationEngine('validate');
	var val=$("#validate").val();
   if(chkvalidate==false)
   {
   var val=$("#validate").val()=val+1;
   }
   else
   {
    $(".finish_hide").show();
   }
   
	});

	
	
		$("#registration").validationEngine();
		
		});
</script>