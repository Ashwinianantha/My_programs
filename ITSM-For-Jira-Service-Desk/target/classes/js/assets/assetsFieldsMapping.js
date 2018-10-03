jQuery(document).ready(function($) {
	
    $('#btnRight').click(function (e) {
    	
        var selectedOpts = $('#fieldsList option:selected');
        if (selectedOpts.length == 0) {
            alert("Nothing to move.");
            e.preventDefault();
        }
        
        $('#fieldsListSelected').append($(selectedOpts).clone());
        $('#fieldsListSelected option').prop('selected', true);
        $(selectedOpts).remove();
        
        e.preventDefault();
    });
    
    $('#btnLeft').click(function (e) {
    	
        var selectedOpts = $('#fieldsListSelected option:selected');
        if (selectedOpts.length == 0) {
            alert("Nothing to move.");
            e.preventDefault();
        }
        
        $('#fieldsList').append($(selectedOpts).clone());
        $('#fieldsList option').prop('selected', false);
        $(selectedOpts).remove();
        
        
        e.preventDefault();
    });
   

});