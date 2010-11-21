$(function() {
	// td:hover in a cross-browser manner
	$('table.tictac:not(.done) td:not(.Player):not(.Computer)').live('mouseenter', function() {
		$(this).addClass('hover');
	}).live('mouseleave', function() {
		$(this).removeClass('hover');
	});
	
	$('table.tictac:not(.done) td').live('click', function() {
		var that = $(this).removeClass('hover').addClass('Player');
		$.post(moveUrl, {'m.x': that.data('x'), 'm.y': that.data('y')}, function(json) {
			if (!json.validMove) {
				that.removeClass('Player');
				$('#log > ul').append('<li>'+json.message+'</li>');
			} else {
				$.each(json.state, function(y, val){
					$.each(val, function(x, className){
						if (className != null) {
							$('td[data-x='+x+'][data-y='+y+']').removeClass('hover').addClass(className);
						}
					});
				});
				$.each(json.moveLog, function(k, val) {
					$('#log > ul').append('<li>'+val+'</li>')
				});
				if (!json.stillPlaying) {
					var li;
					if (json.winner == null)
						li = $('<li>').text(i18n('draw'));
					else
						li = $('<li>').text(i18n('winner', json.winner));
						
					$('#log > ul').append(li);
					that.parents('table.tictac,div.game').addClass('done');
				}
			}
		}, 'json');
	});
	
	$('table.done td').live('click', function() {
	});

});