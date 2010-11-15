$(function() {

	// td:hover in a cross-browser manner
	$('table.tictac:not(.done) td').live('mouseenter', function() {
		$(this).addClass('hover');
	}).live('mouseleave', function() {
		$(this).removeClass('hover');
	});
	
	$('table.tictac:not(.done) td').live('click', function() {
		var that = $(this);
		$.post(moveUrl, {'m.x': that.data('x'), 'm.y': that.data('y')}, function(json) {
			if (!json.validMove) {
				$('#log > ul').append('<li>'+json.message+'</li>');
			} else {
				console.log(json);
				$.each(json.state, function(y, val){
					$.each(val, function(x, className){
						if (className != null) {
							$('td[data-x='+x+'][data-y='+y+']').addClass(className);
						}
					});
				});
				$.each(json.moveLog, function(k, val) {
					$('#log > ul').append('<li>'+val+'</li>')
				});
				if (!json.stillPlaying) {
					$('#log > ul').append('<li>Game Over. '+(json.winner == null? 'Draw' : 'Winner: '+json.winner)+'</li>')
					that.parents('table.tictac').addClass('done');
				}
			}
		}, 'json');
	});
	
	$('table.done td').live('click', function() {
		alert('The game is finished - Click new game to start a new one');
	});

});