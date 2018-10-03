jQuery(document).ready(function($) {
	
	function createFieldsRESTfulTable(){
		if (document.getElementById('asset-config-fields-table') != null) {	
			
						
			var FieldNameCreateView = AJS.RestfulTable.CustomCreateView.extend({
					render : function(self) {
						var value = self.value;
						var textField = AJS.$("<input id='field_name' name='name' type='text' class='text small-field' />")
							textField.val(value);
						return textField
					}
			});
			
			var FieldNameEditView = AJS.RestfulTable.CustomEditView.extend({
				render : function(self) {
					var value = self.value;
					var textField = AJS.$("<input id='field_name' name='name' type='text' class='text small-field' />");
						textField.val(value);
					return textField
				}
			});
			
			
			var FieldDescrptionCreateView = AJS.RestfulTable.CustomCreateView.extend({
				render : function(self) {
					var value = self.value;
					var textarea = AJS.$("<textarea id='field_descrption' name='description' class='text' rows='4' cols='40' wrap='hard'/>");
						textarea.val(value);
					return textarea
				}
			});
			
			var FieldDescriptionEditView = AJS.RestfulTable.CustomEditView.extend({
				render : function(self) {
					var value = self.value;
					var textarea = AJS.$("<textarea id='field_descrption' name='description' class='text' rows='4' cols='40' wrap='hard'/>");
						textarea.val(value);
					return textarea
					
				}
			});
			
			var FieldTypeCreateView = AJS.RestfulTable.CustomCreateView.extend({
		        render: function (self) {
		            var $select = $("<select name='type' id='field_type' class='select'>" +
		            		"<option value='text'>Text(single line)</option>" +
		            		"<option value='textarea'>Text(Multi line)</option>" +
		                    "<option value='select'>Single Select</option>" +
		                    "<option value='multiselect'>Multi Select</option>" +
		                    "<option value='multicheckboxes'>Check Boxs</option>" +
		                    "<option value='radiobuttons'>Radio Buttonx</option>" +
		                    "</select>");

		            	$select.val(self.value); // select currently selected
		            return $select;
		        }
		    });
		
			var FieldTypeEditView = AJS.RestfulTable.CustomEditView.extend({
		        render: function (self) {
		            var $select = $("<select name='type' id='field_type' class='select'>" +
		            		"<option value='text'>Text(single line)</option>" +
		            		"<option value='textarea'>Text(Multi line)</option>" +
		                    "<option value='select'>Single Select</option>" +
		                    "<option value='multiselect'>Multi Select</option>" +
		                    "<option value='multicheckboxes'>Check Boxs</option>" +
		                    "<option value='radiobuttons'>Radio Buttonx</option>" +
		                    "</select>");

		            	$select.val(self.value); // select currently selected
		            return $select;
		        }
		    });
			
			var FieldValuesEditView = AJS.RestfulTable.CustomEditView.extend({
		        render: function (self) {
		        	var textarea = AJS.$("<textarea id='field_values' name='fieldValues' class='text' rows='4' cols='40' wrap='hard'/>");
						textarea.val(value);
					return textarea
		        }
		    });
			
			var FieldValuesCreateView = AJS.RestfulTable.CustomCreateView.extend({
				render : function(self) {
					var value = self.value;
					var textarea = AJS.$("<textarea id='field_values' name='fieldValues' class='text' rows='4' cols='40' wrap='hard'/>");
						textarea.val(value);
					return textarea
				}
			});
			
			var TextareaReadView = AJS.RestfulTable.CustomReadView.extend({
				render : function(self) {
					var value = self.value;					 
						
					return value
				}
			});
			var url = AJS.contextPath();
			
			/* RESTful Table initialization*/
			new AJS.RestfulTable({
			    autoFocus: true,
			    el: AJS.$("#asset-config-fields-table"),			  
			    resources: {			        
			    	 all: url + "/rest/acisrest/1/fields/all",
				     self: url + "/rest/acisrest/1/fields"
			    },
			    columns: [
			        {
			            id: "id",
			            header: AJS.I18n.getText("restful.fields.id"),
			            allowEdit : false
			        },
			        {
			            id: "name",
			            header: AJS.I18n.getText("restful.fields.name"),
			            createView : FieldNameCreateView,
						editView : FieldNameEditView,
						readView : TextareaReadView,
						allowEdit : true
			        },
			        {
			            id: "description",
			            header: AJS.I18n.getText("restful.fields.description"),
			            createView : FieldDescrptionCreateView,
						editView : FieldDescriptionEditView,
						readView : TextareaReadView,
						allowEdit : true
			        },
			        {
			            id: "type",
			            header: AJS.I18n.getText("restful.fields.type"),
			            createView : FieldTypeCreateView,
						//editView : FieldTypeEditView,
						readView : TextareaReadView,
						allowEdit : false
			        },
			        {
			            id: "fieldValues",
			            header: AJS.I18n.getText("restful.fields.values"),
			            createView : FieldValuesCreateView,
						editView : FieldValuesEditView,
						readView : TextareaReadView,
						allowEdit : true
			        }			       
			    ],
			    // message to be displayed when table is empty
		        noEntriesMsg : AJS.I18n.getText("restful.fields.empty"),
				allowCreate : true,
				allowReorder : false, // drag and drop reordering
				allowDelete : false,
//				addPosition : "bottom" // create position
				createPosition: "bottom"
			});
			
			
		}
		
	}
	
	/*AJS.$('a[href="#tabs-fields-configuration"]').live("change click", function(){
		console.log("tabs-fields-configuration click function");
		createFieldsRESTfulTable();
	});
	AJS.$('#assets-config-tabs').on('tabSelect', function(e) {
		  console.log('2- The tab clicked is:', e.target);
		  console.log('2- The corresponding tab pane is:', AJS.$(e.target.getAttribute('href'))[0]);
	});*/
	createFieldsRESTfulTable();
});