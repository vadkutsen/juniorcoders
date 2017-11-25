$( document ).ready( main );

function main() {
	
	$( '.btn-collapse' ).click(function(e) {
		e.preventDafault();
		var $this = $(this);
		var $collapse = $this.closest('.collapse-group').find('.collapse');
		$collapse.collapse('toggle');
	});
	
	
}