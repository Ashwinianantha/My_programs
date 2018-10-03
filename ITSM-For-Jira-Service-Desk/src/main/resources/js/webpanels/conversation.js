jQuery(document).ready(function($) {
	
	function createConversation(currentIssueKey){		
		var jiraurl = AJS.contextPath();
		jQuery.ajax({
			 // url: jiraurl + "/rest/acisrest/1/stride/createConversation"; //need to use this 
			  url: "http://ec2-18-220-148-193.us-east-2.compute.amazonaws.com:7070/rest/acisrest/1/stride/createConversation",
			  type: "POST",
			  processData: false,
			  headers:{"Content-Type":"application/json"},
			  data: JSON.stringify({"name":"ACIS_REST", "topic":"Created By using ACIS Rest services.","issueKey":currentIssueKey}),
			  dataType: "json",			  
			  success: function(result) {
				//console.log("result: "+result);
				  location.reload();
			  }
		}); 
	}
	
	
	$("#create-room-id").click(function(){
		var _issueKey = $("#key-val").attr("data-issue-key");
		console.log("issueKey: "+_issueKey);
		createConversation(_issueKey);
	});
});

