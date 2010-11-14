$(function() {

	//td:hover in a cross-browser manner
	$('table.tictac td').bind('mouseenter', function() {
	    $(this).addClass('hover');
	}).bind('mouseleave', function() {
	    $(this).removeClass('hover');
	});
});