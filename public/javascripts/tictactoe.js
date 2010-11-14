$(function() {

	//td:hover in a cross-browser manner
	$('table.tictac td').bind('mouseenter', function() {
	    $(this).addClass('hover');
	}).bind('mouseleave', function() {
	    $(this).removeClass('hover');
	});
	
	$('table td').bind('click', function() {
		var that = $(this);
	    $.post(moveUrl, {'m.x': that.data('x'), 'm.y': that.data('y')}, function(json) {
	    	if (!json.valid) {
	    		$('#log > ul').append('<li>INVALID MOVE</li>');
	    	} else {
	    		console.log(json);
	    	}
	    }, 'json');
	});

});