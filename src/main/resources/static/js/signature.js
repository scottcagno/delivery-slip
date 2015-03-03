var sigdiv = $('div[id="signature"]');

$(document).ready(function() {
    sigdiv.jSignature();
});

$('a[id="clear"]').click(function() {
	sigdiv.jSignature('clear');
});

$('a[id="accept"]').click(function() {
	$('input[id="signature"]').val(sigdiv.jSignature('getData', 'default'));
	$('form[id="signatureForm"]').submit()
});