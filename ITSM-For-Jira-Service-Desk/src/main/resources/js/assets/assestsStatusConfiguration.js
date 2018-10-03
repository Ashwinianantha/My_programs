jQuery(document).ready(function($) {
	
	function createStatusRESTfulTable(){
		if (document.getElementById('asset-config-status-table') != null) {	
			
						
			var StatusNameCreateView = AJS.RestfulTable.CustomCreateView.extend({
					render : function(self) {
						var value = self.value;
						var textField = AJS.$("<input id='status_name' name='name' type='text' class='text small-field' />")
							textField.val(value);
						return textField
					}
			});
			
			var StatusNameEditView = AJS.RestfulTable.CustomEditView.extend({
				render : function(self) {
					var value = self.value;
					var textField = AJS.$("<input id='status_name' name='name' type='text' class='text small-field' />");
						textField.val(value);
					return textField
				}
			});
			
			
			var StatusDescrptionCreateView = AJS.RestfulTable.CustomCreateView.extend({
				render : function(self) {
					var value = self.value;
					var textarea = AJS.$("<textarea id='status_descrption' name='description' class='text' rows='4' cols='40' wrap='hard'/>");
						textarea.val(value);
					return textarea
				}
			});
			
			var StatusDescriptionEditView = AJS.RestfulTable.CustomEditView.extend({
				render : function(self) {
					var value = self.value;
					var textarea = AJS.$("<textarea id='status_descrption' name='description' class='text' rows='4' cols='40' wrap='hard'/>");
						textarea.val(value);
					return textarea
					
				}
			});
			
			var StatusCategoryCreateView = AJS.RestfulTable.CustomCreateView.extend({
		        render: function (self) {
		            var $select = $("<select name='category' id='status_category' class='select'>" +
		            		"<option value='todo'>To Do</option>" +
		            		"<option value='In Progress'>In Progress</option>" +
		                    "<option value='done'>Done</option>" +		                    
		                    "</select>");

		            	$select.val(self.value); // select currently selected
		            return $select;
		        }
		    });
		
			var StatusCategoryEditView = AJS.RestfulTable.CustomEditView.extend({
		        render: function (self) {
		        	 var $select = $("<select name='category' id='status_category' class='select'>" +
			            		"<option value='todo'>To Do</option>" +
			            		"<option value='In Progress'>In Progress</option>" +
			                    "<option value='done'>Done</option>" +		                    
			                    "</select>");

		            	$select.val(self.value); // select currently selected
		            return $select;
		        }
		    });
					
			
			var TextareaReadView = AJS.RestfulTable.CustomReadView.extend({
				render : function(self) {
					var value = self.value;					 
						
					return value
				}
			});
			
			var LozengeReadView = AJS.RestfulTable.CustomReadView.extend({
				render : function(self) {

					if (self.value != null && self.value != "") {

						var $value = self.value;
						var $class;

						if ($value == "done") {
							$class = "aui-lozenge aui-lozenge-success";
						} else if ($value == "todo") {
							$class = "aui-lozenge aui-lozenge-complete";
						} else if ($value == "In Progress") {
							$class = "aui-lozenge aui-lozenge-current";
						}

						return AJS.$(
								"<span class=' " + $class + "' id='status-span' />")
								.text($value);
					} else {						
						var $emphasized = "";
						return $emphasized;
					}
				}
			});
			
			var url = AJS.contextPath();
			
			/* RESTful Table initialization*/
			new AJS.RestfulTable({
			    autoFocus: true,
			    el: AJS.$("#asset-config-status-table"),			  
			    resources: {			        
			    	 all: url + "/rest/acisrest/1/status/all",
				     self: url + "/rest/acisrest/1/status"
			    },
			    columns: [
			        {
			            id: "id",
			            header: AJS.I18n.getText("restful.status.id"),
			            allowEdit : false
			        },
			        {
			            id: "name",
			            header: AJS.I18n.getText("restful.status.name"),
			            createView : StatusNameCreateView,
						editView : StatusNameEditView,
						readView : TextareaReadView,
						allowEdit : true
			        },
			        {
			            id: "description",
			            header: AJS.I18n.getText("restful.status.description"),
			            createView : StatusDescrptionCreateView,
						editView : StatusDescriptionEditView,
						readView : TextareaReadView,
						allowEdit : true
			        },
			        {
			            id: "category",
			            header: AJS.I18n.getText("restful.status.category"),
			            createView : StatusCategoryCreateView,
						editView : StatusCategoryEditView,
						readView : LozengeReadView,
						allowEdit : false
			        }
			        	       
			    ],
			    // message to be displayed when table is empty
		        noEntriesMsg : AJS.I18n.getText("restful.status.empty"),
				allowCreate : true,
				allowReorder : false, // drag and drop reordering
				allowDelete : false,
//				addPosition : "bottom" // create position
				createPosition: "bottom"
			});
			
			
		}
		
	}
	
	createStatusRESTfulTable();
	/*AJS.$('a[href="#tabs-status-configuration"]').live("change click", function(){
		console.log("tabs-status-configuration click function");
		createStatusRESTfulTable();
	});
	
	AJS.$('#assets-config-tabs').on('tabSelect', function(e) {
		  console.log('1- The tab clicked is:', e.target);
		  console.log('1- The corresponding tab pane is:', AJS.$(e.target.getAttribute('href'))[0]);
	});*/
});