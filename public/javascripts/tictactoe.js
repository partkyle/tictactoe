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
			if (!json.validMove) {
				$('#log > ul').append('<li>'+json.message+'</li>');
			} else {
				$.each(json.state, function(x, val){
					$.each(val, function(y, className){
						if (className != null) {
							$('td[data-x='+x+'][data-y='+y+']').addClass(className);
						}
					});
				});
				$.each(json.moveLog, function(k, val) {
					$('#log > ul').append('<li>'+val+'</li>')
				});
			}
		}, 'json');
	});

});